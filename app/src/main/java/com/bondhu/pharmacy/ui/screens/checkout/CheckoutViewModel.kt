package com.bondhu.pharmacy.ui.screens.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondhu.pharmacy.data.model.Order
import com.bondhu.pharmacy.data.model.OrderItem
import com.bondhu.pharmacy.data.repository.CartRepository
import com.bondhu.pharmacy.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone.asStateFlow()

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()

    private val _isSubmitting = MutableStateFlow(false)
    val isSubmitting: StateFlow<Boolean> = _isSubmitting.asStateFlow()

    private val _orderSuccess = MutableStateFlow<String?>(null)
    val orderSuccess: StateFlow<String?> = _orderSuccess.asStateFlow()

    val totalAmount = CartRepository.getTotalAmount()

    fun updateName(newValue: String) { _name.value = newValue }
    fun updatePhone(newValue: String) { _phone.value = newValue }
    fun updateAddress(newValue: String) { _address.value = newValue }

    fun placeOrder() {
        if (_name.value.isBlank() || _phone.value.isBlank() || _address.value.isBlank()) return
        
        _isSubmitting.value = true
        
        viewModelScope.launch {
            val cartItems = CartRepository.cartItems.value
            val orderItems = cartItems.map { item ->
                OrderItem(
                    medicineId = item.medicine.id,
                    medicineName = item.medicine.name,
                    genericName = item.medicine.genericName,
                    quantity = item.quantity,
                    price = item.medicine.price
                )
            }
            
            val order = Order(
                customerName = _name.value,
                customerPhone = _phone.value,
                deliveryAddress = _address.value,
                items = orderItems,
                totalAmount = totalAmount
            )
            
            val result = OrderRepository.placeOrder(order)
            
            _isSubmitting.value = false
            if (result.isSuccess) {
                CartRepository.clearCart()
                _orderSuccess.value = result.getOrNull()
            }
        }
    }
}
