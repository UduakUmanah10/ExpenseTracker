package com.example.expenseapp.components

import android.content.res.Configuration
import android.view.Surface
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expenseapp.R
import com.example.expenseapp.ui.theme.ExpenseAppTheme
import com.example.expenseapp.ui.theme.FillTertiary
import com.example.expenseapp.ui.theme.Shapes
import com.example.expenseapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickerTrigger(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit

) {
    Surface(
        shape = Shapes.medium,
        color = FillTertiary,
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = Typography.titleMedium)
            Icon(
                painter = painterResource(R.drawable.unfold_more),
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PickerPreview() {
    ExpenseAppTheme {
        Surface() {
            PickerTrigger(label = "this week", onClick = {})
        }
    }
}
