package com.itb.minescope.ui.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
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
            if (filters!!.color != "")
                opaqueMinerals = opaqueMinerals.filter { it.color.lowercase().contains(filters.color.lowercase()) }.toMutableList()
            if (filters.reflectance != "")
                opaqueMinerals = opaqueMinerals.filter { it.reflectance.lowercase().contains(filters.reflectance.lowercase()) }.toMutableList()
            if (filters.pleochroism != "")
                opaqueMinerals = opaqueMinerals.filter { it.pleochroism.lowercase().contains(filters.pleochroism.lowercase()) }.toMutableList()
            if (filters.polishingHardness != "")
                opaqueMinerals = opaqueMinerals.filter { it.polishingHardness.lowercase().contains(filters.polishingHardness.lowercase()) }.toMutableList()
            if (filters.anisotropism != "")
                opaqueMinerals = opaqueMinerals.filter { it.anisotropism.lowercase().contains(filters.anisotropism.lowercase()) }.toMutableList()
            if (filters.interference_colors != "")
                opaqueMinerals = opaqueMinerals.filter { it.interference_colors.lowercase().contains(filters.interference_colors.lowercase()) }.toMutableList()
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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MineralListViewHolder, position: Int) {
        if (isOpaque) {
            holder.bindData(null, opaqueMinerals[position])
            holder.itemView.setOnClickListener {
                val action = ListFragmentDirections.listToMineral(opaqueMinerals[position].idMineral, isOpaque)
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
        @RequiresApi(Build.VERSION_CODES.N)
        fun bindData(transparentMineral: TransparentMineral?, opaqueMineral: OpaqueMineral?) {
            if (transparentMineral == null){
                name.text = opaqueMineral?.name
                if (opaqueMineral?.chemicalFormula != null)
                    chemicalFormula.text = Html.fromHtml(opaqueMineral.chemicalFormula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                description.text = opaqueMineral?.anisotropism
            }else{
                name.text = transparentMineral.name
                chemicalFormula.text = Html.fromHtml(transparentMineral.chemicalFormula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                description.text = transparentMineral.alteration
            }
        }
    }
}
