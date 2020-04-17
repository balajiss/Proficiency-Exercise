package com.balajiss.proficiencyexercise.network

import io.reactivex.Observable
import io.reactivex.Scheduler

/*
* SchedulerProvider class helps in defining performing operations on different threads.
*
* @param backgroundScheduler - Background scheduler is where the operation is been performed
* @param foregroundProvider - Foreground scheduler is where processing of data happens
* */
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