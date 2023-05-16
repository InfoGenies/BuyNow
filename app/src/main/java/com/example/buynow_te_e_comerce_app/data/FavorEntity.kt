package com.example.buynow_te_e_comerce_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_items")
data class FavorEntity(
    @ColumnInfo(name = "Product_id") val productId: String = "",
    @ColumnInfo(name = "Product_Name") val productName: String = "",
    @ColumnInfo(name = "Product_Band") val productBrand: String = "",
    @ColumnInfo(name = "Product_Price") val productPrice: String = "",
    @ColumnInfo(name = "Product_Image") val productImage: String = "",
    @ColumnInfo(name = "Product_Desc") val productDes: String = "",
    @ColumnInfo(name = "Product_have") val productHave: Boolean = false,
    @ColumnInfo(name = "Product_Dicount") val productDisCount: String = "",
    @ColumnInfo(name = "Product_Rating") val productRating: String = "",
    @ColumnInfo(name = "Product_Category") val productCategory: String = "",
    @ColumnInfo(name = "Product_Contain") val Contain: Boolean = false
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
