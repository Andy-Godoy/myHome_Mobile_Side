package com.example.myhome.modules.propiedadeslistainmobiliariaone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.myhome.databinding.SlideritemPropiedadesListaInmobiliariaOne3Binding
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderTwoModel
import java.util.ArrayList
import kotlin.Boolean
import kotlin.Int

class SliderTwoAdapter(
  val dataList: ArrayList<ImageSliderSliderTwoModel>,
  val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderTwoModel>(dataList, mIsInfinite) {
  override fun bindView(
    binding: ViewBinding,
    listPosition: Int,
    viewType: Int
  ) {
    if (binding is SlideritemPropiedadesListaInmobiliariaOne3Binding) {
      binding.imageSliderSliderTwoModel = dataList[listPosition]
    }
  }

  override fun inflateView(
    viewType: Int,
    container: ViewGroup,
    listPosition: Int
  ): ViewBinding {
    val itemBinding =  SlideritemPropiedadesListaInmobiliariaOne3Binding.inflate(
    LayoutInflater.from(container.context),
                    container,
                    false
    )
    return itemBinding
  }
}
