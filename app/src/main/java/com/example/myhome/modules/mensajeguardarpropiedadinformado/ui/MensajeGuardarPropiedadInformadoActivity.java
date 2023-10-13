package com.example.myhome.modules.mensajeguardarpropiedadinformado.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityMensajeGuardarPropiedadInformadoBinding
import com.example.myhome.modules.mensajeguardarpropiedadinformado.`data`.viewmodel.MensajeGuardarPropiedadInformadoVM
import com.example.myhome.modules.propiedadesfavoritosusuario.ui.PropiedadesFavoritosUsuarioActivity
import kotlin.String
import kotlin.Unit

class MensajeGuardarPropiedadInformadoActivity :
    BaseActivity<ActivityMensajeGuardarPropiedadInformadoBinding>(R.layout.activity_mensaje_guardar_propiedad_informado)
    {
  private val viewModel: MensajeGuardarPropiedadInformadoVM by
      viewModels<MensajeGuardarPropiedadInformadoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.mensajeGuardarPropiedadInformadoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtAceptar.setOnClickListener {
      val destIntent = PropiedadesFavoritosUsuarioActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MENSAJE_GUARDAR_PROPIEDAD_INFORMADO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MensajeGuardarPropiedadInformadoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
