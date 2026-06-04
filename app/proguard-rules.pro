# Keep eSpeak NG JNI methods
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class com.example.etts.EspeakNGBridge { *; }
-keep class com.example.etts.BlindFriendlyTTSService { *; }
