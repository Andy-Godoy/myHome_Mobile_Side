package com.example.myhome.modules.cdigoverificacinerror.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityCDigoVerificaciNErrorBinding
import com.example.myhome.modules.cambiarcontrasea.ui.CambiarContraseAActivity
import com.example.myhome.modules.cdigoverificacinerror.`data`.viewmodel.CDigoVerificaciNErrorVM
import kotlin.String
import kotlin.Unit

class CDigoVerificaciNErrorActivity :
    BaseActivity<ActivityCDigoVerificaciNErrorBinding>(R.layout.activity_c_digo_verificaci_n_error)
    {
  private val viewModel: CDigoVerificaciNErrorVM by viewModels<CDigoVerificaciNErrorVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.cDigoVerificaciNErrorVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnAceptar.setOnClickListener {
      val destIntent = CambiarContraseAActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "C_DIGO_VERIFICACI_N_ERROR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CDigoVerificaciNErrorActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
