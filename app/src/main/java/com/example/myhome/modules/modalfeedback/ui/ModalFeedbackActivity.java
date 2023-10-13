package com.example.myhome.modules.modalfeedback.ui

import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityModalFeedbackBinding
import com.example.myhome.modules.modalfeedback.`data`.viewmodel.ModalFeedbackVM
import kotlin.String
import kotlin.Unit

class ModalFeedbackActivity :
    BaseActivity<ActivityModalFeedbackBinding>(R.layout.activity_modal_feedback) {
  private val viewModel: ModalFeedbackVM by viewModels<ModalFeedbackVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.modalFeedbackVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "MODAL_FEEDBACK_ACTIVITY"

  }
}
