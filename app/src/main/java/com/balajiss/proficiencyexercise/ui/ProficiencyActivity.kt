package com.balajiss.proficiencyexercise.ui

import android.os.Bundle
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.ui.main.TitleListener
import dagger.android.support.DaggerAppCompatActivity

abstract class ProficiencyActivity : DaggerAppCompatActivity(), TitleListener {

    abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutRes())
    }

    override fun setTitle(title: String) {
        setActionBarTitle(title)
    }
}