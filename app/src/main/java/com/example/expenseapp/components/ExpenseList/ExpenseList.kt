package com.example.expenseapp.components.ExpenseList

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expenseapp.mock.mockExpense
import com.example.expenseapp.ui.theme.ExpenseAppTheme
import com.example.expenseapp.viewModels.models.Expense
import com.example.expenseapp.viewModels.models.groupedByDay

@Composable
fun ExpenseList(
    expenses: List<Expense>,
    modifier: Modifier = Modifier
) {
    val groupedExpenses = expenses.groupedByDay()

    Column(modifier = modifier) {
        groupedExpenses.keys.forEach { date ->

            if (groupedExpenses[date] != null) {
                ExpenseDayGroup(
                    date = date,
                    dayExpenses = groupedExpenses[date]!!,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ExpensesList() {
    ExpenseAppTheme {
        Surface() {
            ExpenseList(mockExpense, modifier = Modifier)
        }
    }
}
