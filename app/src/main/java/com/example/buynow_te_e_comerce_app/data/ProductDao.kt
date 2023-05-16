package com.example.buynow_te_e_comerce_app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ProductDao {
    @Insert(onConflict = REPLACE)
    suspend fun InsertProduct(product: ProductEntity)

    @Query("SELECT * FROM product_items order by id desc")
    fun GetProduct(): LiveData<List<ProductEntity>>

    @Delete
    suspend fun delete(product: ProductEntity)

    @Update
    suspend fun update(vararg product: ProductEntity)

}