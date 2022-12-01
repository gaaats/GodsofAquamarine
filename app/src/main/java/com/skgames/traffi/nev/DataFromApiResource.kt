package com.skgames.traffi.nev


sealed class DataFromApiResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : DataFromApiResource<T>(data)
    class Loading<T>(data: T? = null) : DataFromApiResource<T>(data)
    class Error<T>(message: String, data: T? = null) : DataFromApiResource<T>(data, message)
}