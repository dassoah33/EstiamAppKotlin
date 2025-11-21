package com.example.estiamapp.ui.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estiamapp.data.ProductRepository
import com.example.estiamapp.data.model.ProductDto
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

data class ProductsUiState(
    val all: List<ProductDto> = emptyList(),
    val visibleCount: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

class ProductsViewModel(
    private val repo: ProductRepository = ProductRepository()
): ViewModel()  {
    companion object { private const val CHUNK = 5 }

    var state = mutableStateOf(ProductsUiState())
        private set

    private var loadJob: Job? = null

    init { load() }

    fun load() {
        loadJob?.cancel()
        state.value = state.value.copy(isLoading = true, error = null)
        loadJob = viewModelScope.launch {
            try {
                val products = repo.fetchProducts()
                state.value = ProductsUiState(
                    all = products,
                    visibleCount = minOf(CHUNK, products.size),
                    isLoading = false
                )
            } catch (e: Exception) {
                state.value = state.value.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }

    fun refresh() {
        state.value = state.value.copy(isRefreshing = true, error = null)
        viewModelScope.launch {
            try {
                val products = repo.fetchProducts()
                state.value = ProductsUiState(
                    all = products,
                    visibleCount = minOf(CHUNK, products.size),
                    isRefreshing = false
                )
            } catch (e: Exception) {
                state.value = state.value.copy(isRefreshing = false, error = e.message ?: "Error")
            }
        }
    }

    fun loadMore() {
        val s = state.value
        if (s.isLoading || s.isRefreshing) return
        if (s.visibleCount >= s.all.size) return
        state.value = s.copy(visibleCount = minOf(s.visibleCount + CHUNK, s.all.size))
    }
}