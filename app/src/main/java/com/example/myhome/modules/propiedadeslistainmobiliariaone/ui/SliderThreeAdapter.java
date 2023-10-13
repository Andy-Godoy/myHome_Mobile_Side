package com.example.myhome.modules.propiedadeslistainmobiliariaone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.myhome.databinding.SlideritemPropiedadesListaInmobiliariaOne4Binding
import com.example.myhome.modules.propiedadeslistainmobiliariaone.`data`.model.ImageSliderSliderThreeModel
import java.util.ArrayList
import kotlin.Boolean
import kotlin.Int

class SliderThreeAdapter(
  val dataList: ArrayList<ImageSliderSliderThreeModel>,
  val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderThreeModel>(dataList, mIsInfinite) {
  override fun bindView(
    binding: ViewBinding,
    listPosition: Int,
    viewType: Int
  ) {
    if (binding is SlideritemPropiedadesListaInmobiliariaOne4Binding) {
      binding.imageSliderSliderThreeModel = dataList[listPosition]
    }
  }

  override fun inflateView(
    viewType: Int,
    container: ViewGroup,
    listPosition: Int
  ): ViewBinding {
    val itemBinding =  SlideritemPropiedadesListaInmobiliariaOne4Binding.inflate(
    LayoutInflater.from(container.context),
                    container,
                    false
    )
    return itemBinding
  }
}
