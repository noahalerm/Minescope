package com.example.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.ui.adapters.MineralsListAdapter
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.google.android.material.tabs.TabLayout

class ListFragment : Fragment(R.layout.fragment_list) {

    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var filtersButton: ImageView
    private lateinit var tabLayout: TabLayout

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RECYCLER VIEW SET UP
        recyclerView = view.findViewById(R.id.recycler_view)
        filtersButton = view.findViewById(R.id.filters_icon)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        tabLayout = view.findViewById(R.id.tab_layout)

        loadTransparents()

        filtersButton.setOnClickListener {
            findNavController().navigate(R.id.listToFilters)
        }

        //TABS
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //SELECTED
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0)
                    loadTransparents()
                else
                    loadOpaques()
            }

            //RESELECTED
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            //UNSELECTED
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //METHODS
    private fun loadTransparents(){
        val principalAdapter = MineralsListAdapter(false)
        viewModel.transparentMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setTransparentMineralsList(it) })
        recyclerView.adapter = principalAdapter
    }
    private fun loadOpaques(){
        val principalAdapter = MineralsListAdapter(true)
        viewModel.opaqueMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setOpaqueMineralsList(it) })
        recyclerView.adapter = principalAdapter
    }
}
