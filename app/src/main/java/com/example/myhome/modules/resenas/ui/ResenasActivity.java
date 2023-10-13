package com.example.myhome.modules.resenas.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityResenasBinding
import com.example.myhome.modules.perfilinmobiliaria.ui.PerfilInmobiliariaActivity
import com.example.myhome.modules.resenas.`data`.model.ResenasRowModel
import com.example.myhome.modules.resenas.`data`.viewmodel.ResenasVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ResenasActivity : BaseActivity<ActivityResenasBinding>(R.layout.activity_resenas) {
  private val viewModel: ResenasVM by viewModels<ResenasVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val resenasAdapter = ResenasAdapter(viewModel.resenasList.value?:mutableListOf())
    binding.recyclerResenas.adapter = resenasAdapter
    resenasAdapter.setOnItemClickListener(
    object : ResenasAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ResenasRowModel) {
        onClickRecyclerResenas(view, position, item)
      }
    }
    )
    viewModel.resenasList.observe(this) {
      resenasAdapter.updateData(it)
    }
    binding.resenasVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearPerfil.setOnClickListener {
      val destIntent = PerfilInmobiliariaActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerResenas(
    view: View,
    position: Int,
    item: ResenasRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "RESENAS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ResenasActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
