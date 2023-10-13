package com.example.myhome.modules.propiedadseleccionadausuarios.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.myhome.databinding.SlideritemPropiedadSeleccionadaUsuarios1Binding
import com.example.myhome.modules.propiedadseleccionadausuarios.`data`.model.ImageSliderSliderModel
import java.util.ArrayList
import kotlin.Boolean
import kotlin.Int

class SliderAdapter(
  val dataList: ArrayList<ImageSliderSliderModel>,
  val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderModel>(dataList, mIsInfinite) {
  override fun bindView(
    binding: ViewBinding,
    listPosition: Int,
    viewType: Int
  ) {
    if (binding is SlideritemPropiedadSeleccionadaUsuarios1Binding) {
      binding.imageSliderSliderModel = dataList[listPosition]
    }
  }

  override fun inflateView(
    viewType: Int,
    container: ViewGroup,
    listPosition: Int
  ): ViewBinding {
    val itemBinding =  SlideritemPropiedadSeleccionadaUsuarios1Binding.inflate(
    LayoutInflater.from(container.context),
                    container,
                    false
    )
    return itemBinding
  }
}
