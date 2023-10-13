package com.example.myhome.modules.modificarpropiedadparteone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityModificarPropiedadParteOneBinding
import com.example.myhome.modules.modificarpropiedadparteone.`data`.viewmodel.ModificarPropiedadParteOneVM
import com.example.myhome.modules.modificarpropiedadpartetwo.ui.ModificarPropiedadParteTwoActivity
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import kotlin.String
import kotlin.Unit

class ModificarPropiedadParteOneActivity :
    BaseActivity<ActivityModificarPropiedadParteOneBinding>(R.layout.activity_modificar_propiedad_parte_one)
    {
  private val viewModel: ModificarPropiedadParteOneVM by viewModels<ModificarPropiedadParteOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.modificarPropiedadParteOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = ModificarPropiedadParteTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MODIFICAR_PROPIEDAD_PARTE_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ModificarPropiedadParteOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
