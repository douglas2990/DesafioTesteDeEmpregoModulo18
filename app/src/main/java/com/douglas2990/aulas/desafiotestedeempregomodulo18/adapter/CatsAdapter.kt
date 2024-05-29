package com.douglas2990.aulas.desafiotestedeempregomodulo18.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.aulas.desafiotestedeempregomodulo18.R
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Data

class CatsAdapter(private val list: List<Data>)
    : RecyclerView.Adapter<CatsAdapter.CatsViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cats_adapter, parent, false)

        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val listName = list[position]
        val title_cat = listName.title

        holder.texViewNome.text = title_cat

    }

    class CatsViewHolder(view: View): RecyclerView.ViewHolder(view){
        var texViewNome: TextView = view.findViewById(R.id.txtTitulo)

    }

    override fun getItemCount(): Int {
        return list.size
    }


}