package com.bondhu.pharmacy.data.model

data class Medicine(
    val id: String,
    val name: String,
    val genericName: String,
    val brand: String = "",
    val price: Double,
    val category: MedicineCategory,
    val inStock: Boolean = true,
    val description: String = "",
    val imageUrl: String = ""
)

enum class MedicineCategory(val displayName: String, val emoji: String) {
    ALL("সব", "💊"),
    TABLET("Tablet", "🔵"),
    CAPSULE("Capsule", "🟡"),
    SYRUP("Syrup", "🟢"),
    INJECTION("Injection", "💉"),
    DROP("Drop", "💧"),
    CREAM("Cream", "🧴"),
    SACHET("Sachet", "🧂")
}
