package com.example.expenseapp.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Category(
    val name: String,
    val color: Color

)

data class CategoriesState(
    val newCategoryColor: Color = Color.White,
    val newCategoryName: String = "",
    val colorPickerShowing: Boolean = false,
    val categories: MutableList<Category> = mutableListOf(

        Category("Takeout", Color.Blue),
        Category("Bills", Color.Red),
        Category("Subscriptions", Color.Yellow)


    )

)

class categoriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState: StateFlow<CategoriesState> = _uiState.asStateFlow()

    fun setNewCategoryColor(color: Color) {
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryColor = color
            )
        }
    }

    fun setNewCategoryName(Name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryName = Name
            )
        }
    }

    fun deleteCategory(category: Category) {
        val index = _uiState.value.categories.indexOf(category)
        val newList = mutableListOf<Category>()
        newList.addAll(_uiState.value.categories)
        newList.removeAt(index)

        _uiState.update { currentState ->
            currentState.copy(
                categories = newList
            )
        }
    }

    fun CreateNewDb() {
        // /
    }

    fun ShowColorPicker() {
        _uiState.update { CategoriesState ->
            CategoriesState.copy(colorPickerShowing = true)
        }
    }
    fun HideColorPicker() {
        _uiState.update { CategoriesState ->
            CategoriesState.copy(colorPickerShowing = false)
        }
    }

    fun createNewCategory() {
        val newCategoriesList = mutableListOf(
            Category(
                _uiState.value.newCategoryName,
                _uiState.value.newCategoryColor
            )

        )
        newCategoriesList.addAll(
            _uiState.value.categories

        )
        _uiState.update { currentState ->
            currentState.copy(
                categories = newCategoriesList,
                newCategoryColor = Color.White,
                newCategoryName = ""

            )
        }
    }
}
