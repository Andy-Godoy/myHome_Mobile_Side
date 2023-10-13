package com.example.myhome.modules.propiedadseleccionadausuariosone.ui

import android.net.Uri
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadSeleccionadaUsuariosOneBinding
import com.example.myhome.modules.avisocompartir.ui.AvisoCompartirActivity
import com.example.myhome.modules.contactaryturnotwo.ui.ContactarYTurnoTwoActivity
import com.example.myhome.modules.propiedadesfavoritosusuarioone.ui.PropiedadesFavoritosUsuarioOneActivity
import com.example.myhome.modules.propiedadseleccionadausuariosone.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadseleccionadausuariosone.`data`.viewmodel.PropiedadSeleccionadaUsuariosOneVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadSeleccionadaUsuariosOneActivity :
    BaseActivity<ActivityPropiedadSeleccionadaUsuariosOneBinding>(R.layout.activity_propiedad_seleccionada_usuarios_one)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_5")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val viewModel: PropiedadSeleccionadaUsuariosOneVM by
      viewModels<PropiedadSeleccionadaUsuariosOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    binding.propiedadSeleccionadaUsuariosOneVM = viewModel
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
    binding.linearButton.setOnClickListener {
      val destIntent = ContactarYTurnoTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearRowarrowone.setOnClickListener {
      val destIntent = PropiedadesFavoritosUsuarioOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageShare.setOnClickListener {
      val destIntent = AvisoCompartirActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROPIEDAD_SELECCIONADA_USUARIOS_ONE_ACTIVITY"

  }
}
