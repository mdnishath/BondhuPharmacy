package com.bondhu.pharmacy.data.model

data class CartItem(
    val medicine: Medicine,
    val quantity: Int = 1
) {
    val totalPrice: Double get() = medicine.price * quantity
}
