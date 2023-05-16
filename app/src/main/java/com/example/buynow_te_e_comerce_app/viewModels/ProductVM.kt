package com.example.buynow_te_e_comerce_app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.buynow_te_e_comerce_app.models.Categorie
import com.example.buynow_te_e_comerce_app.models.CoverProduct
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.repo.ProductRepo
import kotlinx.coroutines.launch

class ProductVM(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepo
    private var _ListFiliter: MutableLiveData<ArrayList<Product>>
    private var _NewList: MutableLiveData<ArrayList<Product>>
    private var _CatList: MutableLiveData<ArrayList<Categorie>>
    private var _CoverList: MutableLiveData<ArrayList<CoverProduct>>
    private var _SaleList: MutableLiveData<ArrayList<Product>>

    val List: LiveData<ArrayList<Product>> get() = _ListFiliter
    val NewList: LiveData<ArrayList<Product>> get() = _NewList
    val CatList: LiveData<ArrayList<Categorie>> get() = _CatList
    val CoverList: LiveData<ArrayList<CoverProduct>> get() = _CoverList
    val saleList: LiveData<ArrayList<Product>> get() = _SaleList


    init {
        // iniatliase the Arrayliste
        _ListFiliter =  MutableLiveData<ArrayList<Product>>()
        _NewList = MutableLiveData<ArrayList<Product>>()
        _CatList = MutableLiveData<ArrayList<Categorie>>()
        _CoverList = MutableLiveData<ArrayList<CoverProduct>>()
        _SaleList = MutableLiveData<ArrayList<Product>>()
// inialise the repository
        repository = ProductRepo()
    }

    fun getAllProduct() {
        viewModelScope.launch {
            _NewList.postValue(repository.getProductsFirestore())
        }
    }

    fun getCoverProduct() {
        viewModelScope.launch {
            _CoverList.postValue(repository.getCoverProductsFirestore())
        }
    }
    fun getSale() {
        viewModelScope.launch {
            _SaleList.postValue(repository.getSaleFirestore())
        }
    }

    fun getCat() {
        viewModelScope.launch {
            _CatList.postValue(repository.getCategorieFirestore())
        }

    }
    fun getlisteFilter(name:String) {
        viewModelScope.launch {
            _ListFiliter.postValue(repository.getListeFiltring(name))
        }

    }

}