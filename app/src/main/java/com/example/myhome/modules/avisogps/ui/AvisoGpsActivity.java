package com.example.myhome.modules.avisogps.ui

import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityAvisoGpsBinding
import com.example.myhome.modules.avisogps.`data`.viewmodel.AvisoGpsVM
import kotlin.String
import kotlin.Unit

class AvisoGpsActivity : BaseActivity<ActivityAvisoGpsBinding>(R.layout.activity_aviso_gps) {
  private val viewModel: AvisoGpsVM by viewModels<AvisoGpsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.avisoGpsVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "AVISO_GPS_ACTIVITY"

  }
}
