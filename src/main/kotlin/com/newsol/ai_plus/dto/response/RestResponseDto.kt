package com.newsol.ai_plus.dto.response

class RestResponseDto(
    val success: Boolean = true,
    var message: String,
    var data: Any? = null,
    var code: Any? = 0
)