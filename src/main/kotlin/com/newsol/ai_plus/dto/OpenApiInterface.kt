package com.newsol.ai_plus.dto

import com.newsol.ai_plus.dto.response.PostDetailDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface OpenApiInterface {
    @GET
    fun getUrlInfo(@Url url: String)

    @GET("posts/{idx}")
    fun getPostDetail(@Path("idx") idx: Int): Call<PostDetailDto>

    @GET("posts")
    fun getPostAll(): Call<List<PostDetailDto>>
}