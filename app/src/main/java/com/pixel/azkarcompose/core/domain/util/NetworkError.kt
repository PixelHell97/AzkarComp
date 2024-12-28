package com.pixel.azkarcompose.core.domain.util

import com.pixel.azkarcompose.core.domain.util.Error

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}
