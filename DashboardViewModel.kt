package com.umkm.superapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umkm.superapp.data.local.ProductEntity
import com.umkm.superapp.data.repository.UmkmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UmkmRepository
) : ViewModel() {

    // Ganti workspaceId sesuai sesi login yang aktif
    private val workspaceId: String = "workspace-demo"

    val products: StateFlow<List<ProductEntity>> = repository
        .observeProducts(workspaceId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun calculateMargin(sellingPrice: Double, hpp: Double): Double =
        repository.calculateProfitMargin(sellingPrice, hpp)
}
