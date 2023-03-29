package com.example.expenseapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expenseapp.pages.*
import com.example.expenseapp.ui.theme.ExpenseAppTheme
import com.example.expenseapp.ui.theme.TopAppBarColorBackGround

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseAppTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                var showBottomBar by rememberSaveable { mutableStateOf(true) }

                showBottomBar = when (backStackEntry?.destination?.route) {
                    "settingscategory" -> true
                    else -> true
                }
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(containerColor = TopAppBarColorBackGround) {
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses") },
                                    label = {
                                        Text("Expenses")
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.upload), contentDescription = "upload") }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "Reports",
                                    onClick = { navController.navigate("Reports") },
                                    label = {
                                        Text("Reports")
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.barchart), contentDescription = "barChart") }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "Add",
                                    onClick = { navController.navigate("Add") },
                                    label = {
                                        Text("Add")
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.add), contentDescription = "upload") }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route?.startsWith("Settings") ?: false,
                                    onClick = { navController.navigate("Settings") },
                                    label = {
                                        Text("Setting")
                                    },
                                    icon = { Icon(painterResource(id = R.drawable.settings), contentDescription = "settings") }
                                )
//
                            }
                        }
                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses") {
                            composable("expenses") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    Expenses(navController = navController)
                                }
//
                            }
                            composable("Reports") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    Reports()
                                }
                            }
                            composable("Add") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    Add(navController)
                                }
                            }
                            composable("Settings") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    Settings(navController = navController)
                                }
                            }

                            composable("settingscategory") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) {
                                    Categories(navController)
                                }
                            }
                        }
                    }

                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello$name!")
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreview() {
    ExpenseAppTheme {
        Surface() {
            Greeting("Android")
        }
    }
}
