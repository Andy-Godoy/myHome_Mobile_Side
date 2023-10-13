package com.example.myhome.modules.logininmobiliariaerror.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityLoginInmobiliariaErrorBinding
import com.example.myhome.modules.logininmobiliariaerror.`data`.viewmodel.LoginInmobiliariaErrorVM
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import com.example.myhome.modules.registrar.ui.RegistrarActivity
import kotlin.String
import kotlin.Unit

class LoginInmobiliariaErrorActivity :
    BaseActivity<ActivityLoginInmobiliariaErrorBinding>(R.layout.activity_login_inmobiliaria_error)
    {
  private val viewModel: LoginInmobiliariaErrorVM by viewModels<LoginInmobiliariaErrorVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginInmobiliariaErrorVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtRegistreseaqu.setOnClickListener {
      val destIntent = RegistrarActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnIngresar.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "LOGIN_INMOBILIARIA_ERROR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginInmobiliariaErrorActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
