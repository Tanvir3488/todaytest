package com.tanvir3488.finaltest.core.Utils

/******

 **** Created By  TANVIR3488 AT 19/8/25 10:06 PM

 ******/


sealed class NetworkResult<out T> {
    data object OnLoading : NetworkResult<Nothing>()
    data class OnError(val errorMsg: String) : NetworkResult<Nothing>()
    data class OnSuccess<T>(val data: T?) : NetworkResult<T>()
}
