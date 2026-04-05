# XPrinter USB Recovery Task Update

## Objective

XPrinter USB printer long time stable থাকার পরে হঠাৎ disconnect হয়ে গেলে existing normal print flow change না করে connection loss detect করা, reconnect attempt দেওয়া, এবং print recovery improve করা।

## Summary Of What Was Added

এই task-এ XPrinter Android implementation-এ নিচের capability add করা হয়েছে:

1. USB detach / attach event detection
2. Automatic USB connection watchdog
3. Automatic reconnect attempt for lost USB session
4. Physical USB availability check before reconnect
5. Safer stale session cleanup
6. Better connection state tracking in service layer
7. Existing pending print retry flow-এর সাথে recovery integration

## Why These Changes Were Added

Existing issue ছিল:
- XPrinter long time connected থাকার পরে session dead হয়ে যেতে পারে
- USB physically unplug হলে app side-এ stale connected state থেকে যেতে পারে
- Connection object alive দেখালেও actual USB communication dead হতে পারে
- Print fail হওয়ার পরে root cause সবসময় immediately clear না

এই changes add করার main purpose:
- USB loss যত তাড়াতাড়ি possible detect করা
- App restart ছাড়া recovery chance বাড়ানো
- Dead connection state clear করা
- Existing print flow না ভেঙে failure-path improve করা

## Files Updated

### [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt)

এই file-এ XPrinter flow-এর app-facing service logic আছে। এখানে USB loss detection, watchdog, reconnect orchestration add করা হয়েছে।

### [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt)

এই file-এ low-level binder/service printer state management আছে। এখানে stale printer cleanup, accurate connection state validation, এবং write failure state update improve করা হয়েছে।

## Detailed Changes

## 1. USB Detach / Attach Detection Added

### What was added

`xprinterService.kt`-এ USB broadcast receiver add করা হয়েছে।

Relevant functions:
- `registerUsbDetachReceiverIfNeeded()`
- `handleUsbDetach(deviceName: String)`
- `handleUsbAttach(deviceName: String)`

### Why it was added

যখন USB cable physically unplug হয়, তখন app side-এ এটা immediately detect করা দরকার। আগে এই case-এ stale session object থেকে যেতে পারত।

### When this will work

এই detection কাজ করবে যখন:
- USB cable unplug হবে
- Android OS USB detach event দিবে
- USB device আবার attach হবে

### What happens on detach

- detach event detect হবে
- watchdog stop হবে
- stale printer session disconnect করার চেষ্টা হবে

### Existing function impact

Existing normal print flow-এর উপর direct impact নেই, কারণ এই receiver শুধু USB attach/detach event এ কাজ করে।

### Why existing behavior should remain safe

- Normal print path change করা হয়নি
- Connect success path change করা হয়নি
- Event-based logic শুধু failure/recovery case-এ active হয়

## 2. USB Watchdog Added

### What was added

USB printer-এর জন্য periodic health check add করা হয়েছে।

Relevant functions:
- `startUsbWatchdogIfNeeded(printerKey: String)`
- `stopUsbWatchdog(printerKey: String)`
- `stopAllUsbWatchdogs()`

### Why it was added

সব disconnect physical detach না। অনেক সময় cable attached থাকে কিন্তু connection internally dead হয়ে যায়। এই silent failure ধরার জন্য watchdog দরকার ছিল।

### Recheck interval

Watchdog প্রতি `10 seconds` পর পর connection recheck দেয়।

Relevant constant:
- `USB_WATCHDOG_INTERVAL_MS = 10000L`

### What the watchdog checks

1. USB device physically available কি না
2. Binder/service level connection still open কি না
3. Lost connection হলে reconnect possible কি না

### When this will work

এই logic কাজ করবে যখন:
- USB printer successful connect হয়েছে
- checkConnection success হয়েছে
- print success-এর পরে printer active আছে

### Existing function impact

Impact mostly XPrinter USB path-এ limited।

Potential concern:
- Repeated background check existing connection-এর উপর extra pressure দিবে কি না

