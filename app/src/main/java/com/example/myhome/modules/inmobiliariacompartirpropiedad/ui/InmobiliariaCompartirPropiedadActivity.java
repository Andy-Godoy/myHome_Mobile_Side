package com.example.myhome.modules.inmobiliariacompartirpropiedad.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityInmobiliariaCompartirPropiedadBinding
import com.example.myhome.modules.inmobiliariacompartirpropiedad.`data`.viewmodel.InmobiliariaCompartirPropiedadVM
import kotlin.String
import kotlin.Unit

class InmobiliariaCompartirPropiedadActivity :
    BaseActivity<ActivityInmobiliariaCompartirPropiedadBinding>(R.layout.activity_inmobiliaria_compartir_propiedad)
    {
  private val viewModel: InmobiliariaCompartirPropiedadVM by
      viewModels<InmobiliariaCompartirPropiedadVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.inmobiliariaCompartirPropiedadVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "INMOBILIARIA_COMPARTIR_PROPIEDAD_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, InmobiliariaCompartirPropiedadActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
