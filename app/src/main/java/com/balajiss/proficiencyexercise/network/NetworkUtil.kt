package com.balajiss.proficiencyexercise.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtil @Inject constructor(private val context: Context) {

    class NoInternetException(message: String) : Exception(message) {
        companion object
    }

    class TimedOutException(message: String) : Exception(message) {
        companion object
    }

    fun isInternetAvailable(): Boolean {
        var isInternetAvailable: Boolean
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectionManager.activeNetwork ?: return false
            val activeNetwork =
                connectionManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isInternetAvailable = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectionManager.activeNetworkInfo.let {
                isInternetAvailable = when (it.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return isInternetAvailable
    }
}