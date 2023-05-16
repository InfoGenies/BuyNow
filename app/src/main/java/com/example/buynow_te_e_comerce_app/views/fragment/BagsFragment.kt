package com.example.buynow_te_e_comerce_app.views.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.buynow_te_e_comerce_app.adapters.BagAdapter
import com.example.buynow_te_e_comerce_app.data.ProductEntity
import com.example.buynow_te_e_comerce_app.viewModels.ProdutRoomViewModel
import com.example.buynow_te_e_comerce_app.databinding.FragmentBagsBinding


class BagsFragment : Fragment(), BagAdapter.CartItemClickAdapter {
    private lateinit var binding: FragmentBagsBinding
    lateinit var viewModel: ProdutRoomViewModel
    lateinit var bagProdAdapter: BagAdapter
    lateinit var bagRecView: RecyclerView
    lateinit var animationView: LottieAnimationView
    lateinit var totalPriceBagFrag: TextView
    lateinit var bottomCartLayout: LinearLayout
    lateinit var emptyBagMsgLayout: LinearLayout
    lateinit var MybagText: TextView
    lateinit var Item: ArrayList<ProductEntity>
    var sum: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBagsBinding.inflate(inflater, container, false);
        Item = ArrayList()
        InitialView()
        bagRecView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bagProdAdapter = BagAdapter(activity as Context, this)
        bagRecView.adapter = bagProdAdapter

        viewModel = ViewModelProvider(this).get(ProdutRoomViewModel::class.java)
        viewModel.allproducts.observe(viewLifecycleOwner, Observer { List ->
            List?.let {
                bagProdAdapter.updateList(it)
                Item.clear()
                sum = 0
                Item.addAll(it)
                if (List.size == 0) {
                    animationView.playAnimation()
                    animationView.loop(true)
                    bottomCartLayout.visibility = View.GONE
                    MybagText.visibility = View.GONE
                    emptyBagMsgLayout.visibility = View.VISIBLE

                } else {
                    emptyBagMsgLayout.visibility = View.GONE
                    bottomCartLayout.visibility = View.VISIBLE
                    MybagText.visibility = View.VISIBLE
                    animationView.pauseAnimation()
                }

                Item.forEach {
                    sum += it.price
                }
                totalPriceBagFrag.text = "$" + sum
            }
        })

        return binding.getRoot()
    }

    private fun InitialView() {
        bagRecView = binding.cartRecView
        animationView = binding.animationViewCartPage
        totalPriceBagFrag = binding.totalPriceBagFrag
        bottomCartLayout = binding.bottomCartLayout
        emptyBagMsgLayout = binding.emptyBagMsgLayout
        MybagText = binding.MybagText

        animationView.playAnimation()
        animationView.loop(true)
        bottomCartLayout.visibility = View.GONE
        MybagText.visibility = View.GONE
        emptyBagMsgLayout.visibility = View.VISIBLE

    }

    override fun onItemDeleteClick(product: ProductEntity) {
        viewModel.deleteCart(product)
            }

    override fun onItemUpdateClickPlus(product: ProductEntity) {
        if ( product.qua< 10){
            var price_product = product.price/product.qua
            var quantite = product.qua + 1
            product.qua = quantite
            product.price = price_product * quantite
            viewModel.updateCart(product)
        }

    }

    override fun onItemUpdateClickMinus(product: ProductEntity) {
        if ( product.qua> 1){
            var price_product = product.price/product.qua
            var quantite = product.qua - 1
            product.qua = quantite
            product.price = price_product * quantite
            viewModel.updateCart(product)
        }    }

}