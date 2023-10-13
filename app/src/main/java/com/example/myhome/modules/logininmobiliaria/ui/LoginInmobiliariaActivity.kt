package com.example.myhome.modules.logininmobiliaria.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityLoginInmobiliariaBinding
import com.example.myhome.modules.login.ui.LoginActivity
import com.example.myhome.modules.logininmobiliaria.`data`.viewmodel.LoginInmobiliariaVM
import com.example.myhome.modules.logininmobiliariaerror.ui.LoginInmobiliariaErrorActivity
import com.example.myhome.modules.olvidemicontrasea.ui.OlvideMiContraseAActivity
import com.example.myhome.modules.registrar.ui.RegistrarActivity
import kotlin.String
import kotlin.Unit

class LoginInmobiliariaActivity :
    BaseActivity<ActivityLoginInmobiliariaBinding>(R.layout.activity_login_inmobiliaria) {
  private val viewModel: LoginInmobiliariaVM by viewModels<LoginInmobiliariaVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginInmobiliariaVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnIngresar.setOnClickListener {
      val destIntent = LoginInmobiliariaErrorActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      val destIntent = LoginActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.txtRegistreseaqu.setOnClickListener {
      val destIntent = RegistrarActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtLvidemicontr.setOnClickListener {
      val destIntent = OlvideMiContraseAActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "LOGIN_INMOBILIARIA_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginInmobiliariaActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
