package com.example.buynow_te_e_comerce_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.buynow_te_e_comerce_app.data.ProductEntity
import com.example.buynow_te_e_comerce_app.models.Product
import com.example.buynow_te_e_comerce_app.R
import com.example.buynow_te_e_comerce_app.utils.Extensions.toast
import com.example.buynow_te_e_comerce_app.viewModels.ProdutRoomViewModel
import com.example.buynow_te_e_comerce_app.databinding.ActivityProductDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    lateinit var vieModel: ProdutRoomViewModel
    private lateinit var product: Product
    lateinit var productImage: ImageView
    lateinit var backIv: ImageView
    lateinit var productName: TextView
    lateinit var productPrice: TextView
    lateinit var productBrand: TextView
    lateinit var productDes: TextView
    lateinit var productRating: RatingBar
    lateinit var add_to_bag: Button
    var pPrice: Int = 0
    var qua: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setInitializeView()
        product = intent.getParcelableExtra<Product>("Product")!!
        setData()

        backIv.setOnClickListener {
            onBackPressed()
        }
        add_to_bag.setOnClickListener {
            val bottomSheetDialod = BottomSheetDialog(
                this, R.style.BottomSheetDialogTheme
            )
            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.fragment_add_to_bag,
                findViewById<ConstraintLayout>(R.id.bottomSheet)
            )
            val button = bottomSheetView.findViewById<View>(R.id.addToCart_BottomSheet)
            button.setOnClickListener {

                val quanti = bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                    .toInt()
                pPrice = quanti * product.productPrice.toInt()
                println(" le prix du produit est est calculer $pPrice")
                addProductToBag()
                bottomSheetDialod.dismiss()

            }
            bottomSheetView.findViewById<LinearLayout>(R.id.minusLayout).setOnClickListener {
                if (bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                        .toInt() > 1
                ) {
                    qua--
                    bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom)
                        .setText(qua.toString())
                }
            }

            bottomSheetView.findViewById<LinearLayout>(R.id.plusLayout).setOnClickListener {
                if (bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                        .toInt() < 10
                ) {
                    qua++
                    bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom)
                        .setText(qua.toString())
                }
            }

            bottomSheetDialod.setContentView(bottomSheetView)
            bottomSheetDialod.show()

        }


    }

    private fun addProductToBag() {
        vieModel = ViewModelProvider(this).get(ProdutRoomViewModel::class.java)
        println(" le prix du produit est $pPrice")
        vieModel.insert(ProductEntity(product.productName, qua, pPrice, product.productId, product.productImage))
        toast("Add to Bag Successfully")
    }

    private fun setInitializeView() {
        productImage = binding.productImageProductDetailsPage
        backIv = binding.backIvProfileFrag
        productName = binding.productNameProductDetailsPage
        productPrice = binding.productPriceProductDetailsPage
        productBrand = binding.productBrandProductDetailsPage
        productDes = binding.productDesProductDetailsPage
        productRating = binding.productRatingSingleProduct
        add_to_bag = binding.addToCartProductDetailsPage
    }

    private fun setData() {
        Glide.with(applicationContext)
            .load(product.productImage)
            .into(productImage)
        productName.text = product.productName
        productPrice.text = product.productPrice
        productBrand.text = product.productBrand
        productDes.text = product.productDes
        productRating.rating = product.productRating.toFloat()

    }


}