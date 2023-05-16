package com.example.buynow_te_e_comerce_app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.buynow_te_e_comerce_app.data.FavorEntity
import com.example.buynow_te_e_comerce_app.data.RoomDB
import com.example.buynow_te_e_comerce_app.repo.Fav_Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavRoomViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Fav_Repo
    val allproducts: LiveData<List<FavorEntity>>

    init {
        val favDao = RoomDB.getInstance(application).favDao()
        repository = Fav_Repo(favDao)
        allproducts = repository.allFavProducts
    }

    fun check(id: String): LiveData<Boolean> {
        return repository.Get_favByID(id)
    }

    fun insert(product: FavorEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.InsertProduct(product)
    }
    fun deleteCart(product: FavorEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }
}