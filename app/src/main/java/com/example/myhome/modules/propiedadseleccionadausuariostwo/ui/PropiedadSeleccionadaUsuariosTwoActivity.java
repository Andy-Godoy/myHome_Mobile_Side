package com.example.myhome.modules.propiedadseleccionadausuariostwo.ui

import android.net.Uri
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadSeleccionadaUsuariosTwoBinding
import com.example.myhome.modules.inmobiliariacompartirpropiedad.ui.InmobiliariaCompartirPropiedadActivity
import com.example.myhome.modules.propiedadeslistainmobiliariaone.ui.PropiedadesListaInmobiliariaOneActivity
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ListlanguageOneRowModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.model.ListquinchoRowModel
import com.example.myhome.modules.propiedadseleccionadausuariostwo.`data`.viewmodel.PropiedadSeleccionadaUsuariosTwoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class PropiedadSeleccionadaUsuariosTwoActivity :
    BaseActivity<ActivityPropiedadSeleccionadaUsuariosTwoBinding>(R.layout.activity_propiedad_seleccionada_usuarios_two)
    {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.example.myhome/drawable/img_image6_2")


  private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
      arrayListOf(ImageSliderSliderModel(imageImageSix =
  imageUri.toString()),ImageSliderSliderModel(imageImageSix = imageUri.toString()))


  private val viewModel: PropiedadSeleccionadaUsuariosTwoVM by
      viewModels<PropiedadSeleccionadaUsuariosTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderAdapter = SliderAdapter(imageSliderSliderItems,true)
    binding.imageSliderSlider.adapter = sliderAdapter
    val listlanguageOneAdapter =
    ListlanguageOneAdapter(viewModel.listlanguageOneList.value?:mutableListOf())
    binding.recyclerListlanguageOne.adapter = listlanguageOneAdapter
    listlanguageOneAdapter.setOnItemClickListener(
    object : ListlanguageOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListlanguageOneRowModel) {
        onClickRecyclerListlanguageOne(view, position, item)
      }
    }
    )
    viewModel.listlanguageOneList.observe(this) {
      listlanguageOneAdapter.updateData(it)
    }
    val listquinchoAdapter =
    ListquinchoAdapter(viewModel.listquinchoList.value?:mutableListOf())
    binding.recyclerListquincho.adapter = listquinchoAdapter
    listquinchoAdapter.setOnItemClickListener(
    object : ListquinchoAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListquinchoRowModel) {
        onClickRecyclerListquincho(view, position, item)
      }
    }
    )
    viewModel.listquinchoList.observe(this) {
      listquinchoAdapter.updateData(it)
    }
    binding.propiedadSeleccionadaUsuariosTwoVM = viewModel
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
    binding.linearRowarrowone.setOnClickListener {
      val destIntent = PropiedadesListaInmobiliariaOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageShare.setOnClickListener {
      val destIntent = InmobiliariaCompartirPropiedadActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListlanguageOne(
    view: View,
    position: Int,
    item: ListlanguageOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerListquincho(
    view: View,
    position: Int,
    item: ListquinchoRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PROPIEDAD_SELECCIONADA_USUARIOS_TWO_ACTIVITY"

  }
}
