package com.bondhu.pharmacy.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bondhu.pharmacy.data.model.MedicineCategory
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun CategoryChip(
    category: MedicineCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) LimeGreen else PanelDark2,
        label = "chipBgColor"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF0D1A00) else TextPrimary,
        label = "chipTextColor"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) LimeGreen else BorderColor,
        label = "chipBorderColor"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (category != MedicineCategory.ALL) {
                Text(text = category.emoji)
                Spacer(Modifier.width(6.dp))
            }
            Text(
                text = category.displayName,
                style = MaterialTheme.typography.labelLarge,
                color = textColor,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}
