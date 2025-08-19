package com.tanvir3488.finaltest.core.Utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

/******

 **** Created By  TANVIR3488 AT 19/8/25 10:08 PM

 ******/


inline fun <T> safeFlow(crossinline block: suspend () -> T): Flow<NetworkResult<T>> =
    flow {
        emit(NetworkResult.OnLoading)
        try {
            emit(NetworkResult.OnSuccess(block()))
        } catch (e: Exception) {
            emit(NetworkResult.OnError(e.localizedMessage ?: "Unexpected error"))
        }
    }.flowOn(Dispatchers.IO)
