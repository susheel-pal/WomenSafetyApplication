package com.example.womensafetyapplication

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.womensafetyapplication.models.Parent
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class ParentAdapter(options: FirestoreRecyclerOptions<Parent>): FirestoreRecyclerAdapter<Parent, ParentAdapter.ParentViewHolder> (options){

    class ParentViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val postName: TextView = itemView.findViewById(R.id.showPostName)
        var postNumber: TextView = itemView.findViewById(R.id.showPostNumber)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_parent, parent, false))
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int, model: Parent) {
        holder.postName.text = model.text
        holder.postNumber.text = model.number


    }

}