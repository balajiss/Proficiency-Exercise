package com.balajiss.proficiencyexercise.data.main

data class DataResponse(val title: String, val rows: List<DataResponseItem>)

data class DataResponseItem(val title: String, val description: String, val imageHref: String)