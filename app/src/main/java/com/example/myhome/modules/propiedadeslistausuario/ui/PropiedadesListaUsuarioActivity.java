package com.example.myhome.modules.propiedadeslistausuario.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesListaUsuarioBinding
import com.example.myhome.modules.perfilinmobiliariaone.ui.PerfilInmobiliariaOneActivity
import com.example.myhome.modules.propiedadeslistausuario.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadeslistausuario.`data`.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadeslistausuario.`data`.viewmodel.PropiedadesListaUsuarioVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadesListaUsuarioActivity :
    BaseActivity<ActivityPropiedadesListaUsuarioBinding>(R.layout.activity_propiedades_lista_usuario)
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


  private val viewModel: PropiedadesListaUsuarioVM by viewModels<PropiedadesListaUsuarioVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems,true)
    binding.imageSliderSliderOne.adapter = sliderOneAdapter
    binding.propiedadesListaUsuarioVM = viewModel
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
    binding.linearPerfil.setOnClickListener {
      val destIntent = PerfilInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROPIEDADES_LISTA_USUARIO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PropiedadesListaUsuarioActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
