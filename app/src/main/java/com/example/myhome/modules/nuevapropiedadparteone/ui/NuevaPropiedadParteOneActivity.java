package com.example.myhome.modules.nuevapropiedadparteone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityNuevaPropiedadParteOneBinding
import com.example.myhome.modules.nuevapropiedadparte1error.ui.NuevaPropiedadParte1ErrorActivity
import com.example.myhome.modules.nuevapropiedadparteone.`data`.viewmodel.NuevaPropiedadParteOneVM
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import kotlin.String
import kotlin.Unit

class NuevaPropiedadParteOneActivity :
    BaseActivity<ActivityNuevaPropiedadParteOneBinding>(R.layout.activity_nueva_propiedad_parte_one)
    {
  private val viewModel: NuevaPropiedadParteOneVM by viewModels<NuevaPropiedadParteOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.nuevaPropiedadParteOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowright.setOnClickListener {
      val destIntent = NuevaPropiedadParte1ErrorActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "NUEVA_PROPIEDAD_PARTE_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NuevaPropiedadParteOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
