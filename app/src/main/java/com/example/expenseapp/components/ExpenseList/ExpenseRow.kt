package com.example.expenseapp.components.ExpenseList

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expenseapp.ui.theme.LabelSecondary
import com.example.expenseapp.ui.theme.Typography
import com.example.expenseapp.viewModels.models.Expense
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseRow(
    expense: Expense,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top= 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(expense.note ?: expense.category.name, style = Typography.headlineMedium)

            Text(
                "USD ${ DecimalFormat("0.#").format(expense.amount)}",
                style = Typography.headlineMedium
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryBadge(category = expense.category)
            Text(
                expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = Typography.bodyMedium,
                color = LabelSecondary
            )
        }
    }
}
