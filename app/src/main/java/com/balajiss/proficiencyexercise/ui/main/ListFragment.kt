package com.balajiss.proficiencyexercise.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.di.ViewModelFactory
import com.balajiss.proficiencyexercise.network.NetworkResource
import com.balajiss.proficiencyexercise.ui.ProficiencyFragment
import com.balajiss.proficiencyexercise.utils.Constants
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ListFragment @Inject constructor() : ProficiencyFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var listView: RecyclerView
    private lateinit var noDataTV: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var titleListener: TitleListener
    private lateinit var dataSnackbar: Snackbar

    @Inject
    lateinit var listAdapter: ListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun layoutRes() = R.layout.list_fragment

    override fun onAttach(context: Context) {
        super.onAttach(context)
        titleListener = context as TitleListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        view?.let {
            listView = view.findViewById(R.id.list)
            swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
            noDataTV = view.findViewById(R.id.noData)

            initViews()
        }

        if (savedInstanceState != null) {
            savedInstanceState.getString(Constants.HEADER)?.let { titleListener.setTitle(it) }
        }

        return view
    }

    private fun initViews() {
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = listAdapter
        listView.addItemDecoration(
            DividerItemDecoration(
                listView.context,
                (listView.layoutManager as LinearLayoutManager).orientation
            )
        )

        populateData()

        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeData()
    }

    private fun fetchData() {
        viewModel.fetchData()
    }

    private fun observeData() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                NetworkResource.Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                NetworkResource.Status.SUCCESS -> {
                    it.data?.let { data ->
                        viewModel.pageTitle = data.title
                        if (::titleListener.isInitialized) {
                            titleListener.setTitle(viewModel.pageTitle!!)
                        }
                        viewModel.filterData(data.rows)
                        populateData()
                        dismissProgress()
                    }
                }
                NetworkResource.Status.ERROR -> {
                    dismissProgress()
                    var message: String = if (it.throwable != null) {
                        it.throwable.message ?: getString(R.string.something_went_wrong)
                    } else {
                        getString(R.string.something_went_wrong)
                    }
                    showDataList()
                    dataSnackbar =
                        Snackbar.make(view!!, message, Snackbar.LENGTH_INDEFINITE).apply {
                            setAction(getString(R.string.retry)) {
                                fetchData()
                            }
                            show()
                        }
                }
            }
        })
    }

    private fun showDataList() {
        if (listAdapter.data.size > 0) {
            noDataTV.visibility = View.GONE
            listView.visibility = View.VISIBLE
        } else {
            listView.visibility = View.GONE
            noDataTV.visibility = View.VISIBLE
        }
    }

    private fun populateData() {
        listAdapter.data.clear()
        listAdapter.data.addAll(viewModel.getData())
        showDataList()
        listAdapter.notifyDataSetChanged()
    }

    private fun dismissProgress() {
        if (::dataSnackbar.isInitialized && dataSnackbar.isShown) {
            dataSnackbar.dismiss()
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> fetchData()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.pageTitle?.let {
            outState.putString(Constants.HEADER, it)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.liveData.removeObservers(viewLifecycleOwner)
    }
}
