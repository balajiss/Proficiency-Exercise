package com.balajiss.proficiencyexercise.network

import android.content.Context
import com.balajiss.proficiencyexercise.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val networkUtil: NetworkUtil,
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtil.isInternetAvailable()) {
            throw NetworkUtil.NoInternetException(context.getString(R.string.no_internet))
        }

        var response: Response = chain.proceed(chain.request())

        if (response.code() != HttpURLConnection.HTTP_OK) {
            when (response.code()) {
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> NetworkUtil.TimedOutException(
                    context.getString(
                        R.string.timeout
                    )
                )
                else -> IOException(context.getString(R.string.something_went_wrong))
            }
        }

        return response
    }
}