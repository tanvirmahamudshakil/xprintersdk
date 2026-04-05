# XPrinter USB Recovery Implementation

## Goal

XPrinter USB connection long time stable থাকার পরে হঠাৎ disconnect হলে existing healthy print flow না বদলে auto-detection এবং recovery improve করা।

## Scope

- Change শুধুমাত্র XPrinter Android implementation-এ করা হয়েছে
- Other printer brand/module touch করা হয়নি
- Existing normal connect + print flow intentionally unchanged রাখা হয়েছে
- New logic শুধুমাত্র USB loss detection, stale session cleanup, watchdog, এবং safer reconnect-এর জন্য add করা হয়েছে

## Files Changed

### [android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt)

Main XPrinter service facade-এ USB loss detection, watchdog, reconnect gating, এবং cleanup logic add করা হয়েছে।

Key changes:

- `initBinding()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L59)
  USB attach/detach receiver register করা হয়েছে।

- `disposeBinding()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L65)
  Watchdog jobs stop করা হয়েছে, registered receivers unregister করা হয়েছে।

- `disconnect()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L98)
  Explicit disconnect-এর সময় USB watchdog stop করা হয়েছে যাতে stale background recovery না চলে।

- `checkConnection()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L121)
  Successful connection state confirm হলে USB watchdog start করা হয়েছে।

- `connetUSB()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L177)
  USB connect success-এর পরে watchdog start করা হয়েছে, permission-based delayed connect success-এর পরও same logic apply করা হয়েছে।

- USB event detection block at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L282)
  `ACTION_USB_DEVICE_DETACHED` এবং `ACTION_USB_DEVICE_ATTACHED` handle করা হয়েছে।

- `handleUsbDetach()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L309)
  Physical detach detect হলে stale connection disconnect করা হচ্ছে এবং watchdog stop করা হচ্ছে।

- `startUsbWatchdogIfNeeded()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L342)
  USB printer-এর জন্য 10 second interval-এ health check run হচ্ছে।

- `printBitmap()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L459)
  Print দেয়ার আগে USB device available কি না check করা হচ্ছে। Device না থাকলে silent write attempt না করে pending queue-তে job রাখা হচ্ছে।

- `ensurePrinterConnected()` at [xprinterService.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/xprinterService.kt#L503)
  USB device physically available না থাকলে reconnect attempt skip করা হচ্ছে। Available থাকলে reconnect success-এর পরে watchdog restart করা হচ্ছে।

Why these changes were needed:

- USB cable physically খুলে গেলে old binder state stale থেকে যাচ্ছিল
- Long-running connection silently dead হলে immediate detect করা যাচ্ছিল না
- Reconnect logic ছিল, কিন্তু physical device missing case filter করা ছিল না
- Successful connect-এর পরে background health monitoring ছিল না

### [android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt)

Binder/service layer-এ stale printer state handling আরও reliable করা হয়েছে।

Key changes:

- `removePrinter()` at [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt#L52)
  Printer remove করার সময় underlying port/device close করা হচ্ছে।

- `disconnectCurrentPort()` at [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt#L165)
  Successful close-এর পরে printer registry থেকেও remove করা হচ্ছে।

- `disconnectAll()` at [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt#L189)
  শুধু network printer না, USB/Bluetooth key দিয়েও disconnect path cover করা হয়েছে।

- `checkLinkedState()` at [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt#L269)
  Old behavior ছিল `Open()` call করে state check করা। এখন actual port-open state `GetPortInfo().PortIsOpen()` দিয়ে verify করা হচ্ছে।

- `writeDataByYouself()` at [xprinter_service.kt](/Users/tanvir/Desktop/Android_Project/Yuma/Github/xprintersdk/android/src/main/kotlin/com/example/xprintersdk/xprinter/Service/xprinter_service.kt#L319)
  Write exception হলে `isConnected = false` set করা হচ্ছে; success হলে state true update হচ্ছে।

Why these changes were needed:

- Watchdog reliable করতে service layer-এ real connection state accurate হওয়া দরকার
- Stale printer object registry-তে থাকলে reconnect path misleading হয়ে যায়
- Failed write-এর পরে connection dead হলেও state stale true থাকতে পারত

## What Was Intentionally Not Changed

- Existing bitmap-to-bytes print payload generation
- Existing successful USB connect behavior
- Existing network printer connect logic
- Existing pending print queue structure
- Other printer implementations outside this XPrinter module

## Expected Behavior After Change

### Case 1: USB cable physically unplugged
- Android USB detach event detect হবে
- Stale printer session disconnect হবে
- Watchdog stop হবে
- Next print attempt immediate silent write করার বদলে unavailable state detect করবে

### Case 2: USB cable connected but session internally dead
- Watchdog periodic state check করবে
- Lost connection detect হলে reconnect attempt হবে
- Reconnect success হলে printing recover করার chance বাড়বে

### Case 3: Print command failure
- Failed write log হবে
- Pending queue-তে job যাবে
- Background retry path reconnect ensure করে resend চেষ্টা করবে

## Verification Status

Code changes applied হয়েছে।

Local compile attempt:
- Command: `GRADLE_USER_HOME=/tmp/xprintersdk-gradle-home sh gradlew compileDebugKotlin`
- Result: build environment blocked
- Reason: Android SDK path configured ছিল না (`ANDROID_HOME` / `local.properties` missing)

So, syntax/behavior full compile verification এখনো complete হয়নি environment limitation-এর কারণে।

## Recommended Manual Test Cases

1. XPrinter USB connect করে normal print run করা
2. Print success-এর পরে cable unplug করা
3. Verify detach log আসে কি না
4. Cable plug back করে reconnect path verify করা
5. Long idle রেখে watchdog reconnect path verify করা
6. Multiple print fail হলে duplicate receipt হচ্ছে কি না check করা
