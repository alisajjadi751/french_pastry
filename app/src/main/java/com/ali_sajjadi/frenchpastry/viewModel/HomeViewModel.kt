package com.ali_sajjadi.frenchpastry.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali_sajjadi.frenchpastry.data.remote.model.home.HomeResponse
import com.ali_sajjadi.frenchpastry.repository.HomeRepository
import com.ali_sajjadi.frenchpastry.utils.Constants.separator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository = HomeRepository()
) : ViewModel() {

    private val _responseMain = MutableStateFlow<HomeResponse?>(null)
    val responseMain : StateFlow<HomeResponse?> get() = _responseMain



    init {
        // بارگیری داده‌ها در زمان ایجاد ViewModel
        if (_responseMain.value == null) {
            getMain()
        }
    }

     private fun getMain() {
        if (_responseMain.value == null) {
            viewModelScope.launch {
                try {
                    val response = homeRepository.getMain()
                    _responseMain.value = response
                } catch (e: Exception) {
                    Log.i("Main_Error", e.message.toString())
                }
            }
        }
    }

    fun getFormattedPrice(price: String): String {
        return separator.format(price.toInt() / 10).toString()
    }

    // پردازش تخفیف
    fun getFormattedSalePrice(price: Int): String {
        return separator.format(price.toInt() / 10).toString()
    }

}