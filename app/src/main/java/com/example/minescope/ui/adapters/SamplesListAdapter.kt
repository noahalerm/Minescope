package com.example.minescope.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.data.models.OpaqueMineral
import com.example.minescope.data.models.OpaqueMineralSample
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.data.models.TransparentMineralSample
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.example.minescope.ui.views.ListFragmentDirections
import com.example.minescope.ui.views.MineralFragmentDirections
import com.squareup.picasso.Picasso
import kotlin.math.min

class SamplesListAdapter(private val isOpaque: Boolean) : RecyclerView.Adapter<SamplesListAdapter.MineralListViewHolder>(){
    //ATTRIBUTES
    private var transparentMineralSamples = mutableListOf<TransparentMineralSample>()
    private var opaqueMineralSamples = mutableListOf<OpaqueMineralSample>()

    //METHODS
    /**
     * This method is used to obtain a list of samples.
     * @param sampleList List of samples
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setTransparentMineralSamplesList(mineralList: MutableList<TransparentMineralSample>){
        transparentMineralSamples = mineralList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOpaqueMineralSamplesList(mineralList: MutableList<OpaqueMineralSample>){
        opaqueMineralSamples = mineralList
        notifyDataSetChanged()
    }

    //ADAPTER METHODS
    /**
     * This method is used to create a View Holder and to set up it's view.
     * @param parent Adapter's parent (used to get context)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineralListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample,parent,false)

        return MineralListViewHolder(view)
    }

    /**
     * This method is used to set up the data of each item.
     * @param holder View Holder
     * @param position Current item
     */
    override fun onBindViewHolder(holder: MineralListViewHolder, position: Int) {
        if (isOpaque) {
            holder.bindData(null, opaqueMineralSamples[position])
            holder.itemView.setOnClickListener {
                val action = MineralFragmentDirections.mineralToSample(opaqueMineralSamples[position].id, isOpaque)
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }else{
            holder.bindData(transparentMineralSamples[position], null)
            holder.itemView.setOnClickListener {
                val action = MineralFragmentDirections.mineralToSample(transparentMineralSamples[position].id, isOpaque)
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }
    }

    /**
     * This method is used to get the total amount of items in the Recycler View.
     */
    override fun getItemCount() : Int {
        return if (isOpaque) {
            opaqueMineralSamples.size
        }else{
            transparentMineralSamples.size
        }
    }

    //VIEW HOLDER
    class MineralListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //ATTRIBUTES
        private var sampleNameAndSurname: TextView = itemView.findViewById(R.id.sample_namesurname)
        private var sampleShortDesc: TextView = itemView.findViewById(R.id.sample_short_description)
        private var sampleLPA: ImageView = itemView.findViewById(R.id.image)
        private var sampleLPNA: ImageView = itemView.findViewById(R.id.image2)

        //METHODS
        /**
         * This method is used to set up the data of each item of the sample list.
         */
        fun bindData(transparentMineralSample: TransparentMineralSample?, opaqueMineralSample: OpaqueMineralSample?) {
            if (transparentMineralSample == null){
                sampleNameAndSurname.text = opaqueMineralSample?.name
                sampleShortDesc.text = opaqueMineralSample?.cleavage
                Picasso.get().load(opaqueMineralSample?.imageLPA+"3363.jpg")
                    .noFade().placeholder(sampleLPA.drawable).into(sampleLPA)
                Picasso.get().load(opaqueMineralSample?.imageLPNA+"3544.jpg")
                    .noFade().placeholder(sampleLPNA.drawable).into(sampleLPNA)
            }else{
                sampleNameAndSurname.text = transparentMineralSample.name
                sampleShortDesc.text = transparentMineralSample.alteration
                Picasso.get().load(transparentMineralSample.imageLPA+"3363.jpg")
                    .noFade().placeholder(sampleLPA.drawable).into(sampleLPA)
                Picasso.get().load(transparentMineralSample.imageLPNA+"3544.jpg")
                    .noFade().placeholder(sampleLPNA.drawable).into(sampleLPNA)
            }
        }
    }
}
