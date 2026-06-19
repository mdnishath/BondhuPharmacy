package com.bondhu.pharmacy.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.bondhu.pharmacy.ui.theme.*

@Composable
fun BondhuSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    placeholder: String = "ওষুধ খুঁজুন...",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PanelDark2, RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector        = Icons.Default.Search,
                contentDescription = "Search",
                tint               = if (query.isNotEmpty()) LimeGreen else TextMuted,
                modifier           = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(10.dp))

            Box(modifier = Modifier.weight(1f)) {
                if (query.isEmpty()) {
                    Text(
                        text  = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                }
                BasicTextField(
                    value         = query,
                    onValueChange = onQueryChange,
                    textStyle     = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                    cursorBrush   = SolidColor(LimeGreen),
                    singleLine    = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {}),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter   = fadeIn(),
                exit    = fadeOut()
            ) {
                IconButton(
                    onClick  = onClear,
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector        = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint               = TextMuted,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
