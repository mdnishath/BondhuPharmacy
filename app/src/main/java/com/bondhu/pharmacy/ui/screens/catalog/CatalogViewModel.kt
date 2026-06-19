package com.bondhu.pharmacy.ui.screens.catalog

import androidx.lifecycle.ViewModel
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.data.model.MedicineCategory
import com.bondhu.pharmacy.data.repository.CartRepository
import com.bondhu.pharmacy.data.repository.MedicineRepository
import com.bondhu.pharmacy.data.repository.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CatalogViewModel : ViewModel() {

    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> = _medicines.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(MedicineCategory.ALL)
    val selectedCategory: StateFlow<MedicineCategory> = _selectedCategory.asStateFlow()

    init {
        updateList()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        updateList()
    }

    fun onCategorySelected(category: MedicineCategory) {
        _selectedCategory.value = category
        updateList()
    }

    private fun updateList() {
        _medicines.value = MedicineRepository.filterAndSearch(
            query = _searchQuery.value,
            category = _selectedCategory.value
        )
    }

    fun addToCart(medicine: Medicine) {
        CartRepository.addToCart(medicine)
    }

    fun toggleWishlist(medicine: Medicine) {
        WishlistRepository.toggleWishlist(medicine)
    }

    fun isInCart(medicineId: String): Boolean = CartRepository.isInCart(medicineId)

    fun isInWishlist(medicineId: String): Boolean = WishlistRepository.isInWishlist(medicineId)
}
