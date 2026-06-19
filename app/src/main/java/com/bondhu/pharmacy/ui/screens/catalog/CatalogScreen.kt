package com.bondhu.pharmacy.ui.screens.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.data.model.MedicineCategory
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.components.BondhuSearchBar
import com.bondhu.pharmacy.ui.components.CategoryChip
import com.bondhu.pharmacy.ui.components.MedicineCard

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = viewModel()
) {
    val medicines by viewModel.medicines.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Search Bar
        Box(modifier = Modifier.padding(16.dp)) {
            BondhuSearchBar(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                onClear = { viewModel.onSearchQueryChange("") }
            )
        }

        // Categories
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MedicineCategory.entries) { category ->
                CategoryChip(
                    category = category,
                    isSelected = category == selectedCategory,
                    onClick = { viewModel.onCategorySelected(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Medicine Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(medicines) { medicine ->
                MedicineCard(
                    medicine = medicine,
                    isInCart = viewModel.isInCart(medicine.id),
                    isInWishlist = viewModel.isInWishlist(medicine.id),
                    onAddToCart = { viewModel.addToCart(medicine) },
                    onToggleWishlist = { viewModel.toggleWishlist(medicine) },
                    onCardClick = { navController.navigate(Screen.MedicineDetail.createRoute(medicine.id)) }
                )
            }
        }
    }
}
