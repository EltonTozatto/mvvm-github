package br.com.eltontozatto.mvvmgithub.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.eltontozatto.mvvmgithub.data.api.ApiHelper
import br.com.eltontozatto.mvvmgithub.data.repository.MainRepository
import br.com.eltontozatto.mvvmgithub.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("Classe não encontrada")
    }
}