package com.example.etts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Button
import android.util.Log

/**
 * Settings Activity for TTS Engine configuration
 * Accessible to blind users via TalkBack
 */
class SettingsActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "SettingsActivity"
    }
    
    private lateinit var statusText: TextView
    private lateinit var speechRateSeekBar: SeekBar
    private lateinit var pitchSeekBar: SeekBar
    private lateinit var testButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create simple UI layout programmatically
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        
        // Status text
        statusText = TextView(this).apply {
            text = "TTS Engine Status: Ready"
            textSize = 18f
            setPadding(0, 16, 0, 16)
            contentDescription = "TTS Engine Status: Ready"
        }
        layout.addView(statusText)
        
        // Speech Rate Label
        layout.addView(TextView(this).apply {
            text = "Speech Rate (0.5x - 2.0x)"
            textSize = 16f
            setPadding(0, 16, 0, 8)
        })
        
        // Speech Rate SeekBar
        speechRateSeekBar = SeekBar(this).apply {
            min = 50
            max = 200
            progress = 100 // 1.0x = 100
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val rate = progress / 100f
                    statusText.text = "Speech Rate: ${String.format("%.1f", rate)}x"
                    statusText.contentDescription = "Speech Rate: ${String.format("%.1f", rate)}x"
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            contentDescription = "Speech Rate Adjustment"
        }
        layout.addView(speechRateSeekBar)
        
        // Pitch Label
        layout.addView(TextView(this).apply {
            text = "Pitch (0.0 - 2.0)"
            textSize = 16f
            setPadding(0, 24, 0, 8)
        })
        
        // Pitch SeekBar
        pitchSeekBar = SeekBar(this).apply {
            min = 0
            max = 200
            progress = 100 // 1.0 = 100
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val pitch = progress / 100f
                    statusText.text = "Pitch: ${String.format("%.1f", pitch)}"
                    statusText.contentDescription = "Pitch: ${String.format("%.1f", pitch)}"
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            contentDescription = "Pitch Adjustment"
        }
        layout.addView(pitchSeekBar)
        
        // Test Button
        testButton = Button(this).apply {
            text = "Test TTS"
            textSize = 16f
            setPadding(0, 32, 0, 0)
            setOnClickListener {
                Log.d(TAG, "Test button clicked")
                statusText.text = "Testing TTS Engine..."
                statusText.contentDescription = "Testing TTS Engine"
            }
            contentDescription = "Test Button to verify TTS Engine"
        }
        layout.addView(testButton)
        
        setContentView(layout)
        
        Log.d(TAG, "SettingsActivity created")
    }
}
