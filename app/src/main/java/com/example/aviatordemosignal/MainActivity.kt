package com.example.aviatordemosignal

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : Activity() {
    private lateinit var signal: TextView
    private lateinit var detail: TextView
    private lateinit var history: TextView
    private val records = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(16,16,16)
        window.navigationBarColor = Color.rgb(16,16,16)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 36, 24, 24)
            setBackgroundColor(Color.rgb(16,16,16))
        }
        fun text(value: String, size: Float, color: Int, bold: Boolean = false) =
            TextView(this).apply {
                text = value; textSize = size; setTextColor(color)
                gravity = Gravity.CENTER
                if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(8, 12, 8, 12)
            }

        root.addView(text("✈ AVIATOR DEMO SIGNAL", 25f, Color.WHITE, true))
        root.addView(text("শুধু অনুশীলনের জন্য • বাস্তব ফল অনুমান করে না", 14f, Color.LTGRAY))
        signal = text("সিগন্যাল পেতে নিচের বোতাম চাপুন", 24f, Color.WHITE, true)
        root.addView(signal)
        detail = text("এটি র‍্যান্ডম ডেমো আউটপুট; জুয়ার পরামর্শ নয়।", 14f, Color.LTGRAY)
        root.addView(detail)

        val button = Button(this).apply {
            text = "DEMO SIGNAL তৈরি করুন"
            setOnClickListener { makeSignal() }
        }
        root.addView(button, LinearLayout.LayoutParams(-1, -2).apply { topMargin = 18 })

        root.addView(text("সাম্প্রতিক ডেমো হিস্ট্রি", 18f, Color.WHITE, true))
        history = text("এখনো কোনো সিগন্যাল নেই", 14f, Color.LTGRAY)
        root.addView(history)

        root.addView(text(
            "সতর্কতা: Aviator-এর পরবর্তী ক্র্যাশ পয়েন্ট কোনো অ্যাপ নির্ভরযোগ্যভাবে আগে থেকে জানতে পারে না। টাকা বাজি ধরার সিদ্ধান্তে এই ডেমো ব্যবহার করবেন না।",
            13f, Color.rgb(255,190,90)
        ))
        setContentView(root)
    }

    private fun makeSignal() {
        val options = listOf("1.20x – 1.50x", "1.50x – 2.00x", "2.00x – 3.00x", "3.00x+")
        val chosen = options[Random.nextInt(options.size)]
        val stamp = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())
        signal.text = "ডেমো সিগন্যাল: $chosen"
        signal.setTextColor(Color.rgb(0, 220, 110))
        detail.text = "সময়: $stamp  |  র‍্যান্ডম নমুনা, বাস্তব পূর্বাভাস নয়"
        records.add(0, "$stamp  •  $chosen")
        if (records.size > 8) records.removeAt(records.lastIndex)
        history.text = records.joinToString("\n")
    }
}
