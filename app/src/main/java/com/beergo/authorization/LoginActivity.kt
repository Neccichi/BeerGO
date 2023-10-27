package com.beergo.authorization

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.beergo.R

class LoginActivity : AppCompatActivity() {
    lateinit var logoText: TextView
    lateinit var termsofuse: TextView
    lateinit var login: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        logoText = findViewById(R.id.logoText)
        termsofuse = findViewById(R.id.termsofuse)
        login = findViewById(R.id.login)

        setLogoTextColors(logoText)
        setTermsTextColors(termsofuse, this)
    }


    fun setLogoTextColors(logoText: TextView) {
        val logoTextContent = "Join BeerGO"
        val logoTextSpannable = SpannableString(logoTextContent)

        val whiteSpan = ForegroundColorSpan(Color.WHITE)
        logoTextSpannable.setSpan(whiteSpan, 0, "Join Beer".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val orangeSpan = ForegroundColorSpan(Color.parseColor("#f76a04"))
        logoTextSpannable.setSpan(orangeSpan, "Join Beer".length, logoTextContent.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        logoText.text = logoTextSpannable
    }
    fun setTermsTextColors(termsofuse: TextView, context: Context) {
        val termsTextContent = "By continuing, you agree to BeerGO’s Terms of\nService and Privacy Policy"
        val termsTextSpannable = SpannableString(termsTextContent)

        val customColorSpan = ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_color_login_edittext))
        termsTextSpannable.setSpan(customColorSpan, 0, "By continuing, you agree to BeerGO’s".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val whiteSpanTerms = ForegroundColorSpan(Color.WHITE)
        termsTextSpannable.setSpan(whiteSpanTerms, "By continuing, you agree to BeerGO’s".length, termsTextContent.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        termsofuse.text = termsTextSpannable
    }
}