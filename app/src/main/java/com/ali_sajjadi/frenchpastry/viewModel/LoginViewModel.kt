package com.ali_sajjadi.frenchpastry.viewModel

import android.app.Application
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ali_sajjadi.frenchpastry.data.manager.TokenManager
import com.ali_sajjadi.frenchpastry.data.remote.model.login.SendPhoneResponse
import com.ali_sajjadi.frenchpastry.data.remote.model.login.VerifyCode
import com.ali_sajjadi.frenchpastry.repository.HomeRepository
import com.ali_sajjadi.frenchpastry.repository.LoginRepository
import com.ali_sajjadi.frenchpastry.wrapper.DeviceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Application.dataStore by preferencesDataStore("user_preferences")

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepository(),
    application: Application,
    private val tokenManager: TokenManager = TokenManager.getInstance(application)
) : AndroidViewModel(application) {



    private val _sendPhoneResponse = MutableStateFlow<SendPhoneResponse?>(null)
    val sendCodeResponse: StateFlow<SendPhoneResponse?> = _sendPhoneResponse

    private val _verifyCodeResponse = MutableStateFlow<VerifyCode?>(null)
    val verifyCode: StateFlow<VerifyCode?> = _verifyCodeResponse


    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone
    fun updatePhone(phone: String) {
        _phone.value = phone
    }
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
   /* private val publicKey: String by lazy {
        DeviceInfo.getPublicKey(context =application, apiKey = )
    }*/

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {

        viewModelScope.launch {
            val status = getLoginStatus()
            _isLoggedIn.value = status
        }
    }


    fun sendPhone2(phone: String) {
        viewModelScope.launch {
            try {
                val response = loginRepository.apiLogin2(deviceUID, publicKey, phone)
                _sendPhoneResponse.value = response

            } catch (e: Exception) {
                Log.i("PHONE_ERROR", e.message.toString())
            }
        }
    }

    fun verifyCode2(code: String, phone: String) {
        viewModelScope.launch {
            try {
                val response = loginRepository.apiCode2(deviceUID, publicKey, code, phone)
                _verifyCodeResponse.value = response

                Log.i("API1", response.api)
                if (response.success == 1) {
                    saveLoginStatus(true)
                    saveApi(response.api)
                    Log.i("API1", response.api)
                }
            } catch (e: Exception) {
                Log.i("VERIFY_ERROR", e.message.toString())
            }
        }
    }


    fun saveLoginStatus(isLoggedIn: Boolean) {
        viewModelScope.launch {
            tokenManager.saveLoginStatus(isLoggedIn)
            _isLoggedIn.value = isLoggedIn
        }
    }

    private suspend fun getLoginStatus(): Boolean {
        return tokenManager.getLoginStatus().first()
    }


    fun logout() {
        viewModelScope.launch {
            // saveLoginStatus(false)
        }
    }

    fun saveApi(api: String) {
        viewModelScope.launch {
            tokenManager.saveToken(api)
        }
    }
}
