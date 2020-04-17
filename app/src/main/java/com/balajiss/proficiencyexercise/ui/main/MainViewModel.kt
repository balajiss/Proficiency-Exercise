package com.balajiss.proficiencyexercise.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.balajiss.proficiencyexercise.data.main.DataResponse
import com.balajiss.proficiencyexercise.data.main.DataResponseItem
import com.balajiss.proficiencyexercise.data.main.ListRepository
import com.balajiss.proficiencyexercise.network.NetworkResource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {

    val liveData = MediatorLiveData<NetworkResource<DataResponse>>()
    private val filteredData = ArrayList<DataResponseItem>()
    var pageTitle: String? = null

    fun fetchData() {
        liveData.addSource(listRepository.getData()) {
            liveData.value = it
        }
    }

    fun filterData(data: List<DataResponseItem>): ArrayList<DataResponseItem> {
        filteredData.clear()
        data.forEach { item ->
            item.title?.let { filteredData.add(item) }
        }
        return filteredData
    }

    fun getData(): List<DataResponseItem> = filteredData
}
