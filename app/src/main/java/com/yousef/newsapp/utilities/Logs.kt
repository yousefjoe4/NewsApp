package com.yousef.newsapp.utilities

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics


object Logs {

    fun logEvent(context: Context, message: String){
        val bundle = Bundle()
        bundle.putString("message", message)
        FirebaseAnalytics.getInstance(context).logEvent("Event", bundle)
    }
}