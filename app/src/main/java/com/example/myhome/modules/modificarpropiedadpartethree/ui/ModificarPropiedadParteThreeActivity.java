package com.example.myhome.modules.modificarpropiedadpartethree.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityModificarPropiedadParteThreeBinding
import com.example.myhome.modules.modificarpropiedadpartefour.ui.ModificarPropiedadParteFourActivity
import com.example.myhome.modules.modificarpropiedadpartethree.`data`.viewmodel.ModificarPropiedadParteThreeVM
import com.example.myhome.modules.modificarpropiedadpartetwo.ui.ModificarPropiedadParteTwoActivity
import kotlin.String
import kotlin.Unit

class ModificarPropiedadParteThreeActivity :
    BaseActivity<ActivityModificarPropiedadParteThreeBinding>(R.layout.activity_modificar_propiedad_parte_three)
    {
  private val viewModel: ModificarPropiedadParteThreeVM by
      viewModels<ModificarPropiedadParteThreeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.modificarPropiedadParteThreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      val destIntent = ModificarPropiedadParteTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowright.setOnClickListener {
      val destIntent = ModificarPropiedadParteFourActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MODIFICAR_PROPIEDAD_PARTE_THREE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ModificarPropiedadParteThreeActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
