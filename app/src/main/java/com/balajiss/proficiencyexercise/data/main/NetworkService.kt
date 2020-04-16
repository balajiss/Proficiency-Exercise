package com.balajiss.proficiencyexercise.data.main

import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkService {

    @GET("s/2iodh4vg0eortkl/facts.js")
    fun getData(): Observable<DataResponse>
}