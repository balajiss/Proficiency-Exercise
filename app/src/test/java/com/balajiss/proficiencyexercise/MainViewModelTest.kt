package com.balajiss.proficiencyexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.balajiss.proficiencyexercise.data.main.DataResponse
import com.balajiss.proficiencyexercise.data.main.ListRepository
import com.balajiss.proficiencyexercise.data.main.NetworkService
import com.balajiss.proficiencyexercise.network.SchedulerProvider
import com.balajiss.proficiencyexercise.ui.main.MainViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class MainViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var listRepository: ListRepository
    private lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var networkService: NetworkService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline())

        listRepository = ListRepository(networkService, schedulerProvider)
        mainViewModel = MainViewModel(listRepository)
    }

    @Test
    fun testData() {
        Mockito.`when`(networkService.getData())
            .thenReturn(Observable.just(DataResponse("Test", ArrayList())))

        assertEquals(
            DataResponse("Test", ArrayList()),
            mainViewModel.getData().getOrAwaitValue().data
        )
    }

    @After
    fun tearDown() {

    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}