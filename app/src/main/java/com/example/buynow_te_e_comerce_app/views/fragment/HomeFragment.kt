package com.example.buynow_te_e_comerce_app.views.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.example.buynow_te_e_comerce_app.adapters.ImageSlideAdapter
import com.example.buynow_te_e_comerce_app.adapters.ProductAdapter
import com.example.buynow_te_e_comerce_app.adapters.SaleAdapter
import com.example.buynow_te_e_comerce_app.data.FavorEntity
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.R
import com.example.buynow_te_e_comerce_app.viewModels.FavRoomViewModel
import com.example.buynow_te_e_comerce_app.viewModels.ProductVM
import com.example.buynow_te_e_comerce_app.views.ProductDetailsActivity
import com.example.buynow_te_e_comerce_app.databinding.FragmentHomeBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), ProductAdapter.ItemClickAdapter, SaleAdapter.ItemSaleClickAdapter {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var cartViewModel: ProductVM
    private lateinit var favViewModel: FavRoomViewModel
    lateinit var animationView: LottieAnimationView
    lateinit var newLayout: LinearLayout
    lateinit var saleLayout: LinearLayout


    lateinit var viewPagerAdapter: ImageSlideAdapter
    lateinit var indicator: WormDotsIndicator
    lateinit var viewpager: ViewPager
    lateinit var newProduct: ArrayList<Product>
    lateinit var saleProduct: ArrayList<Product>
    lateinit var newProdAdapter: ProductAdapter
    lateinit var newRecView: RecyclerView
    lateinit var saleAdapter: SaleAdapter
    lateinit var SalRecView: RecyclerView

    val images: ArrayList<String>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(this).get(ProductVM::class.java)
        favViewModel = ViewModelProvider(this).get(FavRoomViewModel::class.java)
        cartViewModel.getCoverProduct()
        cartViewModel.getAllProduct()
        cartViewModel.getSale()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //
        animationView = binding.animationView
        newLayout = binding.newLayout
        saleLayout = binding.saleLayout
        viewpager = binding.viewpager
        newProduct = ArrayList()
        saleProduct = ArrayList()

        /////
        newRecView = binding.newRecView
        SalRecView = binding.saleRecView

        hideLayout()

        GlobalScope.launch(Dispatchers.Main) {
            delay(800)
            if (view != null) {
                cartViewModel.CoverList.observe(viewLifecycleOwner, Observer { list ->
                    for (i in 0..list.size - 1) {
                        images?.add(list.get(i).productImage)
                    }
                    viewPagerAdapter.notifyDataSetChanged()
                })
                cartViewModel.NewList.observe(viewLifecycleOwner, { list ->
                    newProdAdapter = ProductAdapter(list, activity as Context, this@HomeFragment)
                    newRecView.adapter = newProdAdapter
                })
                cartViewModel.saleList.observe(viewLifecycleOwner, { list ->
                    saleAdapter = SaleAdapter(list, activity as Context, this@HomeFragment)
                    SalRecView.adapter = saleAdapter
                })
                delay(2000)

                showLayout()
            }
        }
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        images?.let {
            viewPagerAdapter = ImageSlideAdapter(requireContext(), it)
            viewpager.adapter = viewPagerAdapter
            indicator = binding.wormDotsIndicator
            indicator.attachTo(viewpager)
        }


    }

    private fun hideLayout() {

        viewpager.visibility = View.GONE
        newLayout.visibility = View.GONE
        saleLayout.visibility = View.GONE
        animationView.visibility = View.VISIBLE

    }

    private fun showLayout() {

        animationView.visibility = View.GONE
        viewpager.visibility = View.VISIBLE
        newLayout.visibility = View.VISIBLE
        saleLayout.visibility = View.VISIBLE
    }

    override fun onItemClickdetail(product: Product) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("Product", product)
        startActivity(intent)
    }

    override fun onItemClickfav(product: Product, imageView: ImageView) {

        val fav = FavorEntity(
            product.productId,
            product.productName,
            product.productBrand,
            product.productPrice,
            product.productImage,
            product.productDes,
            product.productHave,
            product.productDisCount,
            product.productRating,
            product.productCategory,
            true
        )
        favViewModel.check(product.productId).observe(viewLifecycleOwner, { exist ->
            if (exist.equals(false)) {
                println("la valeur de est $exist")

                imageView.setImageResource(R.drawable.ic_fav_24)
                favViewModel.insert(fav)

            }else{
                if (exist.equals(true)) {
                    imageView.setImageResource(R.drawable.ic_fav)
                    favViewModel.deleteCart(fav)
                }


            }


        })


        }








    override fun onItemsaleClickdetail(product: Product) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra("Product", product)
        startActivity(intent)
    }

    override fun onItemsaleClickfav(product: Product, imageView: ImageView) {
        val fav = FavorEntity(
            product.productId,
            product.productName,
            product.productBrand,
            product.productPrice,
            product.productImage,
            product.productDes,
            product.productHave,
            product.productDisCount,
            product.productRating,
            product.productCategory,
            true
        )
        favViewModel.check(product.productId).observe(viewLifecycleOwner, { exist ->
            if (exist.equals(false)) {
                println("la valeur de est $exist")

                imageView.setImageResource(R.drawable.ic_fav_24)
                favViewModel.insert(fav)

            }else{
                if (exist.equals(true)) {
                    imageView.setImageResource(R.drawable.ic_fav)
                    favViewModel.deleteCart(fav)
                }


            }


        })
    }


}