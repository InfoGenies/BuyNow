package com.example.buynow_te_e_comerce_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_items")
data class ProductEntity(
    @ColumnInfo(name = "Product_Name") var name: String,
    @ColumnInfo(name = "Product_Quantity") var qua: Int,
    @ColumnInfo(name = "Product_Price") var price: Int,
    @ColumnInfo(name = "Product_ID") var pId: String,
    @ColumnInfo(name = "Product_Image") var Image: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}