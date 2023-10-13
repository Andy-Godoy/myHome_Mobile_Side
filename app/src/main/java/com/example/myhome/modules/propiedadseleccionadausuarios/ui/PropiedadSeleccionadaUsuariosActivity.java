package com.example.myhome.modules.propiedadseleccionadausuarios.ui

import android.net.Uri
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadSeleccionadaUsuariosBinding
import com.example.myhome.modules.avisocompartir.ui.AvisoCompartirActivity
import com.example.myhome.modules.propiedadesfavoritosusuarioone.ui.PropiedadesFavoritosUsuarioOneActivity
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ListlanguageThreeRowModel
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.viewmodel.PropiedadSeleccionadaUsuariosVM
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadSeleccionadaUsuariosActivity :
    BaseActivity<ActivityPropiedadSeleccionadaUsuariosBinding>(R.layout.activity_propiedad_seleccionada_usuarios)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_2")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_1")


  private val imageSliderSliderOneItems: ArrayList<ImageSliderSliderOneModel> =
      arrayListOf(ImageSliderSliderOneModel(imageImageSixOne =
  imageUri.toString()),ImageSliderSliderOneModel(imageImageSixOne = imageUri.toString()))


  private val viewModel: PropiedadSeleccionadaUsuariosVM by
      viewModels<PropiedadSeleccionadaUsuariosVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems,true)
    binding.imageSliderSliderOne.adapter = sliderOneAdapter
    val listlanguageThreeAdapter =
    ListlanguageThreeAdapter(viewModel.listlanguageThreeList.value?:mutableListOf())
    binding.recyclerListlanguageThree.adapter = listlanguageThreeAdapter
    listlanguageThreeAdapter.setOnItemClickListener(
    object : ListlanguageThreeAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListlanguageThreeRowModel) {
        onClickRecyclerListlanguageThree(view, position, item)
      }
    }
    )
    viewModel.listlanguageThreeList.observe(this) {
      listlanguageThreeAdapter.updateData(it)
    }
    binding.propiedadSeleccionadaUsuariosVM = viewModel
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
    binding.linearRowarrowone.setOnClickListener {
      val destIntent = PropiedadesFavoritosUsuarioOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageShare.setOnClickListener {
      val destIntent = AvisoCompartirActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListlanguageThree(
    view: View,
    position: Int,
    item: ListlanguageThreeRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROPIEDAD_SELECCIONADA_USUARIOS_ACTIVITY"

  }
}
