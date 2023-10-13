package com.example.myhome.modules.propiedadeslistainmobiliaria.ui

import android.net.Uri
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesListaInmobiliariaBinding
import com.example.myhome.modules.perfilinmobiliaria.ui.PerfilInmobiliariaActivity
import com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.model.ImageSliderSliderTwoModel
import com.example.myhome.modules.propiedadeslistainmobiliaria.`data`.viewmodel.PropiedadesListaInmobiliariaVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadesListaInmobiliariaActivity :
    BaseActivity<ActivityPropiedadesListaInmobiliariaBinding>(R.layout.activity_propiedades_lista_inmobiliaria)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_2")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val imageUri: Uri = Uri.parse("android.resource://com.example.myhome/drawable/img_image6")


  private val imageSliderSliderOneItems: ArrayList<ImageSliderSliderOneModel> =
      arrayListOf(ImageSliderSliderOneModel(imageImageSixOne =
  imageUri.toString()),ImageSliderSliderOneModel(imageImageSixOne = imageUri.toString()))


  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_155x388")


  private val imageSliderSliderTwoItems: ArrayList<ImageSliderSliderTwoModel> =
      arrayListOf(ImageSliderSliderTwoModel(imageImageSixTwo =
  imageUri.toString()),ImageSliderSliderTwoModel(imageImageSixTwo = imageUri.toString()))


  private val viewModel: PropiedadesListaInmobiliariaVM by
      viewModels<PropiedadesListaInmobiliariaVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems,true)
    binding.imageSliderSliderOne.adapter = sliderOneAdapter
    val sliderTwoAdapter = SliderTwoAdapter(imageSliderSliderTwoItems,true)
    binding.imageSliderSliderTwo.adapter = sliderTwoAdapter
    binding.propiedadesListaInmobiliariaVM = viewModel
  }

  override fun onPause(): Unit {
    binding.imageSliderSlider.pauseAutoScroll()
    binding.imageSliderSliderOne.pauseAutoScroll()
    binding.imageSliderSliderTwo.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSlider.resumeAutoScroll()
    binding.imageSliderSliderOne.resumeAutoScroll()
    binding.imageSliderSliderTwo.resumeAutoScroll()
  }

  override fun setUpClicks(): Unit {
    binding.linearPerfil.setOnClickListener {
      val destIntent = PerfilInmobiliariaActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROPIEDADES_LISTA_INMOBILIARIA_ACTIVITY"

  }
}
