package com.bondhu.pharmacy.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bondhu.pharmacy.ui.theme.*
import com.bondhu.pharmacy.updater.UpdateViewModel

@Composable
fun ProfileScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignup: () -> Unit,
    profileViewModel: ProfileViewModel = viewModel(),
    updateViewModel: UpdateViewModel = viewModel()
) {
    val currentUser by profileViewModel.currentUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "প্রোফাইল",
            style = MaterialTheme.typography.headlineMedium,
            color = LimeGreen,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        if (currentUser != null) {
            // Logged in UI
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(PanelDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(60.dp),
                    tint = LimeGreen
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentUser?.displayName ?: "User",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = currentUser?.email ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Check for Updates
            Button(
                onClick = { updateViewModel.checkForUpdates() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PanelDark),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Update, contentDescription = null, tint = LimeGreen)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Check for Updates", color = TextPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logout
            Button(
                onClick = { profileViewModel.logout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ErrorColor.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null, tint = ErrorColor)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Log Out", color = ErrorColor)
            }
            
        } else {
            // Logged out UI
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(PanelDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(80.dp),
                    tint = TextMuted
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Welcome to Bondhu Pharmacy!",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Login to manage your profile and orders.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LimeGreen)
            ) {
                Text("লগইন (Login)", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onNavigateToSignup,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = LimeGreen)
            ) {
                Text("রেজিস্টার (Signup)", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