Why this should not be a problem:
- interval aggressive না, 10 second
- শুধু XPrinter USB path-এ run হচ্ছে
- network printer path-এর জন্য watchdog start করা হয় না
- healthy connection-এর ক্ষেত্রে শুধু state check হয়, print payload logic touch হয় না

## 3. Automatic Reconnect Added

### What was added

Lost USB session detect হলে reconnect attempt logic active হবে।

Relevant function:
- `ensurePrinterConnected(printerKey: String)`

### Why it was added

আগে reconnect-related logic কিছুটা ছিল, কিন্তু physical device missing case filter করা ছিল না এবং watchdog-triggered recovery path ছিল না। এখন reconnect structured way-তে হচ্ছে।

### What this function does

1. USB printer হলে আগে physical device available কি না check করে
2. Available না থাকলে reconnect skip করে
3. Current linked state check করে
4. Already connected হলে success return করে
5. Connected না হলে fresh connect attempt দেয়
6. Success হলে watchdog ensure করে

### When this will work

- Silent session loss
- Idle অবস্থায় dead session
- Failed print-এর পরে retry phase

### Existing function impact

Existing `checkConnection()` এবং pending print retry path এখন `ensurePrinterConnected()` এর মাধ্যমে better recovery পায়।

### Why existing behavior should remain safe

- Device missing হলে blind reconnect try করা হচ্ছে না
- Existing connect API same রাখা হয়েছে
- Existing method signature change করা হয়নি

## 4. Print-Time USB Availability Check Added

### What was added

`printBitmap()`-এ print attempt-এর আগে USB device physically available কি না verify করা হচ্ছে।

Relevant function:
- `printBitmap(printerKey, printBmp, result)`

### Why it was added

Cable unplugged থাকলে direct write attempt করে fail করার বদলে আগে detect করা better। এতে controlled retry path-এ যাওয়া যায়।

### What happens now

- USB device unavailable হলে immediate write attempt হবে না
- job pending queue-তে যাবে
- result false return হবে

### Existing function impact

Existing print payload generation untouched।

### Why existing behavior should remain safe

- Device available থাকলে old print flow same
- Only unavailable USB case-এ behavior change

## 5. Better Service-Layer Connection State Tracking Added

### What was changed

`xprinter_service.kt`-এ connection state handling improve করা হয়েছে।

Relevant functions:
- `removePrinter(ip: String)`
- `disconnectCurrentPort(ip: String, callback: TaskCallback)`
- `disconnectAll(callback: TaskCallback)`
- `checkLinkedState(ip: String, execute: TaskCallback)`
- `writeDataByYouself(...)`

### Why it was added

App-level watchdog reliable হতে হলে service layer-এ `isConnected` accurate থাকতে হবে। আগে কিছু জায়গায় stale state থেকে যাওয়ার chance ছিল।

### Specific improvements

#### `removePrinter()`
- এখন remove করার সময় port close-ও করা হচ্ছে

Why:
- stale object remove হলেও underlying port খোলা থাকলে problem হতে পারে

#### `disconnectCurrentPort()`
- successful close-এর পরে printer registry থেকেও remove হচ্ছে

Why:
- stale printer entry future reconnect logic confuse না করুক

#### `disconnectAll()`
- network printer key ছাড়াও USB/Bluetooth key cover করা হয়েছে

Why:
- all disconnect এ USB printer বাদ পড়ে যাওয়ার chance ছিল

#### `checkLinkedState()`
- `Open()` call-based check বাদ দিয়ে actual `PortIsOpen()` based validation করা হয়েছে

Why:
- state check করতে গিয়ে unnecessarily reopen behavior avoid করা
- real open state পাওয়া

#### `writeDataByYouself()`
- write exception হলে `isConnected = false`
- write success হলে `isConnected = true`

Why:
- failed write-এর পরে connection dead state future logic জানুক

## Which Functions Will Work In Which Problem Scenario

## Scenario 1: USB cable physically unplugged

Working functions:
- `registerUsbDetachReceiverIfNeeded()`
- `handleUsbDetach()`
- `stopUsbWatchdog()`
- `disconnectCurrentPort()`

