package com.example.minescope.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.data.models.OpaqueMineral
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.example.minescope.ui.views.ListFragmentDirections
import com.example.minescope.ui.views.MineralFragmentDirections
import kotlin.math.min

class MineralsListAdapter(private val isOpaque: Boolean) : RecyclerView.Adapter<MineralsListAdapter.MineralListViewHolder>(){
    //ATTRIBUTES
    private var transparentMinerals = mutableListOf<TransparentMineral>()
    private var opaqueMinerals = mutableListOf<OpaqueMineral>()

    //METHODS
    /**
     * This method is used to obtain a list of samples.
     * @param sampleList List of samples
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setTransparentMineralsList(mineralList: MutableList<TransparentMineral>){
        transparentMinerals = mineralList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOpaqueMineralsList(mineralList: MutableList<OpaqueMineral>){
        opaqueMinerals = mineralList
        notifyDataSetChanged()
    }

    //ADAPTER METHODS
    /**
     * This method is used to create a View Holder and to set up it's view.
     * @param parent Adapter's parent (used to get context)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineralListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mineral,parent,false)

        return MineralListViewHolder(view)
    }

    /**
     * This method is used to set up the data of each item.
     * @param holder View Holder
     * @param position Current item
     */
    override fun onBindViewHolder(holder: MineralListViewHolder, position: Int) {
        if (isOpaque) {
            holder.bindData(null, opaqueMinerals[position])
            holder.itemView.setOnClickListener {
                val action = ListFragmentDirections.listToMineral(opaqueMinerals[position].id, isOpaque)
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }else{
            holder.bindData(transparentMinerals[position], null)
            holder.itemView.setOnClickListener {
                val action = ListFragmentDirections.listToMineral(transparentMinerals[position].id, isOpaque)
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }
    }

    /**
     * This method is used to get the total amount of items in the Recycler View.
     */
    override fun getItemCount() : Int {
        return if (isOpaque) {
            opaqueMinerals.size
        }else{
            transparentMinerals.size
        }
    }

    //VIEW HOLDER
    class MineralListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //ATTRIBUTES
        private var name: TextView = itemView.findViewById(R.id.mineral_name)
        private var chemical_formula: TextView = itemView.findViewById(R.id.mineral_chemical_formula)
        private var description: TextView = itemView.findViewById(R.id.mineral_description)

        //METHODS
        /**
         * This method is used to set up the data of each item of the sample list.
         */
        fun bindData(transparentMineral: TransparentMineral?, opaqueMineral: OpaqueMineral?) {
            if (transparentMineral == null){
                name.text = opaqueMineral?.name
                chemical_formula.text = opaqueMineral?.chemicalFormula
                description.text = opaqueMineral?.anisotropy
            }else{
                name.text = transparentMineral.name
                chemical_formula.text = transparentMineral.chemicalFormula
                description.text = transparentMineral.alteration
            }
        }
    }
}
