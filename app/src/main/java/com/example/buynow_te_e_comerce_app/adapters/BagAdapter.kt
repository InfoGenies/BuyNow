package com.example.buynow_te_e_comerce_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buynow_te_e_comerce_app.data.ProductEntity
import com.example.buynow_te_e_comerce_app.R

class BagAdapter(private val ctx: Context, val listener: CartItemClickAdapter) :
    RecyclerView.Adapter<BagAdapter.BagViewHolder>() {
    private var baglist: ArrayList<ProductEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagAdapter.BagViewHolder {
        val cartView = LayoutInflater.from(ctx).inflate(R.layout.bag_item_single, parent, false)
        return BagViewHolder(cartView)
    }

    override fun onBindViewHolder(holder: BagAdapter.BagViewHolder, position: Int) {
        val product: ProductEntity = baglist.get(position)
        holder.cartName.text = product.name
        holder.cartPrice.text = "$" + product.price
        holder.quantityTvCart.text = product.qua.toString()
        Glide.with(ctx)
            .load(product.Image)
            .into(holder.cartImage)

        holder.cartMore.setOnClickListener {
            listener.onItemDeleteClick(product)
        }
        holder.plus.setOnClickListener {
            listener.onItemUpdateClickPlus(product)
        }
        holder.minus.setOnClickListener {
            listener.onItemUpdateClickMinus(product)
        }
    }

    fun updateList(newList: List<ProductEntity>) {
        baglist.clear()
        baglist.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return baglist.size
    }

    public class BagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartImage: ImageView = itemView.findViewById(R.id.cartImage)
        val cartMore: ImageView = itemView.findViewById(R.id.cartMore)
        val cartName: TextView = itemView.findViewById(R.id.cartName)
        val cartPrice: TextView = itemView.findViewById(R.id.cartPrice)
        val quantityTvCart: TextView = itemView.findViewById(R.id.quantityTvCart)
        val plus: ImageView = itemView.findViewById(R.id.plusLayout)
        val minus: ImageView = itemView.findViewById(R.id.minusLayout)


    }

    interface CartItemClickAdapter {
        fun onItemDeleteClick(product: ProductEntity)
        fun onItemUpdateClickPlus(product: ProductEntity)
        fun onItemUpdateClickMinus(product: ProductEntity)


    }
}