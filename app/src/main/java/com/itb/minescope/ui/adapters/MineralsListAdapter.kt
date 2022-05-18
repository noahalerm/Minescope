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
            if (filters!!.relleu != "")
                transparentMinerals = transparentMinerals.filter { it.relleu.lowercase().contains(filters.relleu.lowercase()) }.toMutableList()
            if (filters.colors != "")
                transparentMinerals = transparentMinerals.filter { it.colors.lowercase().contains(filters.colors.lowercase()) }.toMutableList()
            if (filters.pleocroisme != "")
                transparentMinerals = transparentMinerals.filter { it.pleocroisme.lowercase().contains(filters.pleocroisme.lowercase()) }.toMutableList()
            if (filters.nom_dir_exfoliacio != "")
                transparentMinerals = transparentMinerals.filter { it.nom_dir_exfoliacio.lowercase().contains(filters.nom_dir_exfoliacio.lowercase()) }.toMutableList()
            if (filters.angle_dir_exfoliacio != "")
                transparentMinerals = transparentMinerals.filter { it.angle_dir_exfoliacio!!.lowercase().contains(filters.angle_dir_exfoliacio!!.lowercase()) }.toMutableList()
            if (filters.colors_interferencia != "")
                transparentMinerals = transparentMinerals.filter { it.colors_interferencia.lowercase().contains(filters.colors_interferencia.lowercase()) }.toMutableList()
            if (filters.extincio != "")
                transparentMinerals = transparentMinerals.filter { it.extincio!!.lowercase().contains(filters.extincio!!.lowercase()) }.toMutableList()
            if (filters.maclat != "")
                transparentMinerals = transparentMinerals.filter { it.maclat!!.lowercase().contains(filters.maclat!!.lowercase()) }.toMutableList()
            if (filters.figura_interferencia != "")
                transparentMinerals = transparentMinerals.filter { it.figura_interferencia!!.lowercase().contains(filters.figura_interferencia!!.lowercase()) }.toMutableList()
            if (filters.signe_optic != "")
                transparentMinerals = transparentMinerals.filter { it.signe_optic!!.lowercase().contains(filters.signe_optic!!.lowercase()) }.toMutableList()
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
            if (filters.reflectivitat != "")
                opaqueMinerals = opaqueMinerals.filter { it.reflectivitat.lowercase().contains(filters.reflectivitat.lowercase()) }.toMutableList()
            if (filters.pleocroisme != "")
                opaqueMinerals = opaqueMinerals.filter { it.pleocroisme.lowercase().contains(filters.pleocroisme.lowercase()) }.toMutableList()
            if (filters.resistencia_polit != "")
                opaqueMinerals = opaqueMinerals.filter { it.resistencia_polit.lowercase().contains(filters.resistencia_polit.lowercase()) }.toMutableList()
            if (filters.anisotropia != "")
                opaqueMinerals = opaqueMinerals.filter { it.anisotropia.lowercase().contains(filters.anisotropia.lowercase()) }.toMutableList()
            if (filters.colors_interferencia != "")
                opaqueMinerals = opaqueMinerals.filter { it.colors_interferencia.lowercase().contains(filters.colors_interferencia.lowercase()) }.toMutableList()
            if (filters.reflexions_internes != "")
                opaqueMinerals = opaqueMinerals.filter { it.reflexions_internes.lowercase().contains(filters.reflexions_internes.lowercase()) }.toMutableList()
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
        @RequiresApi(Build.VERSION_CODES.N)
        fun bindData(transparentMineral: TransparentMineral?, opaqueMineral: OpaqueMineral?) {
            if (transparentMineral == null){
                name.text = opaqueMineral?.nom
                if (opaqueMineral?.formula != null)
                    chemicalFormula.text = Html.fromHtml(opaqueMineral.formula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                description.text = opaqueMineral?.anisotropia
            }else{
                name.text = transparentMineral.nom
                chemicalFormula.text = Html.fromHtml(transparentMineral.formula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                description.text = transparentMineral.alteracio
            }
        }
    }
}
