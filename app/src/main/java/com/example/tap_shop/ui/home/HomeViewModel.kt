package com.example.tap_shop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tap_shop.SingleLiveEvent
import com.example.tap_shop.model.response.GetAllCategories
import com.example.tap_shop.model.response.GetProduct
import com.example.tap_shop.network.ApiClient
import com.example.tap_shop.network.ApiInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var selectedCategories: GetAllCategories? = null
    var selectedProducts: GetProduct? = null
    private val allCategoriesLiveData = SingleLiveEvent<List<GetAllCategories>?>()
    private val allProductsLiveData = SingleLiveEvent<List<GetProduct>?>()

    val getAllCategories: SingleLiveEvent<List<GetAllCategories>?> = allCategoriesLiveData
    val getProducts: SingleLiveEvent<List<GetProduct>?> = allProductsLiveData

    fun allCategoriesApiCall() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val apiClient = ApiClient.client?.create(ApiInterface::class.java)
            val response  = apiClient?.getAllCategories(
                url = "https://api.escuelajs.co/api/v1/categories",
            )
            allCategoriesLiveData.postValue(response)
        }
    }

    fun allProductsApiCall(id: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val apiClient = ApiClient.client?.create(ApiInterface::class.java)
            val response  = apiClient?.getProducts(
                url = "https://api.escuelajs.co/api/v1/categories/${id}/products",
            )
            allProductsLiveData.postValue(response)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
}