package com.itb.minescope.ui.views

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itb.minescope.R
import pl.droidsonroids.gif.GifImageView

@SuppressLint("CustomSplashScreen")
class LaunchScreen : Fragment(R.layout.fragment_launch) {
    private lateinit var gif: GifImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gif = view.findViewById(R.id.gifImageView)

        val night = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        Log.d("AAAAAAAAAAAAAA", night.toString())

        when(night){
            Configuration.UI_MODE_NIGHT_NO -> gif.setImageResource(R.drawable.logo_light_gif)
            Configuration.UI_MODE_NIGHT_YES -> gif.setImageResource(R.drawable.logo_gif)
        }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                findNavController().navigate(R.id.launchToList)
            }, 3000)
    }
}
