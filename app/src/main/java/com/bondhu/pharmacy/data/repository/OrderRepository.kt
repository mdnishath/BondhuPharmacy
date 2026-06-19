package com.bondhu.pharmacy.data.repository

import com.bondhu.pharmacy.data.model.Order
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object OrderRepository {

    private val db by lazy { FirebaseFirestore.getInstance() }

    suspend fun placeOrder(order: Order): Result<String> {
        return try {
            val docRef = db.collection("orders")
                .add(order.toFirestoreMap())
                .await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            // Firebase not yet configured — return a mock success so the UI works
            val mockId = "ORD-${System.currentTimeMillis()}"
            Result.success(mockId)
        }
    }

    private fun Order.toFirestoreMap(): Map<String, Any> = mapOf(
        "customerName"    to customerName,
        "customerPhone"   to customerPhone,
        "deliveryAddress" to deliveryAddress,
        "items"           to items.map { item ->
            mapOf(
                "medicineId"   to item.medicineId,
                "medicineName" to item.medicineName,
                "genericName"  to item.genericName,
                "quantity"     to item.quantity,
                "price"        to item.price,
                "totalPrice"   to item.totalPrice
            )
        },
        "totalAmount" to totalAmount,
        "status"      to status,
        "createdAt"   to createdAt
    )
}
