package com.example.expenseapp.pages

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.expenseapp.R
import com.example.expenseapp.components.TableRow
import com.example.expenseapp.viewModels.models.Recurrence
import com.example.expenseapp.ui.theme.*
import com.example.expenseapp.viewModels.AddViewModel
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import java.time.LocalDate
import java.util.*
import kotlin.text.Typography

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Add(navController: NavController?, addViewModel: AddViewModel = viewModel()) {
    val state by addViewModel.uiState.collectAsState()

    val recurrences = listOf<Recurrence>(
        Recurrence.None,
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.monthly,
        Recurrence.yearly
    )
    var selectedRecurrence by remember {
        mutableStateOf(recurrences[0].name)
    }

    val categories = listOf<String>("Groceries", "Bills", "Hobbies", "Take Out")

    var Category by remember {
        mutableStateOf(categories[0])
    }

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Add") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarColorBackGround
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(Shapes.large)
                    .background(BackgroundElevted)
                    .fillMaxWidth()

            ) {
                TableRow(label = "Amount", hasArrow = false) {
                    BasicTextField(
                        modifier = Modifier
                            .padding(3.dp)
                            .defaultMinSize(minWidth = 80.dp, minHeight = 44.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        value = state.amount,
                        onValueChange = {
                            addViewModel.setAmount(it)
                        },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = TextPrimary,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Right

                        ),
                        cursorBrush = SolidColor(primary),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number

                        )

                    )
                }
                Divider(
                    thickness = 1.dp,
                    color = DividerColor
                )
                TableRow(label ="Recurrences", hasArrow = false) {
                    var recurrenceMenuOpened by remember {
                        mutableStateOf(false)
                    }

                    TextButton(onClick = { recurrenceMenuOpened = true }, shape = Shapes.large) {
                        Text(state.recurrence?.name ?: Recurrence.None.name)
                        DropdownMenu(expanded = recurrenceMenuOpened, onDismissRequest = { recurrenceMenuOpened = false }) {
                            recurrences.forEach { recurrences ->
                                DropdownMenuItem(
                                    text = { Text(recurrences.name) },
                                    onClick = {
                                        addViewModel.setRecurrences(recurrences)
                                        recurrenceMenuOpened = false
                                    }
                                )
                            }
                        }
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = DividerColor
                )
                var datepicker by remember { mutableStateOf(false) }

                TableRow(label = "Date", hasArrow = false) {
                    TextButton(onClick = { datepicker = true }) {
                        Text(state.date.toString())
                    }
                    if (datepicker) {
                        DatePickerDialog(
                            onDismissRequest = { datepicker = false },
                            onDateChange = {
                                addViewModel.setDate(it)
                                datepicker = false
                            },
                            initialDate = LocalDate.now(),
                            title = { Text("Select Date", style= com.example.expenseapp.ui.theme.Typography.titleLarge) }
                        )
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = DividerColor
                )

                TableRow(label ="Categoy", hasArrow = false) {
                    var CategoryMenuOpened by remember {
                        mutableStateOf(false)
                    }
                    TextButton(onClick = { CategoryMenuOpened = true }, shape = Shapes.large) {
                        Text(state.category ?: "select a category first")
                        DropdownMenu(
                            expanded = CategoryMenuOpened,
                            onDismissRequest = { CategoryMenuOpened = false }
                        ) {
                            categories.forEach { recurrences ->
                                DropdownMenuItem(
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Surface(modifier = Modifier.size(10.dp), shape = CircleShape, color = primary) {
                                            }
                                            Text(text = recurrences, modifier = Modifier.padding(start = 8.dp))
                                        }
                                    },
                                    onClick = {
                                        addViewModel.setCategory(recurrences)
                                        CategoryMenuOpened = false
                                    }
                                )
                            }
                        }
                    }
                }

                Divider(
                    thickness = 1.dp,
                    color = DividerColor
                )
                TableRow(label = "Note", hasArrow = false) {
                    TextField(
                        value = state.note,
                        onValueChange = addViewModel::setNote,
                        placeholder = {
                            Text(
                                text = stringResource(
                                    R.string.placeholder
                                )
                            )
                        }
                    )
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp),
                shape = Shapes.large
            ) {
                Text("Submit expense")
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AddPreview() {
    ExpenseAppTheme {
        Surface() {
            Add(null)
        }
    }
}
