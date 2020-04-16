package com.balajiss.proficiencyexercise.network

import io.reactivex.Observable
import io.reactivex.Scheduler

class SchedulerProvider constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundProvider: Scheduler
) {

    fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
        return { observable ->
            observable.subscribeOn(backgroundScheduler).observeOn(foregroundProvider)
        }
    }
}