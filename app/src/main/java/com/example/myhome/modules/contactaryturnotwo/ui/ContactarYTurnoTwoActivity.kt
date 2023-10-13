package com.example.myhome.modules.contactaryturnotwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityContactarYTurnoTwoBinding
import com.example.myhome.modules.contactaryturnoone.ui.ContactarYTurnoOneActivity
import com.example.myhome.modules.contactaryturnotwo.`data`.viewmodel.ContactarYTurnoTwoVM
import kotlin.String
import kotlin.Unit

class ContactarYTurnoTwoActivity :
    BaseActivity<ActivityContactarYTurnoTwoBinding>(R.layout.activity_contactar_y_turno_two) {
  private val viewModel: ContactarYTurnoTwoVM by viewModels<ContactarYTurnoTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.contactarYTurnoTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.etDropdownconsul.setOnClickListener {
      val destIntent = ContactarYTurnoOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CONTACTAR_Y_TURNO_TWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ContactarYTurnoTwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
