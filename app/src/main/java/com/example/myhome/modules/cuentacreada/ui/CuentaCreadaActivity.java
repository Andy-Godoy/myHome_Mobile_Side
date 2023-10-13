package com.example.myhome.modules.cuentacreada.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityCuentaCreadaBinding
import com.example.myhome.modules.cuentacreada.`data`.viewmodel.CuentaCreadaVM
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import kotlin.String
import kotlin.Unit

class CuentaCreadaActivity :
    BaseActivity<ActivityCuentaCreadaBinding>(R.layout.activity_cuenta_creada) {
  private val viewModel: CuentaCreadaVM by viewModels<CuentaCreadaVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.cuentaCreadaVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnAceptar.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CUENTA_CREADA_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CuentaCreadaActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
