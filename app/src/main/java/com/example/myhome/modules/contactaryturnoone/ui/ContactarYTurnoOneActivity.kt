package com.example.myhome.modules.contactaryturnoone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityContactarYTurnoOneBinding
import com.example.myhome.modules.contactaryturno.ui.ContactarYTurnoActivity
import com.example.myhome.modules.contactaryturnoone.`data`.viewmodel.ContactarYTurnoOneVM
import kotlin.String
import kotlin.Unit

class ContactarYTurnoOneActivity :
    BaseActivity<ActivityContactarYTurnoOneBinding>(R.layout.activity_contactar_y_turno_one) {
  private val viewModel: ContactarYTurnoOneVM by viewModels<ContactarYTurnoOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.contactarYTurnoOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.etDropdownconsul.setOnClickListener {
      val destIntent = ContactarYTurnoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "CONTACTAR_Y_TURNO_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ContactarYTurnoOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
