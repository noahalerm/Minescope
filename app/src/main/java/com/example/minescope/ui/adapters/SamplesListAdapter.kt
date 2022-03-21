package com.example.minescope.ui.adapters

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
    var samples = mutableListOf<String>()

    fun setMarkerList(markerList: MutableList<String>){
        samples = markerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListViewHolder {
        // Estem creant la vista del layout del item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample,parent,false)

        // I retornem un FiewListViewHolder que gestionara aquesta vista
        return SampleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleListViewHolder, position: Int) {
        // Cridarem al holder (FilmListViewHolder) pasanli per parametre un item de la llista
        holder.bindData(samples[position], viewModel)

        holder.itemView.setOnClickListener {
            val action = MineralFragmentDirections.mineralToSample(/*samples[position].id*/)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        // Retorno la mida de la llista
        return samples.size
    }

    // Aquesta clase representa una fila concreta del RecycleView i s'encarrega de dibuixar aquesta fila
    inner class SampleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var sampleNameAndSurname: TextView
        private var sampleShortDesc: TextView
        private var sampleLPA: ImageView
        private var sampleLPNA: ImageView

        init {
            sampleNameAndSurname = itemView.findViewById(R.id.sample_namesurname)
            sampleShortDesc = itemView.findViewById(R.id.sample_short_description)
            sampleLPA = itemView.findViewById(R.id.image)
            sampleLPNA = itemView.findViewById(R.id.image2)
        }

        // Funcio que servira per assignar la informacio d'un item de la llista a la vista que el representa
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