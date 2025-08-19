package com.tanvir3488.finaltest.data.repositoryImpl

import com.tanvir3488.finaltest.core.Utils.NetworkResult
import com.tanvir3488.finaltest.core.Utils.safeFlow
import com.tanvir3488.finaltest.data.api.ApiService
import com.tanvir3488.finaltest.data.dto.response.Post
import com.tanvir3488.finaltest.domain.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/******

 **** Created By  TANVIR3488 AT 19/8/25 9:45 PM

 ******/


class PostRepositoryImpl @Inject constructor(val apiService: ApiService): PostRepository {
    override fun getPost(): Flow<NetworkResult<List<Post>>> {
       return safeFlow { apiService.getAllPost() }
    }

    override fun makeNewPost(post: Post): Flow<NetworkResult<Post>> {
        return safeFlow { apiService.newPost(post) }
    }


}
