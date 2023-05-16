package com.example.buynow_te_e_comerce_app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val productId: String= "",
    val productName: String = "",
    val productBrand:String = "",
    val productPrice: String = "",
    val productImage: String = "",
    val productDes:String = "",
    val productHave:Boolean = false,
    val productDisCount:String = "",
    val productRating: String = "",
    val productCategory:String = "",
    ): Parcelable{



}
