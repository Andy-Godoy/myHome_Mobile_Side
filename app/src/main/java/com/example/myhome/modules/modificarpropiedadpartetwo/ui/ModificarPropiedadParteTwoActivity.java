package com.example.myhome.modules.modificarpropiedadpartetwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityModificarPropiedadParteTwoBinding
import com.example.myhome.modules.modificarpropiedadparteone.ui.ModificarPropiedadParteOneActivity
import com.example.myhome.modules.modificarpropiedadpartethree.ui.ModificarPropiedadParteThreeActivity
import com.example.myhome.modules.modificarpropiedadpartetwo.`data`.viewmodel.ModificarPropiedadParteTwoVM
import kotlin.String
import kotlin.Unit

class ModificarPropiedadParteTwoActivity :
    BaseActivity<ActivityModificarPropiedadParteTwoBinding>(R.layout.activity_modificar_propiedad_parte_two)
    {
  private val viewModel: ModificarPropiedadParteTwoVM by viewModels<ModificarPropiedadParteTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.modificarPropiedadParteTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = ModificarPropiedadParteOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowright.setOnClickListener {
      val destIntent = ModificarPropiedadParteThreeActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MODIFICAR_PROPIEDAD_PARTE_TWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ModificarPropiedadParteTwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
