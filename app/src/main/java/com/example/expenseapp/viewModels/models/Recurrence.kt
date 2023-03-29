package com.example.expenseapp.viewModels.models

sealed class Recurrence(val name: String, val trrget: String) {
    object None : Recurrence("None", "None")
    object Daily : Recurrence("Daily", "Today")
    object Weekly : Recurrence("Weekly", "This Week")
    object monthly : Recurrence("Monthly", "This Month")
    object yearly : Recurrence("Yearly", "This year")
}
