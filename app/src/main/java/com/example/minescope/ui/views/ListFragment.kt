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
    //ATTRIBUTES
    private lateinit var recyclerView: RecyclerView
    private lateinit var filtersButton: ImageView
    private lateinit var settingsButton: ImageView
    private lateinit var tabLayout: TabLayout

    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RECYCLER VIEW SET UP
        recyclerView = view.findViewById(R.id.recycler_view)
        filtersButton = view.findViewById(R.id.filters_icon)
        settingsButton = view.findViewById(R.id.settings_icon)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        tabLayout = view.findViewById(R.id.tab_layout)

        loadMinerals(false)

        filtersButton.setOnClickListener {
            findNavController().navigate(R.id.listToFilters)
        }

        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.listToSettings)
        }

        //TABS
        if (!viewModel.isTransparentFilters) {
            tabLayout.getTabAt(1)!!.select()
            loadMinerals(true)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //SELECTED
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    loadMinerals(false)
                    viewModel.isTransparentFilters = true
                }
                else {
                    loadMinerals(true)
                    viewModel.isTransparentFilters = false
                }
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
    /**
     * This method is used to load the minerals into the list.
     * @param isOpaque Used to determine which minerals to load
     */
    private fun loadMinerals(isOpaque: Boolean) {
        if (isOpaque) {
            val principalAdapter = MineralsListAdapter(isOpaque)
            viewModel.opaqueMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setOpaqueMineralsList(it) })
            recyclerView.adapter = principalAdapter
        }
        else {
            val principalAdapter = MineralsListAdapter(isOpaque)
            viewModel.transparentMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setTransparentMineralsList(it) })
            recyclerView.adapter = principalAdapter
        }
    }
}
