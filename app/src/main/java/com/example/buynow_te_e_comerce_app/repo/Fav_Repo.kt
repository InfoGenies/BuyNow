package com.example.buynow_te_e_comerce_app.repo

import androidx.lifecycle.LiveData
import com.example.buynow_te_e_comerce_app.data.FavorEntity
import com.example.buynow_te_e_comerce_app.data.FavoriteDao

class Fav_Repo(private val favDao: FavoriteDao) {


    val allFavProducts: LiveData<List<FavorEntity>> = favDao.GetFavorite()

    fun Get_favByID(id: String): LiveData<Boolean> {
        return favDao.GetFavoriteByID(id)
    }

    suspend fun InsertProduct(pfav: FavorEntity) {
        favDao.InsertFavorite(pfav)
    }


    suspend fun delete(product: FavorEntity) {
      favDao.delete(product)
    }

}