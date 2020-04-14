package com.balajiss.proficiencyexercise.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.ui.ProficiencyFragment
import javax.inject.Inject

class ListFragment @Inject constructor() : ProficiencyFragment() {

    private lateinit var viewModel: MainViewModel

    override fun layoutRes() = R.layout.list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

}
