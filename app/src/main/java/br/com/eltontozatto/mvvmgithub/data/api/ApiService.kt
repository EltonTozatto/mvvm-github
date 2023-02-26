package br.com.eltontozatto.mvvmgithub.data.api

import br.com.eltontozatto.mvvmgithub.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}