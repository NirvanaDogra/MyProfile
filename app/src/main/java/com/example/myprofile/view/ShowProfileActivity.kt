package com.example.myprofile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.commons.view.dialog.DialogUtil
import com.example.mapsmodule.view.LocateMeActivity
import com.example.myprofile.R
import com.example.myprofile.databinding.ActivityShowProfileActivityBinding
import com.example.myprofile.viewmodel.ShowProfileEvent
import com.example.myprofile.viewmodel.ShowProfileState
import com.example.myprofile.viewmodel.ShowProfileViewModel


class ShowProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowProfileActivityBinding
    private lateinit var adapter: MainActivityAdapter
    private lateinit var viewModel: ShowProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_profile_activity)
        initializeView()
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.viewEvent.observe(this) {
            handleScreenEvents(it)
        }
        viewModel.viewState.observe(this) {
            handleScreenState(it)
        }
    }

    private fun handleScreenState(state: ShowProfileState) {
        if (state.isLoading) {
            binding.progressBarShowProfileLoading.visibility = View.VISIBLE
        } else {
            binding.progressBarShowProfileLoading.visibility = View.GONE
        }
    }

    private fun handleScreenEvents(event: ShowProfileEvent) {
        when (event) {
            is ShowProfileEvent.OnSuccess -> adapter.submitData(event.data)
            is ShowProfileEvent.OnNetworkError -> handleNetworkError()
            is ShowProfileEvent.ShowErrorDialog -> showErrorDialog()
            is ShowProfileEvent.FindMeClicked -> navigateToFindMePage()
            is ShowProfileEvent.DownNavigationButtonClicked -> showNextItem()
        }
    }

    private fun showNextItem() {
        with(binding.recyclerShowProfilePostsHolder) {
            val pos = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
            smoothScrollToPosition(pos + 1)
        }
    }

    private fun navigateToFindMePage() {
        Intent(this, LocateMeActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun showErrorDialog() {
        DialogUtil().showAlertDialog(this, "Service not available!\n Try again later!")
    }

    private fun handleNetworkError() {
        DialogUtil().showAlertDialog(
            context = this,
            title = "Network Error",
            message = "Please check your network",
        )
    }

    private fun initializeView() {
        viewModel = ViewModelProvider(this)[ShowProfileViewModel::class.java]
        adapter = MainActivityAdapter(listOf(), viewModel)
        binding.recyclerShowProfilePostsHolder.apply {
            layoutManager = LinearLayoutManager(this@ShowProfileActivity)
            this.adapter = this@ShowProfileActivity.adapter
            val helper: PagerSnapHelper = PagerSnapHelper()
            helper.attachToRecyclerView(this)
        }
        binding.viewmodel = viewModel
    }
}