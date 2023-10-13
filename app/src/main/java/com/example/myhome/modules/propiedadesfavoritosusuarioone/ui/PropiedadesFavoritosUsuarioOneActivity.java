package com.example.myhome.modules.propiedadesfavoritosusuarioone.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesFavoritosUsuarioOneBinding
import com.example.myhome.modules.propiedadesfavoritosusuarioone.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadesfavoritosusuarioone.`data`.viewmodel.PropiedadesFavoritosUsuarioOneVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadesFavoritosUsuarioOneActivity :
    BaseActivity<ActivityPropiedadesFavoritosUsuarioOneBinding>(R.layout.activity_propiedades_favoritos_usuario_one)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_155x387")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val viewModel: PropiedadesFavoritosUsuarioOneVM by
      viewModels<PropiedadesFavoritosUsuarioOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    binding.propiedadesFavoritosUsuarioOneVM = viewModel
  }

  override fun onPause(): Unit {
    binding.imageSliderSlider.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSlider.resumeAutoScroll()
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PROPIEDADES_FAVORITOS_USUARIO_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PropiedadesFavoritosUsuarioOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
