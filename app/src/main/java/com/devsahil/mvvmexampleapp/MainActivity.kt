package com.devsahil.mvvmexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devsahil.mvvmexampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = QuoteDataBase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is testing", "Testing")
            mainViewModel.insertQuote(quote)
        }

    }
}