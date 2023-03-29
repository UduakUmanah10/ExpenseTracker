package com.example.expenseapp.components.ExpenseList

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expenseapp.ui.theme.Shapes
import com.example.expenseapp.ui.theme.Typography
import com.example.expenseapp.viewModels.Category

@Composable
fun CategoryBadge(category: Category, modifier: Modifier = Modifier) {
    Surface(

        shape = Shapes.large,
        color = category.color.copy(alpha = 0.25f),
        modifier = modifier
    ) {
        Text(
            category.name,
            color = category.color,
            style = Typography.bodySmall,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}
