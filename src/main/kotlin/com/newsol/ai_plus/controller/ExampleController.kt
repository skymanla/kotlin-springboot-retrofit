package com.newsol.ai_plus.controller

import com.newsol.ai_plus.dto.OpenApiInterface
import com.newsol.ai_plus.dto.response.PostDetailDto
import com.newsol.ai_plus.dto.response.RestResponseDto
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.extern.slf4j.Slf4j
import okhttp3.ResponseBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


@Tag(name = "Example API", description = "Swagger Test")
@RestController
@RequestMapping("/")
@Slf4j
class ExampleController(
    val restService: OpenApiInterface
) {
    val logger: Logger = LoggerFactory.getLogger(ExampleController::class.java)
    @Operation(summary = "문자열반복", description = "파라미터로 온 문자 2번 반복")
    @Parameter(name = "str", description = "반복할 문자열")
    @GetMapping("/return-str")
    fun returnStr(@RequestParam str: String) = "$str\n$str"

    @GetMapping("/example")
    fun example() = "예시 API"

    @Hidden
    @GetMapping("/ignore")
    fun ignore() = "무시되는 API"

    @Operation(summary = "retrofit test", description = "path 전달받기")
    @GetMapping("/posts/{idx}")
    fun retrofitTest1(@PathVariable(value = "idx") idx: Int): ResponseEntity<out Any> {
        // val url = "https://jsonplaceholder.typicode.com/todos/1"
        return try {
            val call = restService.getPostDetail(idx)
            val response = call.execute()
            ResponseEntity.ok().body(response.body())
        } catch (e: IOException) {
            logger.error(e.message)
            ResponseEntity.status(500).body(e.message)
        }
    }

    @GetMapping("/posts")
    fun getPostList(): ResponseEntity<RestResponseDto> {
        val call = restService.getPostAll()
        val response = call.execute()
        return ResponseEntity.ok().body(RestResponseDto(true, "", response.body(), ""))
    }
}