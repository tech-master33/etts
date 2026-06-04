package com.example.etts

import android.content.Context
import android.util.Log

/**
 * JNI Bridge for eSpeak NG native library
 * Handles all native speech synthesis operations
 */
class EspeakNGBridge(private val context: Context) {
    
    companion object {
        private const val TAG = "EspeakNGBridge"
        init {
            try {
                System.loadLibrary("espeakng-jni")
                Log.d(TAG, "eSpeak NG JNI library loaded successfully")
            } catch (e: UnsatisfiedLinkError) {
                Log.e(TAG, "Failed to load eSpeak NG JNI library", e)
            }
        }
    }
    
    /**
     * Initialize eSpeak NG with data path
     * @return Sample rate or error code
     */
    fun initialize(): Int {
        val espeak_data_path = "${context.filesDir.absolutePath}/espeak-data"
        Log.d(TAG, "Initializing with data path: $espeak_data_path")
        
        val result = nativeInitialize(espeak_data_path)
        Log.d(TAG, "Initialization result: $result")
        return result
    }
    
    /**
     * Check if language is available
     */
    fun isLanguageAvailable(language: String): Boolean {
        return nativeIsLanguageAvailable(language) != 0
    }
    
    /**
     * Load language for synthesis
     */
    fun loadLanguage(language: String) {
        Log.d(TAG, "Loading language: $language")
        nativeLoadLanguage(language)
    }
    
    /**
     * Set speech rate (0.5 - 2.0, where 1.0 is normal)
     */
    fun setSpeechRate(rate: Float) {
        // Convert to words per minute (80-500 wpm)
        // Default is ~175 wpm
        val wpm = (175 * rate).toInt().coerceIn(80, 500)
        Log.d(TAG, "Setting speech rate: $rate -> $wpm wpm")
        nativeSetSpeechRate(wpm)
    }
    
    /**
     * Set pitch (0.0 - 2.0, where 1.0 is normal)
     */
    fun setPitch(pitch: Float) {
        // Adjust pitch (0-200, where 100 is normal)
        val pitchValue = (pitch * 100).toInt().coerceIn(0, 200)
        Log.d(TAG, "Setting pitch: $pitch -> $pitchValue")
        nativeSetPitch(pitchValue)
    }
    
    /**
     * Synthesize text to speech
     * @return ByteArray of PCM audio data or null on error
     */
    fun synthesize(text: String): ByteArray? {
        Log.d(TAG, "Synthesizing text: length=${text.length}")
        return nativeSynthesize(text)
    }
    
    /**
     * Stop current synthesis
     */
    fun stop() {
        Log.d(TAG, "Stopping synthesis")
        nativeStop()
    }
    
    /**
     * Shutdown eSpeak NG and clean up resources
     */
    fun shutdown() {
        Log.d(TAG, "Shutting down eSpeak NG")
        nativeShutdown()
    }
    
    // ==== JNI Method Declarations ====
    external fun nativeInitialize(dataPath: String): Int
    external fun nativeIsLanguageAvailable(language: String): Int
    external fun nativeLoadLanguage(language: String)
    external fun nativeSetSpeechRate(wpm: Int)
    external fun nativeSetPitch(pitch: Int)
    external fun nativeSynthesize(text: String): ByteArray?
    external fun nativeStop()
    external fun nativeShutdown()
}
