package com.example.minescope.ui.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.data.models.FilterOption

class FilterOptionsAdapter(private val options: List<FilterOption>, private val textView: TextView,
                           private val filter: String, private val dialog: AlertDialog)
    : RecyclerView.Adapter<FilterOptionsAdapter.FilterOptionsViewHolder>() {
    //ADAPTER METHODS
    /**
     * This method is used to create a View Holder and to set up it's view.
     * @param parent Adapter's parent (used to get context)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterOptionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_option_item, parent,false)

        return FilterOptionsViewHolder(view)
    }

    /**
     * This method is used to set up the data of each item.
     * @param holder View Holder
     * @param position Current item
     */
    override fun onBindViewHolder(holder: FilterOptionsViewHolder, position: Int) {
        holder.bindData(options[position], textView, filter, dialog)

        //ON CLICK
        holder.itemView.setOnClickListener{
            FilterOptionsViewHolder(it).dismissDialog(textView, filter, options[position].name, dialog)
        }
    }

    /**
     * This method is used to get the total amount of items in the Recycler View.
     */
    override fun getItemCount() : Int {
        return options.size
    }


    //VIEW HOLDER
    class FilterOptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //ATTRIBUTES
        private var optionText: TextView = itemView.findViewById(R.id.option)
        private var optionInfo: ImageView = itemView.findViewById(R.id.option_info)

        //METHODS
        /**
         * This method is used to set up the data of each item of the options list.
         */
        fun bindData(option: FilterOption, textView: TextView, filter: String, dialog: AlertDialog) {
            optionText.text = option.name

            //ON CLICK
            //Option Text
            optionText.setOnClickListener {
                dismissDialog(textView, filter, optionText.text.toString(), dialog)
            }

            //Option Info
            optionInfo.setOnClickListener {
                setUpOptionInfoDialog(option)
            }
        }

        /**
         * This method is used to close a dialog after selecting an option.
         * @param textView Text View
         * @param filter Filter selected (e.g. relief, pleochroism...)
         * @param selectedOption Option chosen (e.g. low, medium, high...)
         * @param dialog AlertDialog builder
         */
        @SuppressLint("SetTextI18n")
        fun dismissDialog(textView: TextView, filter: String, selectedOption: String, dialog: AlertDialog) {
            textView.text = "$filter: $selectedOption"
            dialog.dismiss()
        }

        /**
         * This method is used to create a dialog with the description of a given option.
         * @param option Selected option
         */
        private fun setUpOptionInfoDialog(option: FilterOption) {
            //CUSTOM DIALOG
            val infoBuilder = AlertDialog.Builder(optionText.context, R.style.CustomDialog)
            val viewInfoDialog = LayoutInflater.from(optionText.context).inflate(R.layout.filter_option_description_dialog, null)
            infoBuilder.setView(viewInfoDialog)
            val dialogInfo = infoBuilder.create()
            dialogInfo.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //Set to Transparent to only see the custom bg.
            dialogInfo.window!!.attributes.windowAnimations = R.style.DialogAnimation
            dialogInfo.show()

            //ATTRIBUTES
            val optionView: TextView = viewInfoDialog.findViewById(R.id.option)
            val description: TextView = viewInfoDialog.findViewById(R.id.description)
            val image1: ImageView = viewInfoDialog.findViewById(R.id.image1)
            val image2: ImageView = viewInfoDialog.findViewById(R.id.image2)
            val image3: ImageView = viewInfoDialog.findViewById(R.id.image3)

            optionView.text = option.name
            description.text = option.description

            if (option.image1 != null) {
                image1.setImageResource(option.image1)
                image2.setImageResource(option.image2!!)
                image3.setImageResource(option.image3!!)
            }
        }
    }
}
