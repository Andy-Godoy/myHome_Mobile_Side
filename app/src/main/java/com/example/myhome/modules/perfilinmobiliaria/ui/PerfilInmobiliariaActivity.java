package com.example.myhome.modules.perfilinmobiliaria.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPerfilInmobiliariaBinding
import com.example.myhome.modules.perfilinmobiliaria.`data`.viewmodel.PerfilInmobiliariaVM
import com.example.myhome.modules.resenas.ui.ResenasActivity
import kotlin.String
import kotlin.Unit

class PerfilInmobiliariaActivity :
    BaseActivity<ActivityPerfilInmobiliariaBinding>(R.layout.activity_perfil_inmobiliaria) {
  private val viewModel: PerfilInmobiliariaVM by viewModels<PerfilInmobiliariaVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.perfilInmobiliariaVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnVerReseas.setOnClickListener {
      val destIntent = ResenasActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PERFIL_INMOBILIARIA_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PerfilInmobiliariaActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
