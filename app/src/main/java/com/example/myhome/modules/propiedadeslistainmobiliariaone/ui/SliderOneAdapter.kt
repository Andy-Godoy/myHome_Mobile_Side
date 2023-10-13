package com.example.myhome.modules.propiedadeslistainmobiliariaone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.myhome.databinding.SlideritemPropiedadesListaInmobiliariaOne2Binding
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderOneModel
import java.util.ArrayList
import kotlin.Boolean
import kotlin.Int

class SliderOneAdapter(
  val dataList: ArrayList<ImageSliderSliderOneModel>,
  val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderOneModel>(dataList, mIsInfinite) {
  override fun bindView(
    binding: ViewBinding,
    listPosition: Int,
    viewType: Int
  ) {
    if (binding is SlideritemPropiedadesListaInmobiliariaOne2Binding) {
      binding.imageSliderSliderOneModel = dataList[listPosition]
    }
  }

  override fun inflateView(
    viewType: Int,
    container: ViewGroup,
    listPosition: Int
  ): ViewBinding {
    val itemBinding =  SlideritemPropiedadesListaInmobiliariaOne2Binding.inflate(
    LayoutInflater.from(container.context),
                    container,
                    false
    )
    return itemBinding
  }
}
