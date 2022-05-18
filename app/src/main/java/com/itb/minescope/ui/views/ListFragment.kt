package com.itb.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itb.minescope.ui.adapters.MineralsListAdapter
import com.itb.minescope.ui.viewmodel.MinescopeViewModel
import com.google.android.material.tabs.TabLayout
import com.itb.minescope.R

class ListFragment : Fragment(R.layout.fragment_list) {
    //ATTRIBUTES
    private lateinit var recyclerView: RecyclerView
    private lateinit var filtersButton: ImageView
    private lateinit var settingsButton: ImageView
    private lateinit var searchView: SearchView
    private lateinit var tabLayout: TabLayout

    //Booleans
    private var isOpaque = false

    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RECYCLER VIEW SET UP
        recyclerView = view.findViewById(R.id.recycler_view)
        filtersButton = view.findViewById(R.id.filters_icon)
        settingsButton = view.findViewById(R.id.settings_icon)
        searchView = view.findViewById(R.id.search_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        tabLayout = view.findViewById(R.id.tab_layout)

        loadMinerals(null)

        filtersButton.setOnClickListener {
            findNavController().navigate(R.id.listToFilters)
        }

        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.listToSettings)
        }

        //SEARCH
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //TEXT SUBMIT
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            //TEXT CHANGE
            override fun onQueryTextChange(newText: String?): Boolean {
                loadMinerals(newText!!)
                return false
            }
        })


        //TABS
        if (!viewModel.isTransparentFilters) {
            tabLayout.getTabAt(1)!!.select()
            isOpaque = true
            loadMinerals(null)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //SELECTED
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    isOpaque = false
                    loadMinerals(null)
                    viewModel.isTransparentFilters = true
                }
                else {
                    isOpaque = true
                    loadMinerals(null)
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
     */
    private fun loadMinerals(searchedText: String?) {
        if (isOpaque) {
            val principalAdapter = MineralsListAdapter(isOpaque, viewModel)

            if (searchedText != null)
                viewModel.opaqueMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setOpaqueMineralsList(it.filter { mineral -> mineral.nom.lowercase().contains(searchedText.lowercase()) }.toMutableList()) })
            else
                viewModel.opaqueMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setOpaqueMineralsList(it) })

            recyclerView.adapter = principalAdapter
        }
        else {
            val principalAdapter = MineralsListAdapter(isOpaque, viewModel)

            if (searchedText != null)
                viewModel.transparentMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setTransparentMineralsList(it.filter { mineral -> mineral.name.lowercase().contains(searchedText.lowercase()) }.toMutableList()) })
            else
                viewModel.transparentMineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setTransparentMineralsList(it) })

            recyclerView.adapter = principalAdapter
        }
    }
}
