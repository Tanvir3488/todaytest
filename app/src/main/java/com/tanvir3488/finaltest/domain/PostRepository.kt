package com.tanvir3488.finaltest.domain

import com.tanvir3488.finaltest.core.Utils.NetworkResult
import com.tanvir3488.finaltest.data.dto.response.Post
import kotlinx.coroutines.flow.Flow

/******

 **** Created By  TANVIR3488 AT 19/8/25 9:29 PM

 ******/


interface PostRepository {
     fun getPost(): Flow<NetworkResult<List<Post>>>
     fun makeNewPost(post: Post): Flow<NetworkResult<Post>>
}
