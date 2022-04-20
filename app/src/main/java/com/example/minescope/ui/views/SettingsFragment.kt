package com.example.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.data.models.OpaqueMineral
import com.example.minescope.data.models.OpaqueMineralSample
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.data.models.TransparentMineralSample
import com.example.minescope.ui.adapters.MineralsListAdapter
import com.example.minescope.ui.adapters.SamplesListAdapter
import com.example.minescope.ui.viewmodel.MinescopeViewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    //ATTRIBUTES
    //Layout Elements
    private lateinit var closeBtn: ImageView

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        closeBtn = view.findViewById(R.id.close_btn)

        //ON CLICK
        closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
