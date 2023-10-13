package com.example.myhome.modules.propiedadesfavoritosusuariotwo.ui

import android.net.Uri
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesFavoritosUsuarioTwoBinding
import com.example.myhome.modules.propiedadesfavoritosusuariotwo.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadesfavoritosusuariotwo.`data`.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadesfavoritosusuariotwo.`data`.viewmodel.PropiedadesFavoritosUsuarioTwoVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadesFavoritosUsuarioTwoActivity :
    BaseActivity<ActivityPropiedadesFavoritosUsuarioTwoBinding>(R.layout.activity_propiedades_favoritos_usuario_two)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_3")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_4")


  private val imageSliderSliderOneItems: ArrayList<ImageSliderSliderOneModel> =
      arrayListOf(ImageSliderSliderOneModel(imageImageSixOne =
  imageUri.toString()),ImageSliderSliderOneModel(imageImageSixOne = imageUri.toString()))


  private val viewModel: PropiedadesFavoritosUsuarioTwoVM by
      viewModels<PropiedadesFavoritosUsuarioTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems,true)
    binding.imageSliderSliderOne.adapter = sliderOneAdapter
    binding.propiedadesFavoritosUsuarioTwoVM = viewModel
  }

  override fun onPause(): Unit {
    binding.imageSliderSlider.pauseAutoScroll()
    binding.imageSliderSliderOne.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSlider.resumeAutoScroll()
    binding.imageSliderSliderOne.resumeAutoScroll()
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PROPIEDADES_FAVORITOS_USUARIO_TWO_ACTIVITY"

  }
}
