package com.ali_sajjadi.frenchpastry.viewModel

import androidx.lifecycle.ViewModel
import com.ali_sajjadi.frenchpastry.presentation.navgraph.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class BottomNavViewModel: ViewModel() {

    private val _currentRoute = MutableStateFlow<String>(Route.HomeScreen.route)
    val currentRoute: StateFlow<String> = _currentRoute

    fun updateCurrentRoute(route: String) {
        _currentRoute.value = route
    }


}