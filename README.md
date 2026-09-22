# Pixel Flow Bot

This project is a clean, honest prototype for creating an automation bot for Pixel Flow (`com.loomgames.pixelflow`).

Important:
- This is not a guaranteed final bot that will solve every level.
- The game must be tested on the target Android device to tune detection and input timings.
- The project is intended as a foundation for a real device-based automation build.

What is included:
- Android app shell
- UI for Start / Stop
- Accessibility Service hook for Pixel Flow
- Board-state model and solver skeleton
- GitHub Actions workflow for building a debug APK

What is still missing for a working game bot:
- exact screen calibration for the Pixel Flow UI
- color detection for each tile and pig
- actual coordinate mapping for clicks and drag logic
- board parser and move-planning algorithm
- real device testing and tuning

This repository is intentionally reset to a clean, transparent starting point. It is a proper prototype and not a false claim of a completed bot.
