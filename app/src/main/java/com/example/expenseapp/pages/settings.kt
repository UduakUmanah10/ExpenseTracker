package com.example.expenseapp.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expenseapp.components.TableRow
import com.example.expenseapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController?) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarColorBackGround
                )
            )
        }

    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(Shapes.large)
                    .background(BackgroundElevted)
                    .fillMaxWidth()

            ) {
                TableRow(
                    label = "Categories",
                    hasArrow = true,
                    modifier = Modifier.clickable {
                        navController?.navigate("settingscategory")
                    }
                )
                Divider(
                    thickness = 1.dp,
                    color = DividerColor
                )
                TableRow(label = "Erase all Data", isDestructive = true, hasArrow = false)
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
fun DefaultPreview() {
    ExpenseAppTheme {
        Surface() {
            Settings(navController = null)
        }
    }
}
