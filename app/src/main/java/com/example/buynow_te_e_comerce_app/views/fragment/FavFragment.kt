package com.example.buynow_te_e_comerce_app.views.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.buynow_te_e_comerce_app.adapters.FavAdapter
import com.example.buynow_te_e_comerce_app.data.FavorEntity
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.viewModels.FavRoomViewModel
import com.example.buynow_te_e_comerce_app.views.ProductDetailsActivity
import com.example.buynow_te_e_comerce_app.databinding.FragmentFavBinding


class FavFragment : Fragment(), FavAdapter.FavItemClickAdapter {
    private lateinit var binding: FragmentFavBinding
    lateinit var viewModel: FavRoomViewModel
    lateinit var favAdapter: FavAdapter
    lateinit var favRecView: RecyclerView
    lateinit var animationView: LottieAnimationView
    lateinit var emptyBagMsgLayout: LinearLayout
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavRoomViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavBinding.inflate(inflater, container, false);
        InitialView()
        InitiaRecycler();


        return binding.getRoot()
    }

    private fun InitiaRecycler() {
        favRecView.layoutManager =
            GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        favAdapter = FavAdapter(activity as Context, this)
        favRecView.adapter = favAdapter
        viewModel.allproducts.observe(viewLifecycleOwner, Observer { List ->
            List?.let {
                println(" la taille de la list est ${List.size}")
                favAdapter.updateList(it)
                if (List.size == 0) {
                    animationView.playAnimation()
                    animationView.loop(true)
                    toolbar.visibility = View.INVISIBLE
                    emptyBagMsgLayout.visibility = View.VISIBLE

                } else {
                    emptyBagMsgLayout.visibility = View.GONE
                    animationView.pauseAnimation()
                }

            }
        })

    }

    private fun InitialView() {
        toolbar = binding.toolbar
        favRecView = binding.FavRecView
        animationView = binding.animationViewCartPage
        emptyBagMsgLayout = binding.emptyBagMsgLayout
        animationView.playAnimation()
        animationView.loop(true)
        emptyBagMsgLayout.visibility = View.VISIBLE


    }

    override fun onItemDeleteClick(product: FavorEntity) {
viewModel.deleteCart(product)
    }

    override fun onItemClickdetail(vprod: FavorEntity) {
        val product = Product(
            vprod.productId,
            vprod.productName,
            vprod.productBrand,
            vprod.productPrice,
            vprod.productImage,
            vprod.productDes,
            vprod.productHave,
            vprod.productDisCount,
            vprod.productRating,
            vprod.productCategory
        )
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("Product", product)
        startActivity(intent)
    }


}
