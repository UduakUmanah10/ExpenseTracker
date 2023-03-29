package com.example.expenseapp.pages

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expenseapp.R
import com.example.expenseapp.components.TableRow
import com.example.expenseapp.components.UnstledTextField
import com.example.expenseapp.ui.theme.*
import com.example.expenseapp.viewModels.categoriesViewModel
import com.github.skydoves.colorpicker.compose.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Categories(navController: NavController, vm: categoriesViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsState()
    val colorPickerController = rememberColorPickerController()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarColorBackGround
                ),
                navigationIcon = {
                    Row(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Rounded.KeyboardArrowLeft,
                            contentDescription = ""
                        )
                        Text(text = "Settings")
                    }
                }

            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)

            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(Shapes.large)
                        .background(BackgroundElevted)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(uiState.categories, key = { _, category -> category.name }) { index, category ->
                        val swipableState = rememberDismissState(

                            confirmStateChange = { value ->
                                if (value == DismissValue.valueOf("DismissedToStart")) {
                                    vm.deleteCategory(category)
                                    true
                                } else {
                                    false
                                }
                            }

                        )

                        SwipeToDismiss(
                            state = swipableState,
                            dismissContent = {
                                TableRow(modifier = Modifier.background(BackgroundElevted)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            color = category.color,
                                            modifier = Modifier.size(16.dp),
                                            shape = CircleShape,
                                            border = BorderStroke(
                                                width = 2.dp,
                                                color = Color.White

                                            )

                                        ) {}
                                        Text(
                                            category.name,
                                            modifier = Modifier.padding(
                                                horizontal = 16.dp,
                                                vertical = 10.dp
                                            ),
                                            style = Typography.bodyMedium

                                        )
                                    }
                                }
                            },
                            background = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .background(Color.Red),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Rounded.Delete, "Delete category")
                                    }
//
                                }
                            },
                            directions = setOf(DismissDirection.EndToStart)
                        )

                        if (index < uiState.categories.size - 1) {
                            Divider(
                                modifier = Modifier.padding(start = 16.dp),
                                thickness = 1.dp,
                                color = DividerColor
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (uiState.colorPickerShowing) {
                    Dialog(
                        onDismissRequest = vm::HideColorPicker
                    ) {
                        Surface(color = BackgroundElevted, shape = Shapes.large) {
                            Column(
                                // on below line we are adding a modifier to it,
                                modifier = Modifier
                                    // on below line we are adding a padding.
                                    .padding(all = 30.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.select_a_color),
                                    style = Typography.titleLarge
                                )
                                // on below line we are adding a row.
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // on below line we are adding a alpha tile.
                                    AlphaTile(
                                        // on below line we are
                                        // adding modifier to it
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            // on below line
                                            // we are adding a height.
                                            .height(60.dp)
                                            // on below line we are adding clip.
                                            .clip(RoundedCornerShape(6.dp)),
                                        // on below line we are adding controller.
                                        controller = colorPickerController
                                    )
                                }
                                // on below line we are
                                // adding horizontal color picker.
                                HsvColorPicker(
                                    // on below line we are
                                    // adding a modifier to it
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp)
                                        .padding(10.dp),
                                    // on below line we are
                                    // adding a controller
                                    controller = colorPickerController,
                                    // on below line we are
                                    // adding on color changed.
                                    onColorChanged = { envelope ->
                                        vm.setNewCategoryColor(envelope.color)
                                    }
                                )

                                BrightnessSlider(
                                    // on below line we
                                    // are adding a modifier to it.
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(35.dp),
                                    // on below line we are
                                    // adding a controller.
                                    controller = colorPickerController
                                )
                                TextButton(
                                    onClick = vm::HideColorPicker,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp)
                                ) {
                                    Text(stringResource(R.string.Done), color = primary)
//
                                }
                            }
                        }
//
                    }
                }
                Surface(
                    onClick = vm::ShowColorPicker,
                    shape = CircleShape,
                    color = uiState.newCategoryColor,
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                    border = BorderStroke(
                        width = 3.dp,
                        color = Color.White
                    )

                ) {}
                Surface(
                    color = BackgroundElevted,
                    modifier = Modifier
                        .height(60.dp)
                        .weight(1f)
                        .padding(start = 16.dp),
                    shape = Shapes.large
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        UnstledTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = uiState.newCategoryName,
                            onValueChange = vm::setNewCategoryName,
                            placeholder = { Text("Category name") }

                        )
                    }
                }
                IconButton(onClick = vm::createNewCategory, modifier = Modifier.padding(start = 16.dp)) {
                    Icon(Icons.Rounded.Send, contentDescription = "Send Icon")
//
                }
            }

//
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CategoryPreview() {
    ExpenseAppTheme {
        Surface() {
            Categories(navController = rememberNavController())
        }
    }
}
