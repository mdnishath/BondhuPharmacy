package com.bondhu.pharmacy.data.repository

import com.bondhu.pharmacy.data.SampleData
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.data.model.MedicineCategory

object MedicineRepository {

    fun getAllMedicines(): List<Medicine> = SampleData.medicines

    fun getFeaturedMedicines(): List<Medicine> =
        SampleData.medicines.filter { it.inStock }.take(8)

    fun searchMedicines(query: String): List<Medicine> {
        if (query.isBlank()) return SampleData.medicines
        val lowerQuery = query.lowercase().trim()
        return SampleData.medicines.filter {
            it.name.lowercase().contains(lowerQuery) ||
            it.genericName.lowercase().contains(lowerQuery) ||
            it.brand.lowercase().contains(lowerQuery)
        }
    }

    fun getMedicinesByCategory(category: MedicineCategory): List<Medicine> {
        if (category == MedicineCategory.ALL) return SampleData.medicines
        return SampleData.medicines.filter { it.category == category }
    }

    fun filterAndSearch(query: String, category: MedicineCategory): List<Medicine> {
        var result = if (category == MedicineCategory.ALL) SampleData.medicines
                     else SampleData.medicines.filter { it.category == category }
        if (query.isNotBlank()) {
            val lowerQuery = query.lowercase().trim()
            result = result.filter {
                it.name.lowercase().contains(lowerQuery) ||
                it.genericName.lowercase().contains(lowerQuery) ||
                it.brand.lowercase().contains(lowerQuery)
            }
        }
        return result
    }

    fun getMedicineById(id: String): Medicine? =
        SampleData.medicines.find { it.id == id }
}
