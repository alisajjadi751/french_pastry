package com.ali_sajjadi.frenchpastry.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import com.ali_sajjadi.frenchpastry.data.manager.TokenManager
import com.ali_sajjadi.frenchpastry.data.remote.model.details.DetailsProduct
import com.ali_sajjadi.frenchpastry.data.remote.model.details.setComment.SetComment

import com.ali_sajjadi.frenchpastry.repository.DetailsProductRepository
import com.ali_sajjadi.frenchpastry.repository.ShopRepository
import com.ali_sajjadi.frenchpastry.utils.Constants.separator
import com.ali_sajjadi.frenchpastry.wrapper.DeviceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsProductViewModel(
    application: Application,
    private val detailsRepository: DetailsProductRepository = DetailsProductRepository(),
    private val shopRepository: ShopRepository,
    private val tokenManager: TokenManager = TokenManager.getInstance(application)
) : ViewModel() {

    private val _apiKey = MutableStateFlow("")
    private val apiKey: StateFlow<String> = _apiKey

    init {
        viewModelScope.launch {
            tokenManager.getToken().collect { token ->
                _apiKey.value = token ?: ""
            }
        }
    }

    private val publicKey: String by lazy {
        DeviceInfo.getPublicKey(context = application, apiKey = apiKey.value)
    }
    private val deviceUID: String by lazy {
        DeviceInfo.getDeviceID(application)
    }

    private val _responseDetails = MutableStateFlow<DetailsProduct?>(null)
    val responseDetails: StateFlow<DetailsProduct?> = _responseDetails

    private val _responseComment = MutableStateFlow<SetComment?>(null)
    val responseComment: StateFlow<SetComment?> = _responseComment

    fun getDetails(id: Int) {
        viewModelScope.launch {
            try {
                val response = detailsRepository.getDetailsProduct(id)
                _responseDetails.value = response
                Log.i("API_Response", response.toString())

            } catch (e: Exception) {
                Log.i("Details_Error", e.message.toString())
            }

        }
    }

    fun setComment(postId: Int, content: String, rate: Int) {
        viewModelScope.launch{
            try {
                tokenManager.getToken().collect { apiKey ->
                    Log.i("API_Key",apiKey.toString())

                    if (apiKey != null) {
                        val response = detailsRepository.setComment(
                            apiKey = apiKey,
                            deviceID = deviceUID,
                            publicKey = publicKey,
                            postId = postId,
                            content = content,
                            rate = rate
                        )
                        _responseComment.value = response
                        Log.i("Comment",response.message)

                    }else {

                        Log.e("Comment_Error", "API Key خالی است.")
                    }

                }

            } catch (e: Exception) {
                Log.e("Details_Error", "ارسال کامنت ناموفق بود: ${e.message}")
            }
        }
    }


    fun addProductToBasket(basketEntities: BasketEntities) {
        viewModelScope.launch {
            shopRepository.insertProduct(basketEntities )
        }
    }


    fun getFormattedPrice(price: String): String {
        return separator.format(price.toInt() / 10).toString()
    }

    fun getFormattedSalePrice(price: Int): String {
        return separator.format(price / 10).toString()
    }

    fun logBasketItems() {
        viewModelScope.launch {
            try {
                shopRepository.getBasketProducts().collect { basketItems ->
                    Log.i("Basket_Items", "Basket contains: ${basketItems.size} items")
                    basketItems.forEach { item ->
                        Log.i("Basket_Items_Detail", "Item: $item")
                    }
                }
            } catch (e: Exception) {
                Log.e("Basket_Error", "Error fetching basket items: ${e.message}")
            }
        }
    }


}