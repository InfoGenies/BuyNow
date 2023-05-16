package com.example.buynow_te_e_comerce_app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.buynow_te_e_comerce_app.data.ProductEntity
import com.example.buynow_te_e_comerce_app.data.RoomDB
import com.example.buynow_te_e_comerce_app.repo.ProductRoomRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProdutRoomViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRoomRepo
    val allproducts: LiveData<List<ProductEntity>>

    init {
        val productDao = RoomDB.getInstance(application).productDao()
        repository = ProductRoomRepo(productDao)
        allproducts = repository.allCartProducts
    }
    fun insert(product: ProductEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.InsertProduct(product)
    }

    fun deleteCart(product: ProductEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }

    fun updateCart(product: ProductEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(product)
    }

}