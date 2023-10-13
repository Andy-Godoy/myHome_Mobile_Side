package com.example.myhome.modules.propiedadesfavoritosusuario.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myhome.R
import com.example.myhome.appcomponents.base.BaseActivity
import com.example.myhome.databinding.ActivityPropiedadesFavoritosUsuarioBinding
import com.example.myhome.modules.propiedadesfavoritosusuario.data.model.ImageSliderSliderModel
import com.example.myhome.modules.propiedadesfavoritosusuario.data.model.ImageSliderSliderOneModel
import com.example.myhome.modules.propiedadesfavoritosusuario.data.viewmodel.PropiedadesFavoritosUsuarioVM
import com.example.myhome.modules.valoracionesycomentarios.ui.ValoracionesYComentariosActivity

class PropiedadesFavoritosUsuarioActivity :
    BaseActivity<ActivityPropiedadesFavoritosUsuarioBinding>(R.layout.activity_propiedades_favoritos_usuario) {
    private val imageHeaderParserUtils: Uri =
        Uri.parse("android.resource://com.example.myhome/drawable/img_image6_155x378")


    private val imageSliderSliderItems: ArrayList<ImageSliderSliderModel> =
        arrayListOf(
            ImageSliderSliderModel(
                imageImageSix =
                imageHeaderParserUtils.toString()
            ), ImageSliderSliderModel(imageImageSix = imageHeaderParserUtils.toString())
        )


    private val imageUri: Uri =
        Uri.parse("android.resource://com.example.myhome/drawable/img_image6_6")


    private val imageSliderSliderOneItems: ArrayList<ImageSliderSliderOneModel> =
        arrayListOf(
            ImageSliderSliderOneModel(
                imageImageSixOne =
                imageUri.toString()
            ), ImageSliderSliderOneModel(imageImageSixOne = imageUri.toString())
        )


    private val viewModel: PropiedadesFavoritosUsuarioVM by
    viewModels<PropiedadesFavoritosUsuarioVM>()

    override fun onInitialized(): Unit {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        val sliderAdapter = SliderAdapter(imageSliderSliderItems, true)
        binding.imageSliderSlider.adapter = sliderAdapter
        val sliderOneAdapter = SliderOneAdapter(imageSliderSliderOneItems, true)
        binding.imageSliderSliderOne.adapter = sliderOneAdapter
        binding.propiedadesFavoritosUsuarioVM = viewModel
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
        binding.imageImageFifteen.setOnClickListener {
            val destIntent = ValoracionesYComentariosActivity.getIntent(this, null)
            startActivity(destIntent)
        }
    }

    companion object {
        const val TAG: String = "PROPIEDADES_FAVORITOS_USUARIO_ACTIVITY"


        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, PropiedadesFavoritosUsuarioActivity::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }
}
