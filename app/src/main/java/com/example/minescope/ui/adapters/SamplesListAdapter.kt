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
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.example.minescope.ui.views.MineralFragmentDirections
import com.squareup.picasso.Picasso

class SamplesListAdapter(private val viewModel: MinescopeViewModel) : RecyclerView.Adapter<SamplesListAdapter.SampleListViewHolder>(){
    //ATTRIBUTES
    private var samples = mutableListOf<String>()

    //METHODS
    /**
     * This method is used to obtain a list of samples.
     * @param sampleList List of samples
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setSampleList(sampleList: MutableList<String>){
        samples = sampleList
        notifyDataSetChanged()
    }

    //ADAPTER METHODS
    /**
     * This method is used to create a View Holder and to set up it's view.
     * @param parent Adapter's parent (used to get context)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample,parent,false)

        return SampleListViewHolder(view)
    }

    /**
     * This method is used to set up the data of each item.
     * @param holder View Holder
     * @param position Current item
     */
    override fun onBindViewHolder(holder: SampleListViewHolder, position: Int) {
        holder.bindData(samples[position], viewModel)

        //ON CLICK
        holder.itemView.setOnClickListener {
            val action = MineralFragmentDirections.mineralToSample(/*samples[position].id*/)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    /**
     * This method is used to get the total amount of items in the Recycler View.
     */
    override fun getItemCount() : Int {
        return samples.size
    }


    //VIEW HOLDER
    class SampleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //ATTRIBUTES
        private var sampleNameAndSurname: TextView = itemView.findViewById(R.id.sample_namesurname)
        private var sampleShortDesc: TextView = itemView.findViewById(R.id.sample_short_description)
        private var sampleLPA: ImageView = itemView.findViewById(R.id.image)
        private var sampleLPNA: ImageView = itemView.findViewById(R.id.image2)

        //METHODS
        /**
         * This method is used to set up the data of each item of the sample list.
         */
        fun bindData(sample: String, viewModel: MinescopeViewModel) {
            sampleNameAndSurname.text = sample
            sampleShortDesc.text = sample
            Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPA/IMG_3363.jpg")
                .noFade().placeholder(sampleLPA.drawable).into(sampleLPA)
            Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_3544.jpg")
                .noFade().placeholder(sampleLPNA.drawable).into(sampleLPNA)
        }
    }
}
