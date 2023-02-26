package br.com.eltontozatto.mvvmgithub.data.repository

import br.com.eltontozatto.mvvmgithub.data.api.ApiHelper
import br.com.eltontozatto.mvvmgithub.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }
}