package com.example.myhome.modules.nuevapropiedadpartefour.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityNuevaPropiedadParteFourBinding
import com.example.myhome.modules.nuevapropiedadpartefour.`data`.viewmodel.NuevaPropiedadParteFourVM
import com.example.myhome.modules.nuevapropiedadpartethree.ui.NuevaPropiedadParteThreeActivity
import kotlin.String
import kotlin.Unit

class NuevaPropiedadParteFourActivity :
    BaseActivity<ActivityNuevaPropiedadParteFourBinding>(R.layout.activity_nueva_propiedad_parte_four)
    {
  private val viewModel: NuevaPropiedadParteFourVM by viewModels<NuevaPropiedadParteFourVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.nuevaPropiedadParteFourVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = NuevaPropiedadParteThreeActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "NUEVA_PROPIEDAD_PARTE_FOUR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NuevaPropiedadParteFourActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
