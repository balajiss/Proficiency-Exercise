package com.balajiss.proficiencyexercise.data.main

/*
* Data class to create data object from response
* */

data class DataResponse(val title: String, val rows: List<DataResponseItem>)

data class DataResponseItem(val title: String?, val description: String?, val imageHref: String?)