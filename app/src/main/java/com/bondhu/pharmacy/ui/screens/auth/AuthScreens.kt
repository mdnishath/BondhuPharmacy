package com.bondhu.pharmacy.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bondhu.pharmacy.navigation.Screen
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val authError by viewModel.authError.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            viewModel.resetState()
            navController.navigate(Screen.Home.route) {
                popUpTo(0) // Clear backstack
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "স্বাগতম বন্ধু!",
            style = MaterialTheme.typography.headlineLarge,
            color = LimeGreen,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "আপনার অ্যাকাউন্টে লগইন করুন",
            style = MaterialTheme.typography.bodyLarge,
            color = TextMuted
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (authError != null) {
            Text(
                text = authError!!,
                color = ErrorColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PanelDark2,
                unfocusedContainerColor = PanelDark,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedIndicatorColor = LimeGreen,
                unfocusedIndicatorColor = BorderColor,
                focusedLabelColor = LimeGreen,
                unfocusedLabelColor = TextMuted,
                focusedLeadingIconColor = LimeGreen,
                unfocusedLeadingIconColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = viewModel::updatePassword,
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PanelDark2,
                unfocusedContainerColor = PanelDark,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedIndicatorColor = LimeGreen,
                unfocusedIndicatorColor = BorderColor,
                focusedLabelColor = LimeGreen,
                unfocusedLabelColor = TextMuted,
                focusedLeadingIconColor = LimeGreen,
                unfocusedLeadingIconColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login() },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LimeGreen,
                contentColor = Color(0xFF0D1A00)
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF0D1A00), modifier = Modifier.size(24.dp))
            } else {
                Text("Login", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
            Text(" OR ", color = TextMuted, modifier = Modifier.padding(horizontal = 8.dp))
            HorizontalDivider(modifier = Modifier.weight(1f), color = BorderColor)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Google Login Placeholder
        OutlinedButton(
            onClick = { /* TODO: Implement actual Google Auth */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextPrimary
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Text("Continue with Google", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Text("অ্যাকাউন্ট নেই? ", color = TextMuted)
            Text(
                "সাইন আপ করুন",
                color = LimeGreen,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    viewModel.resetState()
                    navController.navigate(Screen.Signup.route)
                }
            )
        }
    }
}

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val authError by viewModel.authError.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            viewModel.resetState()
            navController.navigate(Screen.Home.route) {
                popUpTo(0)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "নতুন অ্যাকাউন্ট",
            style = MaterialTheme.typography.headlineLarge,
            color = LimeGreen,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "আপনার বিস্তারিত তথ্য দিয়ে যুক্ত হোন",
            style = MaterialTheme.typography.bodyLarge,
            color = TextMuted
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (authError != null) {
            Text(
                text = authError!!,
                color = ErrorColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PanelDark2,
                unfocusedContainerColor = PanelDark,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedIndicatorColor = LimeGreen,
                unfocusedIndicatorColor = BorderColor,
                focusedLabelColor = LimeGreen,
                unfocusedLabelColor = TextMuted,
                focusedLeadingIconColor = LimeGreen,
                unfocusedLeadingIconColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = viewModel::updatePassword,
            label = { Text("Password (Min 6 chars)") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PanelDark2,
                unfocusedContainerColor = PanelDark,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedIndicatorColor = LimeGreen,
                unfocusedIndicatorColor = BorderColor,
                focusedLabelColor = LimeGreen,
                unfocusedLabelColor = TextMuted,
                focusedLeadingIconColor = LimeGreen,
                unfocusedLeadingIconColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.signup() },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LimeGreen,
                contentColor = Color(0xFF0D1A00)
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF0D1A00), modifier = Modifier.size(24.dp))
            } else {
                Text("Sign Up", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Text("আগে থেকে অ্যাকাউন্ট আছে? ", color = TextMuted)
            Text(
                "লগইন করুন",
                color = LimeGreen,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    viewModel.resetState()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
