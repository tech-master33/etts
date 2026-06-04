# ETTS - Android eSpeak NG TTS Engine

A custom Android Text-to-Speech (TTS) engine built specifically for blind users, using the eSpeak NG speech synthesis engine.

## Features

✅ **Blind User Optimized**
- Fully compatible with Android TalkBack
- High-contrast UI
- Adjustable speech rate and pitch
- Keyboard-only navigation support

✅ **Technical Features**
- Supports Android 8.1 (API 27) and higher
- System-wide TTS engine (other apps can use it as default TTS)
- Native eSpeak NG integration via JNI
- Low latency synthesis
- Multi-language support (via eSpeak NG)

✅ **Architecture**
- Java/Kotlin application layer
- Native C/C++ layer for eSpeak NG
- JNI bridge for seamless integration
- ONNX Runtime ready (future)

## Project Structure

```
etts/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml          # Service declaration
│   │   ├── kotlin/com/example/etts/
│   │   │   ├── BlindFriendlyTTSService.kt  # Main TTS Service
│   │   │   ├── EspeakNGBridge.kt          # JNI Bridge
│   │   │   └── SettingsActivity.kt        # Settings UI
│   │   ├── cpp/
│   │   │   ├── CMakeLists.txt
│   │   │   └── espeakng_jni.c            # JNI Implementation
│   │   └── res/
│   │       ├── xml/tts_engine.xml
│   │       ├── values/strings.xml
│   │       ├── values/colors.xml
│   │       └── values/styles.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Prerequisites

- Android Studio 2021.1+
- Android NDK (for native compilation)
- Gradle 7.0+
- Java 11+
- Kotlin 1.9+

## Building

### Step 1: Clone and Setup

```bash
git clone https://github.com/tech-master33/etts.git
cd etts
```

### Step 2: Add eSpeak NG as Submodule

```bash
git submodule add https://github.com/espeak-ng/espeak-ng.git app/src/main/cpp/espeak-ng
```

### Step 3: Build with Gradle

```bash
./gradlew clean build
```

### Step 4: Install APK

```bash
./gradlew installDebug
```

## Configuration

Edit `app/build.gradle` to adjust:

- **minSdk**: Minimum Android API level (currently 27 = Android 8.1)
- **targetSdk**: Target Android API level (currently 35)
- **Supported ABIs**: `arm64-v8a`, `armeabi-v7a`

## Using as Default TTS Engine

1. Install the APK on an Android device
2. Go to **Settings → Accessibility → Text-to-speech output**
3. Select "eSpeak TTS Engine" from the list
4. Open the Settings app and configure speech rate, pitch, etc.
5. Other apps can now use your TTS engine as the default

## Testing

### Unit Tests (Kotlin)

```bash
./gradlew test
```

### Instrumented Tests (Android Device)

```bash
./gradlew connectedAndroidTest
```

### Manual Testing

1. Install the APK
2. Open any app that uses TTS (e.g., Google Reader, Chrome)
3. Enable TTS and select the eSpeak TTS Engine
4. Adjust speech rate and pitch in Settings

## Next Steps

- [ ] Integrate eSpeak NG native library
- [ ] Add more language support
- [ ] Implement voice selection UI
- [ ] Add haptic feedback for blind users
- [ ] Performance optimization
- [ ] CI/CD pipeline (GitHub Actions)
- [ ] Unit and integration tests

## Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -am 'Add feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a Pull Request

## License

This project is licensed under the GNU General Public License v3.0 - see the LICENSE file for details.

## References

- [eSpeak NG Documentation](https://github.com/espeak-ng/espeak-ng)
- [Android TextToSpeechService](https://developer.android.com/reference/android/speech/tts/TextToSpeechService)
- [Android NDK Guide](https://developer.android.com/ndk)
- [Android Accessibility Guide](https://developer.android.com/guide/topics/ui/accessibility)

## Support

For issues, feature requests, or questions:
- Open an issue on GitHub
- Contact: tech-master33 (GitHub)

---

**Made for blind users, by developers who care about accessibility.**
