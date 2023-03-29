@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.expenseapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expenseapp.components.ExpenseList.ExpenseList
import com.example.expenseapp.components.charts.WeeklyCharts
import com.example.expenseapp.mock.mockExpense
import com.example.expenseapp.ui.theme.LabelSecondary
import com.example.expenseapp.ui.theme.TopAppBarColorBackGround
import com.example.expenseapp.ui.theme.Typography

@Composable
fun Reports() {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Reports") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarColorBackGround
                )

            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        "12 sep - 18 sep",
                        style = Typography.titleSmall
                    )
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Text(
                            "USD",
                            style = Typography.bodyMedium,
                            color = LabelSecondary,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text("85", style = Typography.headlineMedium)
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End

                ) {
                    Text("Avg/day", style = Typography.titleSmall)
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Text("USD", style = Typography.bodyMedium, color = LabelSecondary)
                        Text("85", style = Typography.headlineMedium)
                    }
                }
            }

            Box(modifier= Modifier.padding(vertical = 16.dp)) {
                WeeklyCharts(expenses = mockExpense)
            }

            ExpenseList(
                expenses = mockExpense,
                modifier = Modifier.weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
        }
    }
}
