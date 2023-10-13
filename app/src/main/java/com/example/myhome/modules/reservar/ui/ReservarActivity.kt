package com.example.myhome.modules.reservar.ui

import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityReservarBinding
import com.example.myhome.modules.mensajeguardarpropiedadinformado.ui.MensajeGuardarPropiedadInformadoActivity
import com.example.myhome.modules.reservar.`data`.viewmodel.ReservarVM
import kotlin.String
import kotlin.Unit

class ReservarActivity : BaseActivity<ActivityReservarBinding>(R.layout.activity_reservar) {
  private val viewModel: ReservarVM by viewModels<ReservarVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.reservarVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnRealizarReservaOne.setOnClickListener {
      val destIntent = MensajeGuardarPropiedadInformadoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "RESERVAR_ACTIVITY"

  }
}
