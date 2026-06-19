package com.bondhu.pharmacy.data.repository

import com.bondhu.pharmacy.data.model.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object WishlistRepository {

    private val _wishlistItems = MutableStateFlow<List<Medicine>>(emptyList())
    val wishlistItems: StateFlow<List<Medicine>> = _wishlistItems.asStateFlow()

    fun addToWishlist(medicine: Medicine) {
        if (!isInWishlist(medicine.id)) {
            _wishlistItems.value = _wishlistItems.value + medicine
        }
    }

    fun removeFromWishlist(medicineId: String) {
        _wishlistItems.value = _wishlistItems.value.filter { it.id != medicineId }
    }

    fun toggleWishlist(medicine: Medicine) {
        if (isInWishlist(medicine.id)) {
            removeFromWishlist(medicine.id)
        } else {
            addToWishlist(medicine)
        }
    }

    fun isInWishlist(medicineId: String): Boolean =
        _wishlistItems.value.any { it.id == medicineId }

    fun getWishlistCount(): Int = _wishlistItems.value.size
}
