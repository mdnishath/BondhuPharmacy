package com.bondhu.pharmacy.ui.screens.wishlist

import androidx.lifecycle.ViewModel
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.data.repository.CartRepository
import com.bondhu.pharmacy.data.repository.WishlistRepository
import kotlinx.coroutines.flow.StateFlow

class WishlistViewModel : ViewModel() {

    val wishlistItems: StateFlow<List<Medicine>> = WishlistRepository.wishlistItems

    fun removeFromWishlist(medicineId: String) {
        WishlistRepository.removeFromWishlist(medicineId)
    }

    fun moveToCart(medicine: Medicine) {
        CartRepository.addToCart(medicine)
        WishlistRepository.removeFromWishlist(medicine.id)
    }
}
