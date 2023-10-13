package com.example.myhome.extensions

import android.util.Patterns
import android.widget.Toast
import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.Boolean
import kotlin.String

public fun String?.isValidEmail(isMandatory: Boolean = false): Boolean {
  if (isNullOrEmpty()) {
    if (isMandatory) {
      Toast.makeText(
              MyApp.getInstance(),
              R.string.msg_please_enter_valid_email,
              Toast.LENGTH_SHORT
          ).show()
    }
    return !isMandatory
  }
  val result = 
  Patterns.EMAIL_ADDRESS.matcher(this).matches()
  if (!result) {
    Toast.makeText(
            MyApp.getInstance(),
            R.string.msg_please_enter_valid_email,
            Toast.LENGTH_SHORT
        ).show()
  }
  return result
}
