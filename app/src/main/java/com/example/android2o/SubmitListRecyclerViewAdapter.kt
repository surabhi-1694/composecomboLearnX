package com.example.android2o

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android2o.placeholder.ListAdapterItem

class SubmitListRecyclerViewAdapter(private var itemClickCallback:(position:Int)->Unit,
                                    private var itemEditedCallback:(item:ListAdapterItem, position:Int,updatedList:List<ListAdapterItem>)->Unit):
    ListAdapter<ListAdapterItem, SubmitListRecyclerViewAdapter.ViewHolder>(ItemDiffCallback()) {

        private var selectedPosition = 0
        private var previousSelectedposition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.raw_item_submitlisst, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("LOG_NAME ",getItem(position).toString())
        val item = getItem(position)
        holder.textBoldName.text = getItem(position).name
        holder.textName.text = getItem(position).id.toString()
        if(item.isEdited){
            Log.e("isEDITED_TRUE ",item.toString())
            holder.imgShape.setBackgroundColor(ContextCompat.getColor(holder.imgShape.context,R.color.purple_200))
        }else{
            Log.e("isEDITED_false ",item.toString())
            holder.imgShape.setBackgroundColor(ContextCompat.getColor(holder.imgShape.context,R.color.purple_500))

        }

        holder.imgSide.setOnClickListener {
            itemClickCallback(position)
        }
        holder.imgEdit.setOnClickListener { v ->
            updateSelection(item,position)
        }
    }

    // Update the selection logic
    private fun updateSelection(item: ListAdapterItem, selectedPosition: Int) {
        // Update the isSelected state for all items
        val updatedList = currentList.mapIndexed { index, item ->
            item.copy(isEdited = index == selectedPosition) // Only one item is selected
        }
        Log.e("UPdateSElection_ ",updatedList.toString())
        submitList(updatedList) // Refresh the list
        //need to pass updatedlist to callback as currentlist from adapter won't give updatelist immediately
        itemEditedCallback(item,selectedPosition,updatedList)

    }



        //    check content are same or not or not
    class ItemDiffCallback : DiffUtil.ItemCallback<ListAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: ListAdapterItem,
            newItem: ListAdapterItem
        ): Boolean {
            Log.e("DIFF_areItemsTheSame_ ","")
            return oldItem.id == newItem.id // Compare unique IDs or another unique field
        }

        override fun areContentsTheSame(
            oldItem: ListAdapterItem,
            newItem: ListAdapterItem
        ): Boolean {
            Log.e("DIFF_areContentsTheSame_ ","")

            return oldItem == newItem // Compare the entire object to check for content changes

        }

    }

    //init id of view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textBoldName:AppCompatTextView = itemView.findViewById(R.id.txt_boldname)
        val textName:AppCompatTextView = itemView.findViewById(R.id.txt_name)
        val imgSide:AppCompatImageView = itemView.findViewById(R.id.img_side)
        val imgShape:AppCompatImageView = itemView.findViewById(R.id.img_shape)
        val imgEdit:AppCompatImageView = itemView.findViewById(R.id.img_edit)

    }
}