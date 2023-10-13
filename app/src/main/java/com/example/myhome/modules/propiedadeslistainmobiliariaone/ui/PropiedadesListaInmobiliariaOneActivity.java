package com.example.myhome.modules.propiedadeslistainmobiliariaone.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesListaInmobiliariaOneBinding
import com.example.myhome.modules.modificarpropiedadparteone.ui.ModificarPropiedadParteOneActivity
import com.example.myhome.modules.perfilinmobiliaria.ui.PerfilInmobiliariaActivity
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderThreeModel
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderTwoModel
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.viewmodel.PropiedadesListaInmobiliariaOneVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadesListaInmobiliariaOneActivity :
    BaseActivity<ActivityPropiedadesListaInmobiliariaOneBinding>(R.layout.activity_propiedades_lista_inmobiliaria_one)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_1")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_2")


  private val imageSliderSliderOneItems: ArrayList<ImageSliderSliderOneModel> =
      arrayListOf(ImageSliderSliderOneModel(imageImageSixOne =
  imageUri.toString()),ImageSliderSliderOneModel(imageImageSixOne = imageUri.toString()))


  private val imageUri: Uri = Uri.parse("android.resource://com.example.myhome/drawable/img_image6")


  private val imageSliderSliderTwoItems: ArrayList<ImageSliderSliderTwoModel> =
      arrayListOf(ImageSliderSliderTwoModel(imageImageSixTwo =
  imageUri.toString()),ImageSliderSliderTwoModel(imageImageSixTwo = imageUri.toString()))


  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_155x388")


  private val imageSliderSliderThreeItems: ArrayList<ImageSliderSliderThreeModel> =
      arrayListOf(ImageSliderSliderThreeModel(imageImageSixThree =
  imageUri.toString()),ImageSliderSliderThreeModel(imageImageSixThree =
  imageUri.toString()))


  private val viewModel: PropiedadesListaInmobiliariaOneVM by
      viewModels<PropiedadesListaInmobiliariaOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems,true)
    binding.imageSliderSliderOne.adapter = sliderOneAdapter
    val sliderTwoAdapter = SliderTwoAdapter(imageSliderSliderTwoItems,true)
    binding.imageSliderSliderTwo.adapter = sliderTwoAdapter
    val sliderThreeAdapter = SliderThreeAdapter(imageSliderSliderThreeItems,true)
    binding.imageSliderSliderThree.adapter = sliderThreeAdapter
    binding.propiedadesListaInmobiliariaOneVM = viewModel
  }

  override fun onPause(): Unit {
    binding.imageSliderSlider.pauseAutoScroll()
    binding.imageSliderSliderOne.pauseAutoScroll()
    binding.imageSliderSliderTwo.pauseAutoScroll()
    binding.imageSliderSliderThree.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSlider.resumeAutoScroll()
    binding.imageSliderSliderOne.resumeAutoScroll()
    binding.imageSliderSliderTwo.resumeAutoScroll()
    binding.imageSliderSliderThree.resumeAutoScroll()
  }

  override fun setUpClicks(): Unit {
    binding.imageEdit.setOnClickListener {
      val destIntent = ModificarPropiedadParteOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearPerfil.setOnClickListener {
      val destIntent = PerfilInmobiliariaActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROPIEDADES_LISTA_INMOBILIARIA_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PropiedadesListaInmobiliariaOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
