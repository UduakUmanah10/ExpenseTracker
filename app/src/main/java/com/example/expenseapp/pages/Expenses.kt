package com.example.expenseapp.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expenseapp.components.ExpenseList.ExpenseList
import com.example.expenseapp.components.PickerTrigger
import com.example.expenseapp.mock.mockExpense
import com.example.expenseapp.ui.theme.*
import com.example.expenseapp.ui.theme.Typography
import com.example.expenseapp.viewModels.ExpensesViewModel
import com.example.expenseapp.viewModels.models.Recurrence
import kotlin.text.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController, vm: ExpensesViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()

    var recurrenceMenuOpened by remember {
        mutableStateOf(false)
    }
    val recurrences1 = listOf<Recurrence>(
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.monthly,
        Recurrence.yearly
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarColorBackGround
                )

            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total For: ",
                    style = Typography.titleSmall
                )

                PickerTrigger(
                    modifier = Modifier.padding(start = 16.dp),
                    label = state.recurrences.trrget ?: Recurrence.None.trrget,
                    onClick = { recurrenceMenuOpened = !recurrenceMenuOpened }
                )
                // Text(state.recurrences.trrget ?: Recurrence.None.trrget, style = Typography.bodyMedium)
                DropdownMenu(
                    expanded = recurrenceMenuOpened,
                    onDismissRequest = { recurrenceMenuOpened = false }
                ) {
                    recurrences1.forEach { recurrences ->
                        DropdownMenuItem(
                            text = {
                                Text(text = recurrences.trrget, modifier = Modifier.padding(start = 8.dp))
                            },
                            onClick = {
                                vm.setRecurrence(recurrences)
                                recurrenceMenuOpened = false
                            }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "$",
                    style = Typography.bodySmall,
                    color = LabelSecondary,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "${state.sumTotal}", style = Typography.bodyLarge)

//
            }
            ExpenseList(
                expenses = mockExpense,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
//
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ExpensesPreview() {
    ExpenseAppTheme {
        Surface() {
            Expenses(navController = rememberNavController())
        }
    }
}
