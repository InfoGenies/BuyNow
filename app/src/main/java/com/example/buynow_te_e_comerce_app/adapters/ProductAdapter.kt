package com.example.buynow_te_e_comerce_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.R

class ProductAdapter(val productList: ArrayList<Product>, context: Context,val listener:ItemClickAdapter) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    val ctx: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val productView =
            LayoutInflater.from(parent.context).inflate(R.layout.single_product, parent, false)
        return ViewHolder(productView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product: Product = productList[position]
        Glide.with(ctx)
            .load(product.productImage)
            .into(holder.productImage_singleProduct)
        if (product.productHave.equals(true)){
            holder.discountTv_singleProduct.text = product.productDisCount
            holder.discount_singleProduct.visibility = View.VISIBLE
        }else{
            holder.discount_singleProduct.visibility = View.VISIBLE
            holder.discountTv_singleProduct.text = "New"
        }
        holder.productBrandName_singleProduct.text = product.productBrand
        holder.productName_singleProduct.text = product.productName
        holder.productPrice_singleProduct.text = "$" + product.productPrice
        holder.productRating_singleProduct.rating = product.productRating.toFloat()
        holder.itemView.setOnClickListener {
           listener.onItemClickdetail(product)
        }
        holder.productAddToFav_singleProduct.setOnClickListener {
            listener.onItemClickfav(product, holder.productAddToFav_singleProduct)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage_singleProduct: ImageView =
            itemView.findViewById(R.id.productImage_singleProduct)
        val productAddToFav_singleProduct: ImageView =
            itemView.findViewById(R.id.productAddToFav_singleProduct)
        val productRating_singleProduct: RatingBar =
            itemView.findViewById(R.id.productRating_singleProduct)
        val productBrandName_singleProduct: TextView =
            itemView.findViewById(R.id.productBrandName_singleProduct)
        val discountTv_singleProduct: TextView =
            itemView.findViewById(R.id.discountTv_singleProduct)
        val productName_singleProduct: TextView =
            itemView.findViewById(R.id.productName_singleProduct)
        val productPrice_singleProduct: TextView =
            itemView.findViewById(R.id.productPrice_singleProduct)
        val discount_singleProduct =
            itemView.findViewById<LinearLayout>(R.id.discount_singleProduct)


    }
    interface ItemClickAdapter {
        fun onItemClickdetail(product: Product)
        fun onItemClickfav(product: Product, imageView:ImageView)


    }
}