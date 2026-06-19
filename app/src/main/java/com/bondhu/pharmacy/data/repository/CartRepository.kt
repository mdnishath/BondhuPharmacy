package com.bondhu.pharmacy.data.repository

import com.bondhu.pharmacy.data.model.CartItem
import com.bondhu.pharmacy.data.model.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CartRepository {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(medicine: Medicine) {
        val current = _cartItems.value.toMutableList()
        val existingIndex = current.indexOfFirst { it.medicine.id == medicine.id }
        if (existingIndex >= 0) {
            current[existingIndex] = current[existingIndex].copy(
                quantity = current[existingIndex].quantity + 1
            )
        } else {
            current.add(CartItem(medicine))
        }
        _cartItems.value = current
    }

    fun removeFromCart(medicineId: String) {
        _cartItems.value = _cartItems.value.filter { it.medicine.id != medicineId }
    }

    fun updateQuantity(medicineId: String, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(medicineId)
            return
        }
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.medicine.id == medicineId }
        if (index >= 0) {
            current[index] = current[index].copy(quantity = quantity)
            _cartItems.value = current
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun isInCart(medicineId: String): Boolean =
        _cartItems.value.any { it.medicine.id == medicineId }

    fun getCartCount(): Int = _cartItems.value.sumOf { it.quantity }

    fun getTotalAmount(): Double = _cartItems.value.sumOf { it.totalPrice }
}
