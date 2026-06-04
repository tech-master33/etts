package com.example.etts

import android.speech.tts.TextToSpeechService
import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.util.Log

/**
 * Android TTS Engine Service for blind users
 * Provides system-wide text-to-speech using eSpeak NG
 */
class BlindFriendlyTTSService : TextToSpeechService() {
    
    companion object {
        private const val TAG = "BlindTTS"
    }
    
    private lateinit var espeakBridge: EspeakNGBridge
    
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")
        espeakBridge = EspeakNGBridge(this)
        espeakBridge.initialize()
    }
    
    override fun onIsLanguageAvailable(lang: String?, country: String?, variant: String?): Int {
        val language = lang ?: "eng"
        Log.d(TAG, "Checking language availability: $language")
        
        return if (espeakBridge.isLanguageAvailable(language)) {
            LANG_COUNTRY_AVAILABLE
        } else {
            LANG_NOT_SUPPORTED
        }
    }
    
    override fun onGetLanguage(): Array<String> {
        // Return default language [lang, country, variant]
        return arrayOf("eng", "USA", "")
    }
    
    override fun onLoadLanguage(lang: String?, country: String?, variant: String?) {
        val language = lang ?: "eng"
        Log.d(TAG, "Loading language: $language-$country")
        espeakBridge.loadLanguage(language)
    }
    
    override fun onStop() {
        Log.d(TAG, "Stop requested")
        espeakBridge.stop()
    }
    
    override fun onSynthesizeText(request: SynthesisRequest, callback: SynthesisCallback) {
        val text = request.charSequenceText.toString()
        val speechRate = request.speechRate
        val pitch = request.pitch
        
        Log.d(TAG, "Synthesizing: \"$text\" (rate=$speechRate, pitch=$pitch)")
        
        try {
            // Set synthesis parameters
            espeakBridge.setSpeechRate(speechRate)
            espeakBridge.setPitch(pitch)
            
            // Synthesize and get audio
            val audioData = espeakBridge.synthesize(text)
            
            if (audioData != null && audioData.isNotEmpty()) {
                // Start callback with audio format: 44100 Hz, 16-bit, mono
                callback.start(44100, 16, 1)
                callback.audioAvailable(audioData, 0, audioData.size)
                callback.done()
                Log.d(TAG, "Synthesis completed. Audio size: ${audioData.size} bytes")
            } else {
                Log.e(TAG, "No audio data generated")
                callback.error()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Synthesis error", e)
            callback.error()
        }
    }
    
    override fun onDestroy() {
        Log.d(TAG, "Service destroyed")
        espeakBridge.shutdown()
        super.onDestroy()
    }
}
