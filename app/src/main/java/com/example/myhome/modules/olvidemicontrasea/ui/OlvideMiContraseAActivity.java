package com.example.myhome.modules.olvidemicontrasea.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityOlvideMiContraseABinding
import com.example.myhome.modules.mailcontraseaenviado.ui.MailContraseAEnviadoActivity
import com.example.myhome.modules.olvidemicontrasea.`data`.viewmodel.OlvideMiContraseAVM
import kotlin.String
import kotlin.Unit

class OlvideMiContraseAActivity :
    BaseActivity<ActivityOlvideMiContraseABinding>(R.layout.activity_olvide_mi_contrase_a) {
  private val viewModel: OlvideMiContraseAVM by viewModels<OlvideMiContraseAVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.olvideMiContraseAVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnEnviar.setOnClickListener {
      val destIntent = MailContraseAEnviadoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "OLVIDE_MI_CONTRASE_A_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, OlvideMiContraseAActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
