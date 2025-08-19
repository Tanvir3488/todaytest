package com.tanvir3488.finaltest.core.Di

import com.tanvir3488.finaltest.data.repositoryImpl.PostRepositoryImpl
import com.tanvir3488.finaltest.domain.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/******

 **** Created By  TANVIR3488 AT 19/8/25 9:48 PM

 ******/

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBindings {

    @Binds
    abstract fun bindRepository(repositoryImpl: PostRepositoryImpl): PostRepository
}
