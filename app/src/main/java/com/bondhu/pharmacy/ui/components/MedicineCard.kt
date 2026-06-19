package com.bondhu.pharmacy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.bondhu.pharmacy.data.model.Medicine
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun MedicineCard(
    medicine: Medicine,
    isInCart: Boolean,
    isInWishlist: Boolean,
    onAddToCart: () -> Unit,
    onToggleWishlist: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Bounce animation on wishlist toggle
    var wishlistAnimTrigger by remember { mutableStateOf(false) }
    val heartScale by animateFloatAsState(
        targetValue = if (wishlistAnimTrigger) 1.4f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        finishedListener = { wishlistAnimTrigger = false },
        label = "heartScale"
    )

    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape  = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PanelDark),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(14.dp)) {

                // ── Category Emoji Badge ──────────────────────────────────
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.radialGradient(
                                listOf(LimeGreenAlpha, Color.Transparent)
                            )
                        )
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text     = medicine.category.emoji,
                        fontSize = 24.sp
                    )
                }

                Spacer(Modifier.height(10.dp))

                // ── Medicine Name ────────────────────────────────────────
                Text(
                    text       = medicine.name,
                    style      = MaterialTheme.typography.titleSmall,
                    color      = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )

                // ── Generic Name ─────────────────────────────────────────
                Text(
                    text     = medicine.genericName,
                    style    = MaterialTheme.typography.bodySmall,
                    color    = TextMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                // ── Price ─────────────────────────────────────────────────
                Text(
                    text       = "৳ ${String.format("%.2f", medicine.price)}",
                    style      = MaterialTheme.typography.titleMedium,
                    color      = LimeGreen,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                // ── Stock Badge ──────────────────────────────────────────
                if (!medicine.inStock) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = ErrorColor.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text     = "Out of Stock",
                            style    = MaterialTheme.typography.labelSmall,
                            color    = ErrorColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ── Add to Cart Button ────────────────────────────────────
                Button(
                    onClick = { 
                        onAddToCart()
                        Toast.makeText(context, "🛒 ${medicine.name} কার্টে যোগ করা হয়েছে!", Toast.LENGTH_SHORT).show()
                    },
                    enabled = medicine.inStock,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape  = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isInCart) PanelDark2 else LimeGreen,
                        contentColor   = if (isInCart) LimeGreen else Color(0xFF0D1A00),
                        disabledContainerColor = BorderLight,
                        disabledContentColor   = TextMuted
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector  = if (isInCart) Icons.Default.CheckCircle else Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text  = if (isInCart) "Added" else "Add",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // ── Wishlist Heart ───────────────────────────────────────────
            IconButton(
                onClick = {
                    wishlistAnimTrigger = true
                    onToggleWishlist()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(32.dp)
                    .scale(heartScale)
            ) {
                Icon(
                    imageVector  = if (isInWishlist) Icons.Default.Favorite
                                   else Icons.Default.FavoriteBorder,
                    contentDescription = "Wishlist",
                    tint   = if (isInWishlist) LimeGreen else TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
