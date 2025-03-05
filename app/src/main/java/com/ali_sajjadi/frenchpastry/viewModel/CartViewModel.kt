package com.ali_sajjadi.frenchpastry.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import com.ali_sajjadi.frenchpastry.repository.ShopRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val shopRepository: ShopRepository
) : ViewModel() {


    private val _cartItem = MutableStateFlow<List<BasketEntities>>(emptyList())
    val cartItem: StateFlow<List<BasketEntities>> = _cartItem

    private val _salePrice = MutableStateFlow(0)
    val salePrice: StateFlow<Int> = _salePrice

    private val _discount = MutableStateFlow(0)
    val discount: StateFlow<Int> = _discount

    private val _showDeleteDialog = MutableStateFlow<BasketEntities?>(null)
    val showDeleteDialog: StateFlow<BasketEntities?> = _showDeleteDialog


    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            shopRepository.getBasketProducts().collect { items ->
                _cartItem.value = items
                salePrice(items)
                discount(items)
                Log.i("Cart_Items", items.toString())
            }
        }
    }

    fun deleteItems(items: BasketEntities) {
        viewModelScope.launch {
            shopRepository.deleteProducts(items)

        }
    }


    private fun salePrice(items: List<BasketEntities>) {
        val totalSalePrice = items.sumOf { it.salePrice }
        _salePrice.value =totalSalePrice


    }

    private fun discount(items: List<BasketEntities>) {
        val totalPrice = items.sumOf { it.price.toInt() }
        val totalSalePrice = items.sumOf { it.salePrice }
        val discountAmount  = totalPrice - totalSalePrice
        _discount.value = discountAmount
    }

    fun showDeleteDialog(item: BasketEntities) {
        _showDeleteDialog.value = item
    }

    fun closeDeleteDialog() {
        _showDeleteDialog.value = null
    }



}