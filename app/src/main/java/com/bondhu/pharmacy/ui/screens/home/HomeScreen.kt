package com.bondhu.pharmacy.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.R
import com.bondhu.pharmacy.data.model.MedicineCategory
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.components.CategoryChip
import com.bondhu.pharmacy.ui.components.MedicineCard
import com.bondhu.pharmacy.ui.theme.LimeGreen
import com.bondhu.pharmacy.ui.theme.LimeGreenAlpha
import com.bondhu.pharmacy.ui.theme.PanelDark
import com.bondhu.pharmacy.ui.theme.TextMuted
import com.bondhu.pharmacy.ui.theme.TextPrimary

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val featuredMedicines by viewModel.featuredMedicines.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Hero Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(PanelDark, LimeGreenAlpha)
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "বন্ধু ফার্মেসি",
                    style = MaterialTheme.typography.headlineMedium,
                    color = LimeGreen,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "আপনার বিশ্বস্ত স্বাস্থ্য সঙ্গী\nদ্রুততম সময়ে ঔষধ ডেলিভারি",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = LimeGreen.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "📞 Emergency: 01797691153",
                        style = MaterialTheme.typography.labelLarge,
                        color = LimeGreen,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Categories
        Text(
            text = "ক্যাটাগরি",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MedicineCategory.entries.drop(1)) { category -> // Drop ALL
                CategoryChip(
                    category = category,
                    isSelected = false,
                    onClick = {
                        // Navigate to catalog
                        navController.navigate(Screen.Catalog.route)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Medicines
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "জনপ্রিয় ঔষধ",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "সব দেখুন",
                style = MaterialTheme.typography.labelLarge,
                color = LimeGreen,
                modifier = Modifier.clickable { navController.navigate(Screen.Catalog.route) }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(featuredMedicines) { medicine ->
                MedicineCard(
                    medicine = medicine,
                    isInCart = viewModel.isInCart(medicine.id),
                    isInWishlist = viewModel.isInWishlist(medicine.id),
                    onAddToCart = { viewModel.addToCart(medicine) },
                    onToggleWishlist = { viewModel.toggleWishlist(medicine) },
                    onCardClick = { navController.navigate(Screen.MedicineDetail.createRoute(medicine.id)) },
                    modifier = Modifier.width(180.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
