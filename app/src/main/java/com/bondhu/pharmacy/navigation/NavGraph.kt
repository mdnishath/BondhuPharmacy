package com.bondhu.pharmacy.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.bondhu.pharmacy.data.repository.CartRepository
import com.bondhu.pharmacy.data.repository.WishlistRepository
import com.bondhu.pharmacy.ui.components.BottomNavBar
import com.bondhu.pharmacy.ui.screens.cart.CartScreen
import com.bondhu.pharmacy.ui.screens.catalog.CatalogScreen
import com.bondhu.pharmacy.ui.screens.checkout.CheckoutScreen
import com.bondhu.pharmacy.ui.screens.detail.MedicineDetailScreen
import com.bondhu.pharmacy.ui.screens.home.HomeScreen
import com.bondhu.pharmacy.ui.screens.wishlist.WishlistScreen
import com.bondhu.pharmacy.ui.screens.checkout.OrderSuccessScreen
import com.bondhu.pharmacy.ui.screens.auth.LoginScreen
import com.bondhu.pharmacy.ui.screens.auth.SignupScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BondhuNavGraph() {
    val navController = rememberNavController()
    val cartCount     by CartRepository.cartItems.collectAsState()
    val wishlistCount by WishlistRepository.wishlistItems.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomNav = currentRoute in listOf(
        Screen.Home.route,
        Screen.Catalog.route,
        Screen.Cart.route,
        Screen.Wishlist.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavBar(
                    navController    = navController,
                    currentRoute     = currentRoute,
                    cartCount        = cartCount.sumOf { it.quantity },
                    wishlistCount    = wishlistCount.size
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController    = navController,
            startDestination = Screen.Home.route,
            modifier         = Modifier.padding(innerPadding),
            enterTransition  = { fadeIn(tween(220)) + slideInHorizontally(tween(220)) { it / 5 } },
            exitTransition   = { fadeOut(tween(180)) },
            popEnterTransition  = { fadeIn(tween(220)) },
            popExitTransition   = { fadeOut(tween(180)) + slideOutHorizontally(tween(220)) { it / 5 } }
        ) {

            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(Screen.Catalog.route) {
                CatalogScreen(navController = navController)
            }

            composable(Screen.Cart.route) {
                CartScreen(navController = navController)
            }

            composable(Screen.Wishlist.route) {
                WishlistScreen(navController = navController)
            }

        composable(Screen.Profile.route) {
            com.bondhu.pharmacy.ui.screens.profile.ProfileScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToSignup = { navController.navigate(Screen.Signup.route) }
            )
        }

        composable(Screen.Checkout.route) {
                CheckoutScreen(navController = navController)
            }

            composable(
                route     = Screen.MedicineDetail.route,
                arguments = listOf(navArgument("medicineId") { type = NavType.StringType })
            ) { backStackEntry ->
                val medicineId = backStackEntry.arguments?.getString("medicineId") ?: ""
                MedicineDetailScreen(
                    medicineId    = medicineId,
                    navController = navController
                )
            }

            composable(
                route     = Screen.OrderSuccess.route,
                arguments = listOf(navArgument("orderId") { type = NavType.StringType })
            ) { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                OrderSuccessScreen(
                    orderId       = orderId,
                    navController = navController
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(navController = navController)
            }

            composable(Screen.Signup.route) {
                SignupScreen(navController = navController)
            }
        }
    }
}
