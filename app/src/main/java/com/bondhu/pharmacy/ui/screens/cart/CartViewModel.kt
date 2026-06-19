package com.bondhu.pharmacy.ui.screens.cart

import androidx.lifecycle.ViewModel
import com.bondhu.pharmacy.data.model.CartItem
import com.bondhu.pharmacy.data.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = CartRepository.cartItems

    fun updateQuantity(medicineId: String, quantity: Int) {
        CartRepository.updateQuantity(medicineId, quantity)
    }

    fun removeItem(medicineId: String) {
        CartRepository.removeFromCart(medicineId)
    }

    fun getTotalAmount(): Double = CartRepository.getTotalAmount()
}
