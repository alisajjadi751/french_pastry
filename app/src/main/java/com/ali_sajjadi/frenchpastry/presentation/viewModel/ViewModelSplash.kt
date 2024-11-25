package com.ali_sajjadi.frenchpastry.presentation.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelSplash(application: Application) : AndroidViewModel(application) {

    //AlertDialog State
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    //Internet state
    private val _isInternetAvailable = MutableStateFlow(true)
    val isInternetAvailable: StateFlow<Boolean> = _isInternetAvailable

    //Start timer & check internet
    fun startSplashTimer(
        navigateToLogin: () -> Unit
    ) {
        viewModelScope.launch {
            _isInternetAvailable.value = checkInternetConnection(getApplication())

            if (!_isInternetAvailable.value) {
                _showDialog.value = true
            } else {
                delay(3000L)
                navigateToLogin()
            }
        }
    }

    fun retryConnection() {
        _isInternetAvailable.value = checkInternetConnection(getApplication())
        if (_isInternetAvailable.value) {
            _showDialog.value = false
        }
    }

    @SuppressLint("MissingPermission")
    private fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}