package com.itb.minescope.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.ui.viewmodel.MinescopeViewModel
import com.itb.minescope.R
import com.itb.minescope.ui.views.ListFragmentDirections

class MineralsListAdapter(private val isOpaque: Boolean, private val viewModel: MinescopeViewModel) : RecyclerView.Adapter<MineralsListAdapter.MineralListViewHolder>(){
    //ATTRIBUTES
    private var transparentMinerals = mutableListOf<TransparentMineral>()
    private var opaqueMinerals = mutableListOf<OpaqueMineral>()

    //METHODS
    /**
     * This method is used to obtain a list of samples.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setTransparentMineralsList(mineralList: MutableList<TransparentMineral>){
        val filters = viewModel.transparentFilters

        transparentMinerals = mineralList

        //FILTERS
        if (viewModel.transparentFilters != null) {
            if (filters!!.relief != "")
                transparentMinerals = transparentMinerals.filter { it.relief.lowercase().contains(filters.relief.lowercase()) }.toMutableList()
            if (filters.colors != "")
                transparentMinerals = transparentMinerals.filter { it.colors.lowercase().contains(filters.colors.lowercase()) }.toMutableList()
            if (filters.pleochroism != "")
                transparentMinerals = transparentMinerals.filter { it.pleochroism.lowercase().contains(filters.pleochroism.lowercase()) }.toMutableList()
            if (filters.exfoliationDirectionName != "")
                transparentMinerals = transparentMinerals.filter { it.exfoliationDirectionName.lowercase().contains(filters.exfoliationDirectionName.lowercase()) }.toMutableList()
            if (filters.exfoliationDirectionAngles != "")
                transparentMinerals = transparentMinerals.filter { it.exfoliationDirectionAngles!!.lowercase().contains(filters.exfoliationDirectionAngles!!.lowercase()) }.toMutableList()
            if (filters.interferenceColorsOrder != "")
                transparentMinerals = transparentMinerals.filter { it.interferenceColorsOrder.lowercase().contains(filters.interferenceColorsOrder.lowercase()) }.toMutableList()
            if (filters.extinction != "")
                transparentMinerals = transparentMinerals.filter { it.extinction!!.lowercase().contains(filters.extinction!!.lowercase()) }.toMutableList()
            if (filters.twinning != "")
                transparentMinerals = transparentMinerals.filter { it.twinning!!.lowercase().contains(filters.twinning!!.lowercase()) }.toMutableList()
            if (filters.interferenceFigure != "")
                transparentMinerals = transparentMinerals.filter { it.interferenceFigure!!.lowercase().contains(filters.interferenceFigure!!.lowercase()) }.toMutableList()
            if (filters.opticSign != "")
                transparentMinerals = transparentMinerals.filter { it.opticSign!!.lowercase().contains(filters.opticSign!!.lowercase()) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOpaqueMineralsList(mineralList: MutableList<OpaqueMineral>){
        val filters = viewModel.opaqueFilters

        opaqueMinerals = mineralList

        //FILTERS
        if (viewModel.opaqueFilters != null) {
            if (filters!!.colors != "")
                opaqueMinerals = opaqueMinerals.filter { it.colors.lowercase().contains(filters.colors.lowercase()) }.toMutableList()
            if (filters.reflectivity != "")
                opaqueMinerals = opaqueMinerals.filter { it.reflectivity.lowercase().contains(filters.reflectivity.lowercase()) }.toMutableList()
            if (filters.pleochroism != "")
                opaqueMinerals = opaqueMinerals.filter { it.pleochroism.lowercase().contains(filters.pleochroism.lowercase()) }.toMutableList()
            if (filters.hardness != "")
                opaqueMinerals = opaqueMinerals.filter { it.hardness.lowercase().contains(filters.hardness.lowercase()) }.toMutableList()
            if (filters.anisotropy != "")
                opaqueMinerals = opaqueMinerals.filter { it.anisotropy.lowercase().contains(filters.anisotropy.lowercase()) }.toMutableList()
            if (filters.interferenceColors != "")
                opaqueMinerals = opaqueMinerals.filter { it.interferenceColors.lowercase().contains(filters.interferenceColors.lowercase()) }.toMutableList()
            if (filters.internalReflections != "")
                opaqueMinerals = opaqueMinerals.filter { it.internalReflections.lowercase().contains(filters.internalReflections.lowercase()) }.toMutableList()
        }
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
        private var chemicalFormula: TextView = itemView.findViewById(R.id.mineral_chemical_formula)
        private var description: TextView = itemView.findViewById(R.id.mineral_description)

        //METHODS
        /**
         * This method is used to set up the data of each item of the sample list.
         */
        fun bindData(transparentMineral: TransparentMineral?, opaqueMineral: OpaqueMineral?) {
            if (transparentMineral == null){
                name.text = opaqueMineral?.name
                chemicalFormula.text = opaqueMineral?.chemicalFormula
                description.text = opaqueMineral?.anisotropy
            }else{
                name.text = transparentMineral.name
                chemicalFormula.text = transparentMineral.chemicalFormula
                description.text = transparentMineral.alteration
            }
        }
    }
}
