package com.example.expenseapp.components.ExpenseList

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expenseapp.ui.theme.LabelSecondary
import com.example.expenseapp.ui.theme.Typography
import com.example.expenseapp.utils.formatDay
import com.example.expenseapp.viewModels.models.DayExpenses
import java.text.DecimalFormat
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun ExpenseDayGroup(date: LocalDate, dayExpenses: DayExpenses, modifier: Modifier = Modifier) {
    Column() {
        Text(
            date.formatDay(),
            style = Typography.headlineMedium,
            color = LabelSecondary
        )
        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))

        dayExpenses.expenses.forEach { expense ->
            ExpenseRow(expense, modifier = Modifier.padding(top = 12.dp))
        }

        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total", style = Typography.bodyMedium, color = LabelSecondary)
            Text(
                DecimalFormat("0.#").format(dayExpenses.total),
                style = Typography.bodyMedium,
                color = LabelSecondary
            )
        }
    }
}
