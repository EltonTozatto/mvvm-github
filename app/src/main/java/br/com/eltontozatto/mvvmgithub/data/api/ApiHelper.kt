package br.com.eltontozatto.mvvmgithub.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getUsers() = apiService.getUsers()
}