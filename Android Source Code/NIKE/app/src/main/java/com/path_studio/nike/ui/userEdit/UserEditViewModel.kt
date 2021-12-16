package com.path_studio.nike.ui.userEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.UserEntity

class UserEditViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun updateUserData(userData: UserEntity): LiveData<String> = nikeRepository.updateUserData(userData)
}