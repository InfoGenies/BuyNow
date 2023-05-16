package com.example.buynow_te_e_comerce_app.repo

import android.util.Log
import com.example.buynow_te_e_comerce_app.models.Categorie
import com.example.buynow_te_e_comerce_app.models.CoverProduct
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.utils.Constante
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductRepo {
    private val firebaseFirestore: FirebaseFirestore

    init {
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    suspend fun getProductsFirestore(): ArrayList<Product> {
        var list: ArrayList<Product> = ArrayList()
        try {
            list = firebaseFirestore.collection(Constante.Product_TABEL).get()
                .await().documents.mapNotNull { snapShot ->
                    snapShot.toObject(Product::class.java)
                } as ArrayList<Product>
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
        }
        return list
    }

    suspend fun getCoverProductsFirestore(): ArrayList<CoverProduct> {
        var listCoverProduct: ArrayList<CoverProduct> = ArrayList()
        try {
            listCoverProduct = firebaseFirestore.collection(Constante.Product_TABEL).get()
                .await().documents.mapNotNull { snapShot ->
                    snapShot.toObject(CoverProduct::class.java)
                } as ArrayList<CoverProduct>
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
        }
        return listCoverProduct
    }

    suspend fun getSaleFirestore(): ArrayList<Product> {
        var listSale: ArrayList<Product> = ArrayList()
        try {
            listSale = firebaseFirestore.collection(Constante.Sale_TABEL).get()
                .await().documents.mapNotNull { snapShot ->
                    snapShot.toObject(Product::class.java)
                } as ArrayList<Product>
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
        }
        return listSale
    }

    suspend fun getCategorieFirestore(): ArrayList<Categorie> {
        var listCat: ArrayList<Categorie> = ArrayList()
        try {
            listCat = firebaseFirestore.collection(Constante.Categorie_TABEL).get()
                .await().documents.mapNotNull { snapShot ->
                    snapShot.toObject(Categorie::class.java)
                } as ArrayList<Categorie>
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
        }
        return listCat
    }

     suspend fun getListeFiltring(name: String): ArrayList<Product> {
        var listProd: ArrayList<Product> = ArrayList()
        try {
            listProd = firebaseFirestore.collection(Constante.Product_TABEL)
                .whereEqualTo("productCategory", name)
                .get()
                .await().documents.mapNotNull { snapShot ->
                    snapShot.toObject(Product::class.java)
                } as ArrayList<Product>
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
        }

        return listProd
    }

}


/*
    suspend fun getAllProduct(): ArrayList<Product> {

        var list: ArrayList<Product> = ArrayList()
        firebaseFirestore.collection(Constante.Product_TABEL)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val data = document.toObject(Product::class.java)
                    println("le nom de produit est ${data.productName}")
                    list.add(data)

                }
                Log.d("main produit", "la taille est ${list.size.toString()}")
            }
            .addOnFailureListener { exception ->
                Log.w("main", "Error getting documents.", exception)
            }

        Log.d("main cat", "la taille est ${list.size.toString()}")
        return list

    }
*/

/*val resultList =   firebaseFirestore.collection(Constante.Product_TABEL)
    .get().await()
for (document in resultList) {
    val productId = document.getString("productId")
    val productName = document.getString("productName")
    val productPrice = document.getString("productPrice")
    val productImage = document.getString("productImage")
    val productDes = document.getString("productDes")
    val productRating = document.getString("productRating")
    val productCategory = document.getString("productCategory")
    list.add(Product(productId!!,productName!!,productPrice!!,productImage!!,productDes!!, productRating!!,productCategory!! ))

}
return list*/
