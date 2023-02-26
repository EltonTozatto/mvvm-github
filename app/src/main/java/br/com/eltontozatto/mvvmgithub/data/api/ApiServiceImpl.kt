package br.com.eltontozatto.mvvmgithub.data.api

import br.com.eltontozatto.mvvmgithub.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl:ApiService {
    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://api.github.com./users/eltontozatto/followers?page=1&per_page=1000")
            .build()
            .getObjectListSingle(User::class.java)
    }

}