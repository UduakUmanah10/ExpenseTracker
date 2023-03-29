package com.example.expenseapp.viewModels.models

import com.example.expenseapp.viewModels.Category
import java.time.LocalDate
import java.time.LocalDateTime

data class Expense(
    val id: Int,
    val recurrence: Recurrence,
    val amount: Double,
    val date: LocalDateTime,
    val note: String?,
    val category: Category
)

data class DayExpenses(
    val expenses: MutableList<Expense>,
    var total: Double
)

fun List<Expense>.groupedByDay(): Map<LocalDate, DayExpenses> {
    val dataMap: MutableMap<LocalDate, DayExpenses> = mutableMapOf()

    this.forEach { Expense ->
        val date = Expense.date.toLocalDate()
        if (dataMap[date] == null) {
            dataMap[date] = DayExpenses(
                expenses = mutableListOf(),
                total = 0.0
            )
        }
        dataMap[date]!!.expenses.add(Expense)
        dataMap[date]!!.total = dataMap[date]!!.total.plus(Expense.amount)
    }

    dataMap.values.forEach { dayExpenses ->
        dayExpenses.expenses.sortBy { expense ->
            expense.date
        }
    }

    return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupedByDayofWeeks(): Map<String, DayExpenses> {
    val dataMap: MutableMap<String, DayExpenses> = mutableMapOf()

    this.forEach { Expense ->

        val dayOfWeek = Expense.date.toLocalDate().dayOfWeek

        if (dataMap[dayOfWeek.name] == null) {
            dataMap[dayOfWeek.name] = DayExpenses(
                expenses = mutableListOf(),
                total = 0.0
            )
        }
        dataMap[dayOfWeek.name]!!.expenses.add(Expense)
        dataMap[dayOfWeek.name]!!.total = dataMap[dayOfWeek.name]!!.total.plus(Expense.amount)
    }

    return dataMap.toSortedMap(compareByDescending { it })
}
