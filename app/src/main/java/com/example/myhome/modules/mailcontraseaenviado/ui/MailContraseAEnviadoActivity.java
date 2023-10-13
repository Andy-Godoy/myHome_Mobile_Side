package com.example.myhome.modules.mailcontraseaenviado.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityMailContraseAEnviadoBinding
import com.example.myhome.modules.cdigoverificacinerror.ui.CDigoVerificaciNErrorActivity
import com.example.myhome.modules.mailcontraseaenviado.`data`.viewmodel.MailContraseAEnviadoVM
import kotlin.String
import kotlin.Unit

class MailContraseAEnviadoActivity :
    BaseActivity<ActivityMailContraseAEnviadoBinding>(R.layout.activity_mail_contrase_a_enviado) {
  private val viewModel: MailContraseAEnviadoVM by viewModels<MailContraseAEnviadoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.mailContraseAEnviadoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnAceptar.setOnClickListener {
      val destIntent = CDigoVerificaciNErrorActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "MAIL_CONTRASE_A_ENVIADO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, MailContraseAEnviadoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
