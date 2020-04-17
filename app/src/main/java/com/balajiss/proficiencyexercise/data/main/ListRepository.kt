package com.balajiss.proficiencyexercise.data.main

import androidx.lifecycle.MutableLiveData
import com.balajiss.proficiencyexercise.network.NetworkResource
import com.balajiss.proficiencyexercise.network.SchedulerProvider
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val networkService: NetworkService,
    private val schedulerProvider: SchedulerProvider
) {

    /*
    * Method which fetch data from server and returns a livedata object
    * */
    fun getData(): MutableLiveData<NetworkResource<DataResponse>> {
        val liveData = MutableLiveData<NetworkResource<DataResponse>>()
        networkService.getData()
            .compose(schedulerProvider.getSchedulersForObservable())
            .subscribe(object : Observer<DataResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    liveData.value = NetworkResource.loading(null)
                }

                override fun onNext(t: DataResponse) {
                    liveData.value = NetworkResource.success(t)
                }

                override fun onError(e: Throwable) {
                    liveData.value = NetworkResource.error(e, null)
                }
            })
        return liveData
    }
}