package com.example.ayushjumpingmindsassignment

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class InternetCheck {       // code to check internet connection
    companion object{
       fun isInternetAvailable(context: Context) : Boolean {
           (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
       return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
           NetworkCapabilities.NET_CAPABILITY_INTERNET
       )?:false
   }else{
       (@Suppress("Deprecation")
       return this.activeNetworkInfo?.isConnected ?: false)
   }
           }
       }
    }
}