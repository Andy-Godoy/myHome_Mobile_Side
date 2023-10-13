package com.example.myhome.modules.registrarerror.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityRegistrarErrorBinding
import com.example.myhome.modules.cuentacreada.ui.CuentaCreadaActivity
import com.example.myhome.modules.registrarerror.`data`.viewmodel.RegistrarErrorVM
import kotlin.String
import kotlin.Unit

class RegistrarErrorActivity :
    BaseActivity<ActivityRegistrarErrorBinding>(R.layout.activity_registrar_error) {
  private val viewModel: RegistrarErrorVM by viewModels<RegistrarErrorVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.registrarErrorVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnCrearCuenta.setOnClickListener {
      val destIntent = CuentaCreadaActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "REGISTRAR_ERROR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RegistrarErrorActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
