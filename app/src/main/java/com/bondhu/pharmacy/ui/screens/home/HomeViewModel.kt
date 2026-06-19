package com.bondhu.pharmacy.ui.screens.home

import androidx.lifecycle.ViewModel
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.data.repository.CartRepository
import com.bondhu.pharmacy.data.repository.MedicineRepository
import com.bondhu.pharmacy.data.repository.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _featuredMedicines = MutableStateFlow<List<Medicine>>(emptyList())
    val featuredMedicines: StateFlow<List<Medicine>> = _featuredMedicines.asStateFlow()

    init {
        loadFeaturedMedicines()
    }

    private fun loadFeaturedMedicines() {
        _featuredMedicines.value = MedicineRepository.getFeaturedMedicines()
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
