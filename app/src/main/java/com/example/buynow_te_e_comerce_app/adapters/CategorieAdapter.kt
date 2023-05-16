package com.example.buynow_te_e_comerce_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buynow_te_e_comerce_app.models.Categorie
import com.example.buynow_te_e_comerce_app.R

class CategorieAdapter(private val ctx: Context, val listener: ItemClickAdapter) :
    RecyclerView.Adapter<CategorieAdapter.CatViewHolder>() {
    private val catlist: ArrayList<Categorie> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategorieAdapter.CatViewHolder {
        val catview = LayoutInflater.from(ctx).inflate(R.layout.category_single, parent, false)
        return CatViewHolder(catview)
    }

    override fun onBindViewHolder(holder: CategorieAdapter.CatViewHolder, position: Int) {
        val cat:Categorie = catlist.get(position)
        holder.apply {
            name.text = cat.name
            Glide.with(ctx)
                .load(cat.image)
                .into(image)
        }

    }

    override fun getItemCount(): Int {
        return catlist.size
    }


    fun updateList(newList: List<Categorie>) {
        catlist.clear()
        catlist.addAll(newList)
        notifyDataSetChanged()
    }
    public class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.categoryImage_CateSingle)
        val name: TextView = itemView.findViewById(R.id.categoryName_CateSingle)
    }


    interface ItemClickAdapter {
        fun onItemClickDetail(catname: String)


    }
}