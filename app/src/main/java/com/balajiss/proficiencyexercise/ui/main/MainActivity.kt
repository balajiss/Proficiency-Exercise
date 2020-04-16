package com.balajiss.proficiencyexercise.ui.main

import android.os.Bundle
import android.util.Log
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.data.main.NetworkService
import com.balajiss.proficiencyexercise.ui.ProficiencyActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : ProficiencyActivity() {

    private val TAG = MainActivity::class.simpleName

    @Inject
    lateinit var listFragment: ListFragment

    override fun layoutRes() = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, listFragment)
                    .commitNow()
        }
    }
}
