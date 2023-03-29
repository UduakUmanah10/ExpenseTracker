package com.example.expenseapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.expenseapp.viewModels.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ExpensesState(
    val recurrences: Recurrence = Recurrence.Daily,
    val sumTotal: Float = 125.32f

)

class ExpensesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesState())
    val uiState: StateFlow<ExpensesState> = _uiState.asStateFlow()

    fun setRecurrence(recurrences: Recurrence) {
        _uiState.update { currentState ->
            currentState.copy(
                recurrences = recurrences

            )
        }
    }
}
