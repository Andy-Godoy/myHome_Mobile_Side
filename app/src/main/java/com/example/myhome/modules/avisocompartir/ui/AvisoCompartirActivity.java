package com.example.myhome.modules.avisocompartir.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityAvisoCompartirBinding
import com.example.myhome.modules.avisocompartir.`data`.viewmodel.AvisoCompartirVM
import kotlin.String
import kotlin.Unit

class AvisoCompartirActivity :
    BaseActivity<ActivityAvisoCompartirBinding>(R.layout.activity_aviso_compartir) {
  private val viewModel: AvisoCompartirVM by viewModels<AvisoCompartirVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.avisoCompartirVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "AVISO_COMPARTIR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AvisoCompartirActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
