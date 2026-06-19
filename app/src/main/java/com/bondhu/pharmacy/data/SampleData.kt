package com.bondhu.pharmacy.data

import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.data.model.MedicineCategory

object SampleData {

    val medicines = listOf(
        // ── Tablets ───────────────────────────────────────────────────────────────
        Medicine(
            id = "1", name = "Napa", genericName = "Paracetamol 500mg",
            brand = "Beximco", price = 2.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Common pain reliever and fever reducer. Safe for all ages."
        ),
        Medicine(
            id = "2", name = "Fimoxyl", genericName = "Amoxicillin 500mg",
            brand = "Square", price = 12.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Broad-spectrum antibiotic for bacterial infections."
        ),
        Medicine(
            id = "3", name = "Flagyl", genericName = "Metronidazole 400mg",
            brand = "Sanofi", price = 5.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Antibiotic effective against bacterial and parasitic infections."
        ),
        Medicine(
            id = "4", name = "Alatrol", genericName = "Cetirizine 10mg",
            brand = "Square", price = 4.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Non-drowsy antihistamine for seasonal allergies."
        ),
        Medicine(
            id = "5", name = "C-Vit", genericName = "Vitamin C 500mg",
            brand = "ACI", price = 3.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Vitamin C supplement for immune system support."
        ),
        Medicine(
            id = "6", name = "Calcio-D", genericName = "Calcium + Vitamin D3",
            brand = "Renata", price = 15.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Bone strength and calcium supplement."
        ),
        Medicine(
            id = "7", name = "Montika", genericName = "Montelukast 10mg",
            brand = "Opsonin", price = 20.0, category = MedicineCategory.TABLET,
            inStock = true, description = "For asthma prevention and allergic rhinitis."
        ),
        Medicine(
            id = "8", name = "Storvas", genericName = "Atorvastatin 10mg",
            brand = "Square", price = 18.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Lowers cholesterol and reduces heart disease risk."
        ),
        Medicine(
            id = "9", name = "Glucomin", genericName = "Metformin 500mg",
            brand = "General", price = 6.0, category = MedicineCategory.TABLET,
            inStock = true, description = "First-line medication for type 2 diabetes."
        ),
        Medicine(
            id = "10", name = "Amdocal", genericName = "Amlodipine 5mg",
            brand = "ACI", price = 8.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Calcium channel blocker for high blood pressure."
        ),
        Medicine(
            id = "11", name = "Losacar", genericName = "Losartan 50mg",
            brand = "Incepta", price = 12.0, category = MedicineCategory.TABLET,
            inStock = true, description = "ARB for hypertension and kidney protection."
        ),
        Medicine(
            id = "12", name = "Azithro", genericName = "Azithromycin 500mg",
            brand = "Eskayef", price = 35.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Antibiotic for respiratory and skin infections."
        ),
        Medicine(
            id = "13", name = "Pantop", genericName = "Pantoprazole 40mg",
            brand = "Acme", price = 10.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Proton pump inhibitor for acid reflux."
        ),
        Medicine(
            id = "14", name = "Dome", genericName = "Domperidone 10mg",
            brand = "ACI", price = 5.0, category = MedicineCategory.TABLET,
            inStock = true, description = "For nausea, vomiting, and bloating relief."
        ),
        Medicine(
            id = "15", name = "Voren", genericName = "Diclofenac 50mg",
            brand = "Incepta", price = 6.0, category = MedicineCategory.TABLET,
            inStock = true, description = "NSAID for pain and inflammation relief."
        ),
        Medicine(
            id = "16", name = "Ibufen", genericName = "Ibuprofen 400mg",
            brand = "Beximco", price = 4.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Pain, fever, and inflammation relief."
        ),
        Medicine(
            id = "17", name = "Ranison", genericName = "Ranitidine 150mg",
            brand = "Square", price = 3.0, category = MedicineCategory.TABLET,
            inStock = false, description = "H2 blocker to reduce stomach acid."
        ),
        Medicine(
            id = "18", name = "Setron", genericName = "Ondansetron 4mg",
            brand = "General", price = 12.0, category = MedicineCategory.TABLET,
            inStock = true, description = "Powerful anti-nausea medication."
        ),

        // ── Capsules ────────────────────────────────────────────────────────────
        Medicine(
            id = "19", name = "Fimoxyl Cap", genericName = "Amoxicillin 500mg",
            brand = "Square", price = 14.0, category = MedicineCategory.CAPSULE,
            inStock = true, description = "Capsule form of amoxicillin antibiotic."
        ),
        Medicine(
            id = "20", name = "Losectil", genericName = "Omeprazole 20mg",
            brand = "Incepta", price = 8.0, category = MedicineCategory.CAPSULE,
            inStock = true, description = "Reduces stomach acid; treats ulcers."
        ),
        Medicine(
            id = "21", name = "Doxitin", genericName = "Doxycycline 100mg",
            brand = "Renata", price = 15.0, category = MedicineCategory.CAPSULE,
            inStock = true, description = "Broad-spectrum antibiotic capsule."
        ),

        // ── Syrups ──────────────────────────────────────────────────────────────
        Medicine(
            id = "22", name = "Bricasal Syrup", genericName = "Salbutamol 2mg/5ml",
            brand = "Aristopharma", price = 45.0, category = MedicineCategory.SYRUP,
            inStock = true, description = "Bronchodilator for asthma relief."
        ),
        Medicine(
            id = "23", name = "Mucosol Syrup", genericName = "Ambroxol 15mg/5ml",
            brand = "Opsonin", price = 60.0, category = MedicineCategory.SYRUP,
            inStock = true, description = "Mucolytic for cough and cold relief."
        ),
        Medicine(
            id = "24", name = "Napa Syrup", genericName = "Paracetamol 120mg/5ml",
            brand = "Beximco", price = 35.0, category = MedicineCategory.SYRUP,
            inStock = true, description = "Children's liquid paracetamol for fever."
        ),
        Medicine(
            id = "25", name = "Zin Syrup", genericName = "Zinc Sulphate 20mg/5ml",
            brand = "Square", price = 80.0, category = MedicineCategory.SYRUP,
            inStock = true, description = "Zinc supplement for children's immunity."
        ),

        // ── Sachets ─────────────────────────────────────────────────────────────
        Medicine(
            id = "26", name = "Orsaline-N", genericName = "ORS Powder",
            brand = "ACME", price = 5.0, category = MedicineCategory.SACHET,
            inStock = true, description = "Oral rehydration salts for dehydration."
        ),

        // ── Eye Drops ───────────────────────────────────────────────────────────
        Medicine(
            id = "27", name = "Chlora Eye Drop", genericName = "Chloramphenicol 0.5%",
            brand = "General", price = 40.0, category = MedicineCategory.DROP,
            inStock = true, description = "Antibiotic eye drops for eye infections."
        ),
        Medicine(
            id = "28", name = "Napha Drop", genericName = "Naphazoline HCl 0.05%",
            brand = "Renata", price = 25.0, category = MedicineCategory.DROP,
            inStock = true, description = "Eye redness and irritation relief."
        ),

        // ── Creams ──────────────────────────────────────────────────────────────
        Medicine(
            id = "29", name = "Beta Cream", genericName = "Betamethasone 0.1%",
            brand = "Square", price = 55.0, category = MedicineCategory.CREAM,
            inStock = true, description = "Anti-inflammatory corticosteroid cream."
        ),
        Medicine(
            id = "30", name = "Clotri Cream", genericName = "Clotrimazole 1%",
            brand = "ACI", price = 48.0, category = MedicineCategory.CREAM,
            inStock = true, description = "Antifungal cream for skin infections."
        ),

        // ── Injections ──────────────────────────────────────────────────────────
        Medicine(
            id = "31", name = "Actrapid", genericName = "Insulin Regular 100IU/ml",
            brand = "Novo Nordisk", price = 350.0, category = MedicineCategory.INJECTION,
            inStock = true, description = "Fast-acting insulin for diabetes management."
        ),
        Medicine(
            id = "32", name = "Ceftriaxone", genericName = "Ceftriaxone 1g",
            brand = "Incepta", price = 180.0, category = MedicineCategory.INJECTION,
            inStock = true, description = "Third-generation cephalosporin antibiotic injection."
        ),
    )
}
