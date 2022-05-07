package com.itb.minescope.ui.views

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itb.minescope.R

import java.util.Locale

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    //ATTRIBUTES
    //Layout Elements
    private lateinit var closeBtn: ImageView
    private lateinit var themeInput: AutoCompleteTextView

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        closeBtn = view.findViewById(R.id.close_btn)
        themeInput = view.findViewById(R.id.themeInput)

        //LANGUAGES
        val langList = mutableListOf(getString(R.string.es_lang),getString(R.string.en_lang),getString(R.string.cat_lang))
        themeInput.setAdapter(ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, langList))

        //ON CLICK
        closeBtn.setOnClickListener {
            Log.d("LANGUAGE", themeInput.text.toString())
            setLanguage(themeInput.text.toString())
            findNavController().popBackStack()
        }
    }

    //METHODS
    /**
     * This method is used to set the app's language.
     * @param lang Chosen language
     */
    private fun setLanguage(lang: String) {
        var locale = Locale.getDefault()

        when (lang) {
            getString(R.string.en_lang) -> locale = Locale("en")
            getString(R.string.es_lang) -> locale = Locale("es")
            getString(R.string.cat_lang) -> locale = Locale("ca")
        }

        Locale.setDefault(locale)
        val resources = activity!!.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
