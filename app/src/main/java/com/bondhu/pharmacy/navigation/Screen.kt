package com.bondhu.pharmacy.navigation

sealed class Screen(val route: String) {
    object Home           : Screen("home")
    object Catalog        : Screen("catalog")
    object Cart           : Screen("cart")
    object Wishlist       : Screen("wishlist")
    object Checkout       : Screen("checkout")

    object MedicineDetail : Screen("medicine_detail/{medicineId}") {
        fun createRoute(medicineId: String) = "medicine_detail/$medicineId"
    }

    object OrderSuccess : Screen("order_success/{orderId}") {
        fun createRoute(orderId: String) = "order_success/$orderId"
    }

    object Login : Screen("login")
    object Signup : Screen("signup")
}

// Bottom nav items
val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Catalog,
    Screen.Cart,
    Screen.Wishlist
)
