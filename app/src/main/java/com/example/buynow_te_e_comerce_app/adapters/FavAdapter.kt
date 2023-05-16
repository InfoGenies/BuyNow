package com.example.buynow_te_e_comerce_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buynow_te_e_comerce_app.data.FavorEntity
import com.example.buynow_te_e_comerce_app.R

class FavAdapter(private val ctx: Context, val listener: FavItemClickAdapter) :
    RecyclerView.Adapter<FavAdapter.FavViewHolder>() {
    private var favlist: ArrayList<FavorEntity> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val View = LayoutInflater.from(ctx).inflate(R.layout.fav_item, parent, false)
        return FavViewHolder(View)
    }

    override fun onBindViewHolder(holder: FavAdapter.FavViewHolder, position: Int) {
        val product: FavorEntity = favlist.get(position)

        Glide.with(ctx)
            .load(product.productImage)
            .into(holder.Image)
        holder.like.setImageResource(R.drawable.ic_fav_24)
        holder.delete.setOnClickListener{
            listener.onItemDeleteClick(product)
        }
        holder.Image.setOnClickListener{
            listener.onItemClickdetail(product)
        }
    }

    override fun getItemCount(): Int {
        return favlist.size
    }
    fun updateList(newList: List<FavorEntity>) {
        favlist.clear()
        favlist.addAll(newList)
        notifyDataSetChanged()
    }


    public class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Image: ImageView = itemView.findViewById(R.id.productImage_singleProduct)
        val like: ImageView = itemView.findViewById(R.id.productAddToFav_singleProduct)
        val delete: ImageView = itemView.findViewById(R.id.delete_single)


    }

    interface FavItemClickAdapter {
        fun onItemDeleteClick(product: FavorEntity)
        fun onItemClickdetail(product: FavorEntity)


    }
}