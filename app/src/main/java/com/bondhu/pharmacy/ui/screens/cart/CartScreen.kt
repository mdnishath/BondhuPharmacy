package com.bondhu.pharmacy.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
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
import com.bondhu.pharmacy.data.model.CartItem
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.components.BondhuTopAppBar
import com.bondhu.pharmacy.ui.components.EmptyStateMessage
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalAmount = viewModel.getTotalAmount()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BondhuTopAppBar(title = "আপনার কার্ট")

        if (cartItems.isEmpty()) {
            EmptyStateMessage("আপনার কার্ট খালি")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems) { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.updateQuantity(item.medicine.id, item.quantity + 1) },
                        onDecrease = { viewModel.updateQuantity(item.medicine.id, item.quantity - 1) },
                        onRemove = { viewModel.removeItem(item.medicine.id) },
                        onClick = { navController.navigate(Screen.MedicineDetail.createRoute(item.medicine.id)) }
                    )
                }
            }

            // Checkout Bottom Bar
            Surface(
                color = PanelDark,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "সর্বমোট:",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary
                        )
                        Text(
                            text = "৳ ${String.format("%.2f", totalAmount)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = LimeGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate(Screen.Checkout.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LimeGreen,
                            contentColor = Color(0xFF0D1A00)
                        )
                    ) {
                        Text(
                            text = "চেকআউট করুন",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PanelDark),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Emoji Category placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PanelDark2)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.medicine.category.emoji, fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick() }
            ) {
                Text(
                    text = item.medicine.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.medicine.genericName,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "৳ ${String.format("%.2f", item.totalPrice)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = LimeGreen,
                    fontWeight = FontWeight.Bold
                )
            }

            // Quantity Control
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = ErrorColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(PanelDark2, RoundedCornerShape(8.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                        .width(80.dp)
                ) {
                    Icon(
                        Icons.Default.Remove,
                        contentDescription = "-",
                        tint = TextPrimary,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDecrease() }
                            .padding(4.dp)
                    )

                    Text(
                        text = item.quantity.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = LimeGreen,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        Icons.Default.Add,
                        contentDescription = "+",
                        tint = LimeGreen,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onIncrease() }
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
