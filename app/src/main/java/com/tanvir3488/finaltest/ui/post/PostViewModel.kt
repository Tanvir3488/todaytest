package com.tanvir3488.finaltest.ui.post

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanvir3488.finaltest.core.Utils.NetworkResult
import com.tanvir3488.finaltest.data.dto.response.Post
import com.tanvir3488.finaltest.domain.GetPostUseCase
import com.tanvir3488.finaltest.domain.MakeNewPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/******

 **** Created By  TANVIR3488 AT 19/8/25 9:49 PM

 ******/


@HiltViewModel
class PostViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase, private val makeNewPostUseCase: MakeNewPostUseCase): ViewModel() {

    val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList = _postList.asStateFlow()




    init {
        loadPost()
        val dummyPost = Post(
            body = "This is a dummy post body for testing.",
            id = 1,
            title = "Hello World!",
            userId = 100
        )
        makePost(dummyPost)
    }


    fun loadPost(){
        viewModelScope.launch {
            getPostUseCase().collect {
                when (it){
                    is NetworkResult.OnError -> {

                    }
                    NetworkResult.OnLoading -> {

                    }
                    is NetworkResult.OnSuccess -> {
                        it.data?.let {
                            _postList.emit(it)
                        }

                    }
                }
            }
        }
    }


    fun makePost(post: Post){
        viewModelScope.launch {
            makeNewPostUseCase(post).collect {
                when (it){
                    is NetworkResult.OnError -> {
                        Log.e("OnError",it.errorMsg)
                    }
                    NetworkResult.OnLoading -> {

                    }
                    is NetworkResult.OnSuccess -> {
                        it.data?.let {
                            Log.e("newPost",it.toString())
                        }

                    }
                }
            }
        }
    }



}
