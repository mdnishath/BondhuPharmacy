package com.bondhu.pharmacy.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.ui.components.EmptyStateMessage
import com.bondhu.pharmacy.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(
    medicineId: String,
    navController: NavController,
    viewModel: DetailViewModel = viewModel()
) {
    LaunchedEffect(medicineId) {
        viewModel.loadMedicine(medicineId)
    }

    val medicine by viewModel.medicine.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ওষুধের বিবরণ") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundDark,
                    titleContentColor = TextPrimary,
                    navigationIconContentColor = TextPrimary
                )
            )
        },
        bottomBar = {
            medicine?.let { med ->
                val isInCart = viewModel.isInCart(med.id)
                Surface(
                    color = PanelDark,
                    shadowElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Total Price",
                                style = MaterialTheme.typography.labelMedium,
                                color = TextMuted
                            )
                            Text(
                                text = "৳ ${String.format("%.2f", med.price)}",
                                style = MaterialTheme.typography.titleLarge,
                                color = LimeGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(
                            onClick = { viewModel.addToCart(med) },
                            enabled = med.inStock,
                            modifier = Modifier
                                .weight(2f)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isInCart) LimeGreenDark else LimeGreen,
                                contentColor = Color(0xFF0D1A00)
                            )
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (isInCart) "Added to Cart" else "Add to Cart",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        if (medicine == null) {
            EmptyStateMessage("Medicine not found")
        } else {
            val med = medicine!!
            val isInWishlist = viewModel.isInWishlist(med.id)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Image Placeholder / Emoji
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(PanelDark2),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Brush.radialGradient(listOf(LimeGreenAlpha, Color.Transparent)))
                            .border(1.dp, BorderColor, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = med.category.emoji, fontSize = 50.sp)
                    }

                    IconButton(
                        onClick = { viewModel.toggleWishlist(med) },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = if (isInWishlist) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = if (isInWishlist) ErrorColor else TextPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                // Details Content
                Column(modifier = Modifier.padding(16.dp)) {
                    // Category Badge
                    Surface(
                        color = PanelDark,
                        shape = RoundedCornerShape(8.dp),
                        border = borderStroke(BorderColor)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = med.category.emoji, fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = med.category.displayName,
                                style = MaterialTheme.typography.labelSmall,
                                color = TextPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = med.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = med.genericName,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextMuted
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (med.brand.isNotEmpty()) {
                        Text(
                            text = "Brand: ${med.brand}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = LimeGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = med.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSoft,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun borderStroke(color: Color) = androidx.compose.foundation.BorderStroke(1.dp, color)
