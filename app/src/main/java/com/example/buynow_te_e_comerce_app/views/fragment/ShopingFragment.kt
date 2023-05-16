package com.example.buynow_te_e_comerce_app.views.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buynow_te_e_comerce_app.adapters.CategorieAdapter
import com.example.buynow_te_e_comerce_app.viewModels.ProductVM
import com.example.buynow_te_e_comerce_app.databinding.FragmentShopingBinding

class ShopingFragment : Fragment(), CategorieAdapter.ItemClickAdapter {
    private lateinit var binding: FragmentShopingBinding
    private lateinit var viewModel: ProductVM
    lateinit var catRecView: RecyclerView
    lateinit var catAdapter: CategorieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductVM::class.java)
        viewModel.getCat()
        viewModel.getlisteFilter("Clothing")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShopingBinding.inflate(inflater, container, false);

        InitialView()
        catRecView.layoutManager =
            GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        catAdapter = CategorieAdapter(activity as Context, this)
        catRecView.adapter = catAdapter

        viewModel.CatList.observe(viewLifecycleOwner, Observer { List ->
            List?.let {
                catAdapter.updateList(it)
            }
        })
        // felring data from firestore
        viewModel.List.observe(viewLifecycleOwner , Observer { List ->
            List?.let {

            }
        })


        return binding.getRoot()
    }

    private fun InitialView() {
        catRecView = binding.categoriesRecView


    }

    override fun onItemClickDetail(catname: String) {
        TODO("Not yet implemented")
    }


}