package com.example.expenseapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.expenseapp.R
import com.example.expenseapp.ui.theme.Destructive
import com.example.expenseapp.ui.theme.TextPrimary
import com.example.expenseapp.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TableRow(
    modifier: Modifier = Modifier,
    label: String? = null,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    details: (@Composable RowScope.() -> Unit)? = null,
    Ordinarydetails: (@Composable RowScope.() -> Unit)? = null

) {
    val textColor = if (isDestructive) Destructive else TextPrimary

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (label != null) {
            Text(
                text = " $label",
                style = Typography.bodyMedium,
                color = textColor,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        if (Ordinarydetails != null) {
            Ordinarydetails()
        }

        if (details !== null) {
            details()
        }

        if (hasArrow) {
            Icon(
                painterResource(id = R.drawable.chevron_right),
                contentDescription = "right Click",
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}
