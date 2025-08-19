package com.tanvir3488.finaltest.core.Utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/******

 **** Created By  TANVIR3488 AT 19/8/25 8:06 PM

 ******/


class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        Log.e("Interceptor",response.toString())
        return response
    }
}
