package com.example.myhome.modules.registrar.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityRegistrarBinding
import com.example.myhome.modules.registrar.`data`.viewmodel.RegistrarVM
import com.example.myhome.modules.registrarerror.ui.RegistrarErrorActivity
import kotlin.String
import kotlin.Unit

class RegistrarActivity : BaseActivity<ActivityRegistrarBinding>(R.layout.activity_registrar) {
  private val viewModel: RegistrarVM by viewModels<RegistrarVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.registrarVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnCrearCuenta.setOnClickListener {
      val destIntent = RegistrarErrorActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "REGISTRAR_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RegistrarActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
