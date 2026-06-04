#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdlib.h>

#define LOG_TAG "EspeakNG-JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

// TODO: When eSpeak NG is integrated, uncomment and add proper includes
// #include <espeak-ng/speak_lib.h>

static int current_sample_rate = 44100;
static int current_speech_rate = 175;  // Default 175 WPM
static int current_pitch = 100;        // Default pitch (100 = normal)

/**
 * Initialize eSpeak NG TTS engine
 * Returns sample rate on success, negative value on error
 */
JNIEXPORT jint JNICALL
Java_com_example_etts_EspeakNGBridge_nativeInitialize(JNIEnv *env, jobject thiz, jstring data_path) {
    const char *path = (*env)->GetStringUTFChars(env, data_path, 0);
    
    LOGI("Initializing eSpeak NG with path: %s", path);
    
    // TODO: Add actual eSpeak NG initialization
    // int result = espeak_Initialize(AUDIO_OUTPUT_SYNCHRONOUS, BUFFER_SIZE, path, 0);
    // if (result > 0) {
    //     current_sample_rate = result;
    //     LOGI("eSpeak NG initialized. Sample rate: %d", current_sample_rate);
    // }
    
    int result = current_sample_rate;  // Stub: return sample rate
    
    (*env)->ReleaseStringUTFChars(env, data_path, path);
    return result;
}

/**
 * Check if language is available
 */
JNIEXPORT jint JNICALL
Java_com_example_etts_EspeakNGBridge_nativeIsLanguageAvailable(JNIEnv *env, jobject thiz, jstring language) {
    const char *lang = (*env)->GetStringUTFChars(env, language, 0);
    
    LOGD("Checking language availability: %s", lang);
    
    // TODO: Add actual eSpeak NG language check
    // int result = espeak_SetVoiceByProperties(NULL);
    
    jint result = 1;  // Stub: language available
    
    (*env)->ReleaseStringUTFChars(env, language, lang);
    return result;
}

/**
 * Load language for synthesis
 */
JNIEXPORT void JNICALL
Java_com_example_etts_EspeakNGBridge_nativeLoadLanguage(JNIEnv *env, jobject thiz, jstring language) {
    const char *lang = (*env)->GetStringUTFChars(env, language, 0);
    
    LOGI("Loading language: %s", lang);
    
    // TODO: Add actual eSpeak NG language loading
    // espeak_SetVoiceByName(lang);
    
    (*env)->ReleaseStringUTFChars(env, language, lang);
}

/**
 * Set speech rate in words per minute
 */
JNIEXPORT void JNICALL
Java_com_example_etts_EspeakNGBridge_nativeSetSpeechRate(JNIEnv *env, jobject thiz, jint wpm) {
    LOGI("Setting speech rate: %d WPM", wpm);
    current_speech_rate = wpm;
    
    // TODO: Add actual eSpeak NG speech rate setting
    // espeak_SetParameter(espeakRATE, wpm, 0);
}

/**
 * Set pitch
 */
JNIEXPORT void JNICALL
Java_com_example_etts_EspeakNGBridge_nativeSetPitch(JNIEnv *env, jobject thiz, jint pitch) {
    LOGI("Setting pitch: %d", pitch);
    current_pitch = pitch;
    
    // TODO: Add actual eSpeak NG pitch setting
    // espeak_SetParameter(espeakPITCH, pitch, 0);
}

/**
 * Synthesize text to speech
 */
JNIEXPORT jbyteArray JNICALL
Java_com_example_etts_EspeakNGBridge_nativeSynthesize(JNIEnv *env, jobject thiz, jstring text) {
    const char *text_str = (*env)->GetStringUTFChars(env, text, 0);
    
    LOGI("Synthesizing text: %s", text_str);
    
    // TODO: Add actual eSpeak NG synthesis
    // Generate audio buffer and return as ByteArray
    
    // Stub: Create a small audio buffer (100ms of silence at 44100Hz, 16-bit mono)
    int audio_size = 44100 / 10 * 2;  // 100ms * 2 bytes per sample
    jbyte *audio_data = (jbyte *)malloc(audio_size);
    memset(audio_data, 0, audio_size);
    
    jbyteArray result = (*env)->NewByteArray(env, audio_size);
    (*env)->SetByteArrayRegion(env, result, 0, audio_size, audio_data);
    
    free(audio_data);
    (*env)->ReleaseStringUTFChars(env, text, text_str);
    
    return result;
}

/**
 * Stop synthesis
 */
JNIEXPORT void JNICALL
Java_com_example_etts_EspeakNGBridge_nativeStop(JNIEnv *env, jobject thiz) {
    LOGI("Stopping synthesis");
    
    // TODO: Add actual eSpeak NG stop
    // espeak_Cancel();
}

/**
 * Shutdown and cleanup
 */
JNIEXPORT void JNICALL
Java_com_example_etts_EspeakNGBridge_nativeShutdown(JNIEnv *env, jobject thiz) {
    LOGI("Shutting down eSpeak NG");
    
    // TODO: Add actual eSpeak NG shutdown
    // espeak_Terminate();
}
