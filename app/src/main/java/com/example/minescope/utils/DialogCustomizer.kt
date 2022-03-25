package com.example.minescope.utils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.Path
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.minescope.R
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.color.MaterialColors


object DialogCustomizer {
    //METHODS
    /**
     * This method is used to obtain a customized dialog title.
     * @param titleText Dialog Title
     */
    fun getCustomizedDialogTitle(titleText: String, context: Context) : View {
        //CUSTOM TITLE
        val title = TextView(context)
        title.text = titleText
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
        title.setTextColor(MaterialColors.getColor(context, R.attr.colorSecondary, Color.BLACK))
        title.typeface = ResourcesCompat.getFont(context, R.font.permanent_marker)
        title.gravity = Gravity.CENTER
        title.setPadding(0, 30, 0, 30)

        return title
    }
}
