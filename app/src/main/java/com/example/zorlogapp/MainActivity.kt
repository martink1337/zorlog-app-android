package com.example.zorlogapp

import android.os.Build
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Enable DOM storage (localStorage, sessionStorage)
        webView.settings.domStorageEnabled = true

        // Enable cookies
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true)
        }

        // Set WebViewClient to stay inside the app when navigating links
        webView.webViewClient = WebViewClient()

        // Optional: Set WebChromeClient to catch JS console messages (debugging)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean {
                android.util.Log.d("WebView", consoleMessage?.message() ?: "")
                return true
            }
        }

        // Load your website URL
        webView.loadUrl("https://zorlog.run.place")

        // Set the WebView as the activity content
        setContentView(webView)
    }
}
