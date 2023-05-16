package com.example.buynow_te_e_comerce_app.repo

import androidx.lifecycle.LiveData
import com.example.buynow_te_e_comerce_app.data.ProductDao
import com.example.buynow_te_e_comerce_app.data.ProductEntity

class ProductRoomRepo(private val productDao: ProductDao) {

    val allCartProducts: LiveData<List<ProductEntity>> = productDao.GetProduct()
    suspend fun InsertProduct(product: ProductEntity) {
        productDao.InsertProduct(product)
    }

    suspend fun delete(product: ProductEntity) {
        productDao.delete(product)
    }

    suspend fun update(product: ProductEntity) {
        productDao.update(product)
    }
}