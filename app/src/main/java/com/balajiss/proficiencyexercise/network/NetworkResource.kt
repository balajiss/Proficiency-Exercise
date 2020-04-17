package com.balajiss.proficiencyexercise.network

/*
* A generic class that contains status and data
* */
class NetworkResource<T> constructor(val status: Status, val data: T?, val throwable: Throwable?) {

    companion object {

        fun <T> success(data: T): NetworkResource<T> {
            return NetworkResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: Throwable, data: T?): NetworkResource<T> {
            return NetworkResource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): NetworkResource<T> {
            return NetworkResource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}