Expected result:
- physical loss detect হবে
- stale session clear হবে
- future reconnect blindভাবে চলবে না

## Scenario 2: USB cable connected but printer session dead

Working functions:
- `startUsbWatchdogIfNeeded()`
- `isConnect()`
- `checkLinkedState()`
- `ensurePrinterConnected()`

Expected result:
- watchdog lost session detect করবে
- reconnect attempt হবে

## Scenario 3: Print command failed

Working functions:
- `printBitmap()`
- `writeDataByYouself()`
- `enqueuePendingPrint()`
- `trySendPendingJob()`
- `ensurePrinterConnected()`

Expected result:
- print fail detect হবে
- job queue হবে
- retry phase-এ reconnect ensure করে resend চেষ্টা হবে

## Scenario 4: App-side manual disconnect

Working functions:
- `disconnect()`
- `stopUsbWatchdog()`
- `stopAllUsbWatchdogs()`
- `disconnectCurrentPort()`
- `disconnectAll()`

Expected result:
- manual disconnect-এর পরে old watchdog বা background reconnect loop চলবে না

## Will Existing Functions Break?

Short answer: normal expected flow-এ break হওয়ার কথা না, কারণ changes failure-path focused এবং XPrinter-specific।

## Why existing functions should not break

1. Existing method signatures change করা হয়নি
2. Existing print data generation logic change করা হয়নি
3. Existing successful connect path keep করা হয়েছে
4. New logic mostly event-driven বা failure-driven
5. Network printer flow intentionally untouched রাখা হয়েছে

## Where there is still some risk

নতুন logic add করলে কিছু realistic risk সবসময় থাকে।

### Risk 1: Aggressive reconnect in unstable USB environment

Why it could happen:
- USB port বারবার up/down হলে watchdog repeated reconnect attempt করতে পারে

Why impact should stay limited:
- reconnect শুধু XPrinter USB flow-এ
- device unavailable হলে reconnect skip করা হচ্ছে

### Risk 2: Path/deviceName mismatch

Why it could happen:
- library-returned printer path আর Android `deviceName` fully identical না হলে detach matching imperfect হতে পারে

Why impact should stay limited:
- fallback হিসেবে watchdog still আছে
- print-time availability check still আছে

### Risk 3: Pending queue repeated retries

Why it could happen:
- physical issue unresolved থাকলে queued jobs বারবার retry হতে পারে

Why impact should stay limited:
- existing queue architecture already ছিল
- নতুন change শুধু connection recovery improve করেছে

## Why Other Existing Functions Should Not Be Affected

### Network printer functions

Why not affected:
- watchdog USB-only
- USB device availability check USB-only
- detach receiver USB-only

### Bitmap generation / print payload functions

Why not affected:
- receipt render logic change করা হয়নি
- chunking logic change করা হয়নি
- print command bytes change করা হয়নি

### Other printer implementations

Why not affected:
- code changes only XPrinter module-এ
- shared global printer framework touch করা হয়নি

## What Was Intentionally Left Unchanged

- Existing print format generation
- Existing bitmap chunking
- Existing queue structure
- Existing connect method signatures
- Existing non-XPrinter printer flows

## Current Recheck Timing

- Watchdog recheck interval: `10 seconds`
- Constant location: [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L643)

## Verification Status

Code changes applied successfully।

Compile verification attempt করা হয়েছিল with:

```sh
GRADLE_USER_HOME=/tmp/xprintersdk-gradle-home sh gradlew compileDebugKotlin
```

Compile environment issue:
- Android SDK path configured ছিল না
- `ANDROID_HOME` / `local.properties` missing ছিল

So build failure environment-related, code logic verification-related না।

## Final Task Update Summary

এই task-এ XPrinter USB flow-এ automatic loss detection, 10-second watchdog recheck, structured reconnect, stale connection cleanup, এবং safer print retry support add করা হয়েছে। Existing normal print flow intentionally unchanged রাখা হয়েছে। New changes mainly failure recovery path-এ কাজ করবে, তাই normal connected scenario-তে behavioral impact minimal থাকার কথা।
