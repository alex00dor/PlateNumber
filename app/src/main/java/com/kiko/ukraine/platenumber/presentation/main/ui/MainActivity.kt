package com.kiko.ukraine.platenumber.presentation.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.kiko.ukraine.platenumber.R
import com.kiko.ukraine.platenumber.databinding.ActivityMainBinding
import com.kiko.ukraine.platenumber.presentation.base.BaseActivity
import com.kiko.ukraine.platenumber.presentation.main.MainViewModel
import com.kiko.ukraine.platenumber.presentation.plateinfo.ui.PlateInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        val REQUEST_CODE_PLATE_EXIST = 101
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var recentAdapter: RecentAdapter

    lateinit var binding: ActivityMainBinding

    lateinit var viewMainModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewMainModel = getViewModel(viewModelFactory)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = viewMainModel

        viewMainModel.navigateToPlate.observe(this, Observer { if (it != null) navigateToPlateNumberInfo(it) })

        initRecentViews()
    }

    private fun initRecentViews() {
        recentAdapter = RecentAdapter(viewMainModel)

        binding.recentViewList.layoutManager = LinearLayoutManager(this)
        binding.recentViewList.adapter = recentAdapter

        viewMainModel.recentViewsData.observe(this, Observer { list -> recentAdapter.submitList(list) })
    }

    private fun navigateToPlateNumberInfo(plateNumber: String) {
        val intent = Intent(this, PlateInfoActivity::class.java)
        intent.putExtra(PlateInfoActivity.PLATE_NUMBER, plateNumber)
        startActivityForResult(intent, REQUEST_CODE_PLATE_EXIST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_PLATE_EXIST && resultCode == PlateInfoActivity.RESULT_CODE_NOT_EXIST){
            Snackbar.make(binding.coordinatorLayout, getString(R.string.cannot_find_plate_number), Snackbar.LENGTH_LONG).show()
        }
    }

}
