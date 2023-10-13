package com.example.myhome.modules.cambiarcontrasea.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityCambiarContraseABinding
import com.example.myhome.modules.cambiarcontrasea.`data`.viewmodel.CambiarContraseAVM
import com.example.myhome.modules.cambiarcontraseaerror.ui.CambiarContraseAErrorActivity
import kotlin.String
import kotlin.Unit

class CambiarContraseAActivity :
    BaseActivity<ActivityCambiarContraseABinding>(R.layout.activity_cambiar_contrase_a) {
  private val viewModel: CambiarContraseAVM by viewModels<CambiarContraseAVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.cambiarContraseAVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnAceptar.setOnClickListener {
      val destIntent = CambiarContraseAErrorActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CAMBIAR_CONTRASE_A_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CambiarContraseAActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
