package com.path_studio.nike.ui.main.userAccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.UserEntity

class UserViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun insertUserData(userData: UserEntity): LiveData<String> = nikeRepository.insertUserData(userData)
    fun updateUserLogin(email: String, password: String, isLogin: Int): LiveData<String> =
        nikeRepository.updateUserLogin(email, password, isLogin)
    fun updateUserLogin(email: String, isLogin: Int): LiveData<String> =
        nikeRepository.updateUserLogin(email, isLogin)
    fun getUserData(email: String): LiveData<UserEntity> =
        nikeRepository.getUserData(email)
}