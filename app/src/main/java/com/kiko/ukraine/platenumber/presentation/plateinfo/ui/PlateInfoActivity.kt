package com.kiko.ukraine.platenumber.presentation.plateinfo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.flexbox.FlexboxLayoutManager
import com.kiko.ukraine.platenumber.R
import com.kiko.ukraine.platenumber.databinding.ActivityPlateInfoBinding
import com.kiko.ukraine.platenumber.domain.entity.PlateInfo
import com.kiko.ukraine.platenumber.presentation.base.BaseActivity
import com.kiko.ukraine.platenumber.presentation.plateinfo.PlateInfoViewModel
import com.kiko.ukraine.platenumber.presentation.plateinfo.entity.Info
import kotlinx.android.synthetic.main.activity_plate_info.*
import javax.inject.Inject


class PlateInfoActivity : BaseActivity() {

    companion object {
        const val PLATE_NUMBER = "platenumber"
        const val RESULT_CODE_NOT_EXIST = -1
        const val RESULT_CODE_EXIST = 1
    }

    lateinit var plateNumber: String
        private set

    private lateinit var plateInfoViewModel: PlateInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ActivityPlateInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plateNumber = intent.getStringExtra(PLATE_NUMBER)

        if (savedInstanceState?.getString(PLATE_NUMBER) != null)
            plateNumber = savedInstanceState.getString(PLATE_NUMBER)!!

        if (plateNumber.isEmpty())
            finish()

        plateInfoViewModel = getViewModel(viewModelFactory)
        plateInfoViewModel.setPlateNumber(plateNumber)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_plate_info)

        binding.apply {
            this.lifecycleOwner = this@PlateInfoActivity
            this.viewModel = plateInfoViewModel
        }

        initObservers()
    }

    private fun initObservers() {
        plateInfoViewModel.uiModel.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        initToolbar()
                        initInformationBlocks(it.data!!)
                        setResult(RESULT_CODE_EXIST)
                    }
                    Status.ERROR -> {
                        if(it.error == "PLATE_NOT_EXIST") {
                            setResult(RESULT_CODE_NOT_EXIST)
                            finish()
                        }
                    }
                }
            }
        })

        plateInfoViewModel.copyBuffer.observe(this, Observer {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied", it)
            clipboard.primaryClip = clip
            Snackbar.make(coordinator_layout, "Copied to buffer", Snackbar.LENGTH_SHORT).show()
        })
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(PLATE_NUMBER, plateNumber)
        super.onSaveInstanceState(outState)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initInformationBlocks(info: PlateInfo) {
        val generalInfoList = mutableListOf<Info>()
        generalInfoList.add(Info("Year", info.year.toString()))
        generalInfoList.add(Info("Color", info.color))
        if (info.type != null)
            generalInfoList.add(Info("Type", info.type))
        if (info.fuel != null)
            generalInfoList.add(Info("Fuel", info.fuel))
        if (info.engineCapacity != null)
            generalInfoList.add(Info("Engine capacity", info.engineCapacity.toString()))
        if (info.mass != null && info.maxMass != null)
            generalInfoList.add(Info("Mass / Max Mass", "${info.mass} / ${info.maxMass}"))
        if (info.category != null)
            generalInfoList.add(Info("Category", info.category))
        if (info.seating != null)
            generalInfoList.add(Info("Seating", info.seating.toString()))
        if (info.vin != null)
            generalInfoList.add(Info("VIN", info.vin, true))
        if (info.dataOfFirstRegistration != null)
            generalInfoList.add(Info("Date Of First Registration", info.dataOfFirstRegistration.toString(), true))


        binding.generalInformationRecyclerView.layoutManager = FlexboxLayoutManager(this)
        binding.generalInformationRecyclerView.adapter = InfoAdapter(plateInfoViewModel, generalInfoList)

        if (info.lastRecord != null) {
            val lastRecordList = mutableListOf<Info>()
            lastRecordList.add(Info("Action", info.lastRecord, true))
            if (info.lastRecordDate != null)
                lastRecordList.add(Info("Date", info.lastRecordDate.toString(), true))
            if (info.lastPlace != null)
                lastRecordList.add(Info("Place", info.lastPlace, true))

            binding.lastRecordRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.lastRecordRecyclerView.adapter = InfoAdapter(plateInfoViewModel, lastRecordList)
        } else {
            binding.lastRecordRecyclerView.visibility = View.GONE
        }
    }
}
