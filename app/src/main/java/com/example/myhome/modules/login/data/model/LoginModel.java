package com.example.myhome.modules.login.`data`.model

import com.example.myhome.R
import com.example.myhome.appcomponents.di.MyApp
import kotlin.String

data class LoginModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtMyHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAccedparaver: String? =
      MyApp.getInstance().resources.getString(R.string.msg_acced_para_ver)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTensunainmo: String? = MyApp.getInstance().resources.getString(R.string.msg_ten_s_una_inmo)

)
