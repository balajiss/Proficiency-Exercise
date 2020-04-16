package com.balajiss.proficiencyexercise.ui.main

import androidx.lifecycle.ViewModel
import com.balajiss.proficiencyexercise.data.main.ListRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {

    fun getData() = listRepository.getData()
}
