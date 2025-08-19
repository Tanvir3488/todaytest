package com.tanvir3488.finaltest.domain

import com.tanvir3488.finaltest.core.Utils.NetworkResult
import com.tanvir3488.finaltest.data.dto.response.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/******

 **** Created By  TANVIR3488 AT 19/8/25 9:41 PM

 ******/


class MakeNewPostUseCase @Inject constructor(val postRepository: PostRepository) {
      operator fun invoke(post: Post): Flow<NetworkResult<Post>>{
        return postRepository.makeNewPost(post)
    }
}
