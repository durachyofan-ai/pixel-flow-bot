# Pixel Flow Bot prototype

This repository contains a starter Android app scaffold for an automation bot for the game Pixel Flow.

Important:
- This is a prototype and not a guaranteed final working bot for every level.
- Real gameplay automation requires testing on the target device and tuning the screen recognition logic.
- This project is meant to be finished and tested in Android Studio.

## Included in this prototype
- Android app module
- Accessibility Service for Pixel Flow
- Main UI with Start / Stop controls
- Placeholder solver logic for board parsing
- Screen-capture and automation hooks
- OpenCV dependency for future board detection

## Build instructions
1. Install Android Studio.
2. Open the project folder.
3. Let Gradle sync.
4. Build -> Build APK or Build Bundle.
5. Install the APK on a real Android device.

## Required manual setup on device
1. Install Pixel Flow.
2. Open the bot app.
3. In Settings -> Accessibility, enable the Pixel Flow Bot accessibility service.
4. Open Pixel Flow.
5. Press Start.

## Known limitations
- Level recognition is intentionally stubbed until the game is tested on-device.
- The real solver needs image calibration and per-level region detection.
- Additional logic is needed for:
  - board boundaries
  - tile colors
  - key tiles and locked paths
  - click positions for each action
  - queue / slot logic

## Suggested next steps
- Test on a real OnePlus Nord 5.
- Capture a few screenshots from Pixel Flow.
- Tune color threshold values and click coordinates.
- Replace the placeholder solver with full board graph logic.

## Notes
This project was created as a starting point for implementing a Pixel Flow automation bot. It is not a universal completed bot yet.
