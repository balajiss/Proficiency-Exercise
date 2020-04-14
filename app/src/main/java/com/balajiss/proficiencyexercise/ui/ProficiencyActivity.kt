package com.balajiss.proficiencyexercise.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class ProficiencyActivity : DaggerAppCompatActivity() {

    abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutRes())
    }
}