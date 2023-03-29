package com.example.expenseapp.components.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.expenseapp.ui.theme.LabelSecondary
import com.example.expenseapp.viewModels.models.Expense
import com.example.expenseapp.viewModels.models.groupedByDayofWeeks
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import java.time.DayOfWeek

@Composable
fun WeeklyCharts(
    expenses: List<Expense>
) {
    val groupedExpenses = expenses.groupedByDayofWeeks()

    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                Bar(
                    label = DayOfWeek.MONDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.MONDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                Bar(
                    label = DayOfWeek.TUESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                Bar(
                    label = DayOfWeek.WEDNESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.WEDNESDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                Bar(
                    label = DayOfWeek.THURSDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                Bar(
                    label = DayOfWeek.FRIDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.FRIDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                )

            )

        ),
        modifier = Modifier.height(147.dp).padding(bottom = 20.dp).fillMaxWidth(),

        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.XAxis,
            labelTextColor = LabelSecondary
        ),

        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = LabelSecondary
        ),
        barDrawer = BarDrawer(),


    )
}
