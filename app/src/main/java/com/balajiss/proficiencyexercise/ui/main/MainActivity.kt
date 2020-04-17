package com.balajiss.proficiencyexercise.ui.main

import android.os.Bundle
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.ui.ProficiencyActivity
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
