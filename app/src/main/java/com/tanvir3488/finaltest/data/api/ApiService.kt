package com.tanvir3488.finaltest.data.api

import com.tanvir3488.finaltest.data.dto.response.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/******

 **** Created By  TANVIR3488 AT 19/8/25 7:56 PM

 ******/


interface ApiService {
    @GET("posts")
    suspend fun getAllPost(): List<Post>

    @POST("posts")
    suspend fun newPost(@Body post: Post): Post
}
