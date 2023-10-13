package com.example.myhome.modules.cambiarcontraseaerror.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityCambiarContraseAErrorBinding
import com.example.myhome.modules.cambiarcontraseaerror.`data`.viewmodel.CambiarContraseAErrorVM
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import kotlin.String
import kotlin.Unit

class CambiarContraseAErrorActivity :
    BaseActivity<ActivityCambiarContraseAErrorBinding>(R.layout.activity_cambiar_contrase_a_error) {
  private val viewModel: CambiarContraseAErrorVM by viewModels<CambiarContraseAErrorVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.cambiarContraseAErrorVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnAceptar.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CAMBIAR_CONTRASE_A_ERROR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CambiarContraseAErrorActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
