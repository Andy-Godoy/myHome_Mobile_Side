package com.example.myhome.modules.contactaryturno.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.appcomponents.views.DatePickerFragment
import com.example.myhome.databinding.ActivityContactarYTurnoBinding
import com.example.myhome.modules.contactaryturno.`data`.viewmodel.ContactarYTurnoVM
import com.example.myhome.modules.contactaryturnoone.ui.ContactarYTurnoOneActivity
import java.util.Date
import kotlin.String
import kotlin.Unit

class ContactarYTurnoActivity :
    BaseActivity<ActivityContactarYTurnoBinding>(R.layout.activity_contactar_y_turno) {
  private val viewModel: ContactarYTurnoVM by viewModels<ContactarYTurnoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.contactarYTurnoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.etDropdownconsul.setOnClickListener {
      val destIntent = ContactarYTurnoOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearRowcalendar.setOnClickListener {
      val destinationInstance = DatePickerFragment.getInstance()
      destinationInstance.show(this.supportFragmentManager, DatePickerFragment.TAG) {
        selectedDate ->
        onDateSelectedLinearRowcalendar(selectedDate)
      }
    }
  }

  private fun onDateSelectedLinearRowcalendar(selectedDate: Date): Unit {
  }

  companion object {
    const val TAG: String = "CONTACTAR_Y_TURNO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ContactarYTurnoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
