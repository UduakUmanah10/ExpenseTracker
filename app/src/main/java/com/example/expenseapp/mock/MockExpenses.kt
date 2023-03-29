package com.example.expenseapp.mock

import androidx.compose.ui.graphics.Color
import com.example.expenseapp.viewModels.Category
import com.example.expenseapp.viewModels.models.Expense
import com.example.expenseapp.viewModels.models.Recurrence
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

val faker = Faker()
val mockExpense: List<Expense> = List(30) { index ->
    Expense(
        id = index,
        amount = faker.random.nextInt(min = 1, max = 999).toDouble() + faker.random.nextDouble(),

        date = LocalDateTime.now().minus(
            faker.random.nextInt(min = 300, max = 345600).toLong(),
            ChronoUnit.SECONDS
        ),

        recurrence = faker.random.randomValue(listOf(Recurrence.None, Recurrence.Daily, Recurrence.Weekly, Recurrence.monthly, Recurrence.yearly)),
        note = faker.australia.animals(),
        category = faker.random.randomValue(
            listOf(
                Category("Bills", Color.Red),
                Category("Subscription", Color.Yellow),
                Category("Take out", Color.Blue),
                Category("Bills", Color.Cyan)

            )

        )

    )
}
