package com.beergo.authorization

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.beergo.MainActivity
import com.beergo.R

class LoginActivity : AppCompatActivity() {
    lateinit var logoText: TextView
    lateinit var termsofuse: TextView
    lateinit var login: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        )
            }
        }
        setContentView(R.layout.activity_login)
        logoText = findViewById(R.id.logoText)
        termsofuse = findViewById(R.id.termsofuse)
        login = findViewById(R.id.login)

        setLogoTextColors(logoText)
        setTermsTextColors(termsofuse, this)
        login.setOnClickListener {
            onLoginButtonClicked()
        }
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
    fun onLoginButtonClicked() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}