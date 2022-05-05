package com.itb.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.itb.minescope.R
import com.itb.minescope.ui.viewmodel.MinescopeViewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    //ATTRIBUTES
    //Layout Elements
    private lateinit var closeBtn: ImageView
    private val viewModel: MinescopeViewModel by activityViewModels()

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        //IDs
        closeBtn = view.findViewById(R.id.close_btn)
        val themeInput: AutoCompleteTextView = view.findViewById(R.id.themeInput)
        themeInput.setAdapter(ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, viewModel.langList))

        //ON CLICK
        closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
