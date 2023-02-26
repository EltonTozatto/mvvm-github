package br.com.eltontozatto.mvvmgithub.ui.main.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.eltontozatto.mvvmgithub.R
import br.com.eltontozatto.mvvmgithub.data.api.ApiHelper
import br.com.eltontozatto.mvvmgithub.data.api.ApiServiceImpl
import br.com.eltontozatto.mvvmgithub.data.model.User
import br.com.eltontozatto.mvvmgithub.data.repository.MainRepository
import br.com.eltontozatto.mvvmgithub.ui.base.ViewModelFactory
import br.com.eltontozatto.mvvmgithub.ui.main.adapter.MainAdapter
import br.com.eltontozatto.mvvmgithub.ui.main.viewmodel.MainViewModel
import br.com.eltontozatto.mvvmgithub.ui.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObserver()
        setupViewModel()
    }

    private fun setupViewModel() {
        val mainRepository = MainRepository(ApiHelper(ApiServiceImpl()))
        val factory = ViewModelFactory(mainRepository)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun setupObserver() {
        val progressBar = findViewById<ProgressBar>(R.id.pbCarregando)
        val recyclerView = findViewById<RecyclerView>(R.id.rvUsers)
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}