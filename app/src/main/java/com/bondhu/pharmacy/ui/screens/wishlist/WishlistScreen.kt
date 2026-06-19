package com.bondhu.pharmacy.ui.screens.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.components.BondhuTopAppBar
import com.bondhu.pharmacy.ui.components.EmptyStateMessage
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun WishlistScreen(
    navController: NavController,
    viewModel: WishlistViewModel = viewModel()
) {
    val wishlistItems by viewModel.wishlistItems.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BondhuTopAppBar(title = "আপনার পছন্দ")

        if (wishlistItems.isEmpty()) {
            EmptyStateMessage("আপনার পছন্দ তালিকা খালি")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(wishlistItems) { medicine ->
                    WishlistItemRow(
                        medicine = medicine,
                        onRemove = { viewModel.removeFromWishlist(medicine.id) },
                        onMoveToCart = { viewModel.moveToCart(medicine) },
                        onClick = { navController.navigate(Screen.MedicineDetail.createRoute(medicine.id)) }
                    )
                }
            }
        }
    }
}

@Composable
fun WishlistItemRow(
    medicine: Medicine,
    onRemove: () -> Unit,
    onMoveToCart: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PanelDark),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PanelDark2)
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                        .clickable { onClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = medicine.category.emoji, fontSize = 28.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onClick() }
                ) {
                    Text(
                        text = medicine.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = medicine.genericName,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "৳ ${String.format("%.2f", medicine.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = LimeGreen,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = ErrorColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onMoveToCart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LimeGreenAlpha,
                    contentColor = LimeGreen
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Move to Cart",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
