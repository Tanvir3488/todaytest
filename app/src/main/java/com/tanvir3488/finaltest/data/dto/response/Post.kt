package com.tanvir3488.finaltest.data.dto.response


import java.io.Serializable


data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
):Serializable
