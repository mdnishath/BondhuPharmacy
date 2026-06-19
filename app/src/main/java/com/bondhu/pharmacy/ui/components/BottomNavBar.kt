package com.bondhu.pharmacy.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.theme.*

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home,     "হোম",     Icons.Default.Home),
    BottomNavItem(Screen.Catalog,  "ওষুধ",    Icons.Default.Search),
    BottomNavItem(Screen.Cart,     "কার্ট",    Icons.Default.ShoppingCart),
    BottomNavItem(Screen.Wishlist, "পছন্দ",    Icons.Default.Favorite),
    BottomNavItem(Screen.Profile,  "প্রোফাইল", Icons.Default.Person)
)

@Composable
fun BottomNavBar(
    navController: NavController,
    currentRoute: String?,
    cartCount: Int,
    wishlistCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PanelDark)
    ) {
        // Top divider line
        HorizontalDivider(
            modifier  = Modifier.fillMaxWidth(),
            color     = BorderColor,
            thickness = 1.dp
        )

        NavigationBar(
            containerColor = Color.Transparent,
            contentColor   = TextMuted,
            tonalElevation = 0.dp,
            modifier       = Modifier.padding(top = 1.dp)
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = currentRoute == item.screen.route

                val iconColor by animateColorAsState(
                    targetValue = if (isSelected) LimeGreen else TextMuted,
                    animationSpec = tween(200),
                    label = "navIconColor"
                )

                val badge: Int? = when (item.screen) {
                    Screen.Cart     -> cartCount.takeIf { it > 0 }
                    Screen.Wishlist -> wishlistCount.takeIf { it > 0 }
                    else            -> null
                }

                NavigationBarItem(
                    selected = isSelected,
                    onClick  = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState    = true
                        }
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (badge != null) {
                                    Badge(
                                        containerColor = LimeGreen,
                                        contentColor   = Color(0xFF0D1A00)
                                    ) {
                                        Text(
                                            text       = badge.toString(),
                                            fontSize   = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector        = item.icon,
                                contentDescription = item.label,
                                tint               = iconColor,
                                modifier           = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text       = item.label,
                            fontSize   = 10.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            color      = iconColor
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor   = LimeGreen,
                        unselectedIconColor = TextMuted,
                        selectedTextColor   = LimeGreen,
                        unselectedTextColor = TextMuted,
                        indicatorColor      = LimeGreenAlpha
                    )
                )
            }
        }
    }
}
