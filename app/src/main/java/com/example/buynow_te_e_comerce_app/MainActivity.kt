package com.example.buynow_te_e_comerce_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buynow_te_e_comerce_app.viewModels.ProductVM
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var cartViewModel: ProductVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cartViewModel = ViewModelProvider(this).get(ProductVM::class.java)
        cartViewModel.getAllProduct()

        GlobalScope.launch(Dispatchers.Main) {
            delay(800)
            cartViewModel.NewList.observe(this@MainActivity, Observer { list ->
                Log.i("list size", list.size.toString())

            })


        }
    }
}