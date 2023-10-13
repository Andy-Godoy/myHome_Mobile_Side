package com.example.myhome.modules.perfilinmobiliariaone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPerfilInmobiliariaOneBinding
import com.example.myhome.modules.perfilinmobiliariaone.`data`.viewmodel.PerfilInmobiliariaOneVM
import kotlin.String
import kotlin.Unit

class PerfilInmobiliariaOneActivity :
    BaseActivity<ActivityPerfilInmobiliariaOneBinding>(R.layout.activity_perfil_inmobiliaria_one) {
  private val viewModel: PerfilInmobiliariaOneVM by viewModels<PerfilInmobiliariaOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.perfilInmobiliariaOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PERFIL_INMOBILIARIA_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PerfilInmobiliariaOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
