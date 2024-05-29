package com.douglas2990.aulas.desafiotestedeempregomodulo18.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.aulas.desafiotestedeempregomodulo18.databinding.RecyclerViewItemBinding
import com.douglas2990.aulas.desafiotestedeempregomodulo18.model.Image

class ImageCatsAdapter (private val list: List<Image>)
    : RecyclerView.Adapter<ImageCatsAdapter.ListCatsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCatsViewHolder {

        return ListCatsViewHolder(
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListCatsViewHolder, position: Int) {

        val listCats = list[position]

        val tipo = listCats.type
        val imagem = listCats.link

        if (tipo == "image/jpeg"){
            Glide.with(holder.imgViewCats)
                .load(imagem)
                .into(holder.imgViewCats)
        }

    }

    class ListCatsViewHolder(binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgViewCats = binding.imageView1

    }

    override fun getItemCount(): Int {
        return list.size
    }





}