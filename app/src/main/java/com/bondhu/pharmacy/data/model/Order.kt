package com.bondhu.pharmacy.data.model

data class Order(
    val id: String = "",
    val customerName: String = "",
    val customerPhone: String = "",
    val deliveryAddress: String = "",
    val items: List<OrderItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val status: String = "pending",
    val createdAt: Long = System.currentTimeMillis()
)

data class OrderItem(
    val medicineId: String = "",
    val medicineName: String = "",
    val genericName: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0
) {
    val totalPrice: Double get() = price * quantity
}
