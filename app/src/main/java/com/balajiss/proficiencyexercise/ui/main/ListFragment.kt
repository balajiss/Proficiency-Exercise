package com.balajiss.proficiencyexercise.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.di.ViewModelFactory
import com.balajiss.proficiencyexercise.network.NetworkResource
import com.balajiss.proficiencyexercise.ui.ProficiencyFragment
import javax.inject.Inject

class ListFragment @Inject constructor() : ProficiencyFragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun layoutRes() = R.layout.list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        fetchData()
    }

    private fun fetchData() {
        viewModel.getData().observe(viewLifecycleOwner, Observer{
            when(it.status) {
                NetworkResource.Status.LOADING -> {

                }
                NetworkResource.Status.SUCCESS -> {

                }
                NetworkResource.Status.ERROR -> {

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.getData().removeObservers(viewLifecycleOwner)
    }
}
