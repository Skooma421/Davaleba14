package com.example.dessertapp

import androidx.lifecycle.ViewModel
import com.example.davaleba14new.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DessertViewModel : ViewModel() {

    private val _desserts = MutableStateFlow<List<Dessert>>(emptyList())//updatable
    val desserts: StateFlow<List<Dessert>> = _desserts//readOnly

    private var dessertId = 0

    fun addDessert(name: String, imageRes: Int) {
        val newDessert = Dessert(id = dessertId++, name, imageRes)
        _desserts.value = _desserts.value + newDessert
    }

    fun removeDessert(dessert: Dessert) {
        _desserts.value = _desserts.value.filter { it.id != dessert.id }
    }

    fun refreshDesserts() {
        _desserts.value = emptyList()
    }
}

