package com.bondhu.pharmacy.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.components.BondhuTopAppBar
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModel: CheckoutViewModel = viewModel()
) {
    val name by viewModel.name.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val address by viewModel.address.collectAsState()
    val isSubmitting by viewModel.isSubmitting.collectAsState()
    val orderSuccess by viewModel.orderSuccess.collectAsState()
    val totalAmount = viewModel.totalAmount

    LaunchedEffect(orderSuccess) {
        orderSuccess?.let { orderId ->
            navController.navigate(Screen.OrderSuccess.createRoute(orderId)) {
                popUpTo(Screen.Home.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BondhuTopAppBar(
            title = "চেকআউট",
            onBackClick = { navController.popBackStack() }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Summary Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = PanelDark),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Order Summary",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Amount to Pay:",
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Payment Method: Cash on Delivery (COD)",
                        style = MaterialTheme.typography.bodySmall,
                        color = SuccessGreen
                    )
                }
            }

            Text(
                text = "Delivery Details",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = name,
                onValueChange = viewModel::updateName,
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PanelDark2,
                    unfocusedContainerColor = PanelDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedIndicatorColor = LimeGreen,
                    unfocusedIndicatorColor = BorderColor,
                    focusedLabelColor = LimeGreen,
                    unfocusedLabelColor = TextMuted
                )
            )

            OutlinedTextField(
                value = phone,
                onValueChange = viewModel::updatePhone,
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PanelDark2,
                    unfocusedContainerColor = PanelDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedIndicatorColor = LimeGreen,
                    unfocusedIndicatorColor = BorderColor,
                    focusedLabelColor = LimeGreen,
                    unfocusedLabelColor = TextMuted
                )
            )

            OutlinedTextField(
                value = address,
                onValueChange = viewModel::updateAddress,
                label = { Text("Full Delivery Address") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PanelDark2,
                    unfocusedContainerColor = PanelDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedIndicatorColor = LimeGreen,
                    unfocusedIndicatorColor = BorderColor,
                    focusedLabelColor = LimeGreen,
                    unfocusedLabelColor = TextMuted
                )
            )
        }

        Surface(
            color = PanelDark,
            shadowElevation = 8.dp
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                val isFormValid = name.isNotBlank() && phone.isNotBlank() && address.isNotBlank()
                Button(
                    onClick = { viewModel.placeOrder() },
                    enabled = isFormValid && !isSubmitting,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LimeGreen,
                        contentColor = Color(0xFF0D1A00),
                        disabledContainerColor = PanelDark2,
                        disabledContentColor = TextMuted
                    )
                ) {
                    if (isSubmitting) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = LimeGreen,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "Place Order",
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
fun OrderSuccessScreen(
    orderId: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(LimeGreenAlpha)
                .border(2.dp, LimeGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Success",
                tint = LimeGreen,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Order Placed Successfully!",
            style = MaterialTheme.typography.headlineSmall,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Order ID: $orderId",
            style = MaterialTheme.typography.bodyLarge,
            color = TextMuted
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "আমাদের প্রতিনিধি শীঘ্রই আপনার সাথে যোগাযোগ করবেন।",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSoft,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(0)
                }
            },
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
                text = "Back to Home",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
