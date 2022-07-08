package com.example.simpleweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private var sumPrice: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productAdapter = ProductAdapter(mutableListOf())

        rvTodoItems.adapter = productAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)


        btnAddProduct.setOnClickListener {
            val productTitle = etProductTitle.text.toString()
            val productPrice = etProductPrice.text.toString()
            if (productTitle.isNotEmpty() and productPrice.isNotEmpty()) {
                sumPrice += productPrice.toInt()
                tvSumPrice.text = "Сумма: $sumPrice руб."
                val product = Product(productTitle, productPrice.toInt())
                productAdapter.addProduct(product)

            }
            etProductTitle.text.clear()
            etProductPrice.text.clear()
        }

        btnRemoveProducts.setOnClickListener {
            sumPrice = productAdapter.removeSelectedProducts(sumPrice)
            tvSumPrice.text = "Сумма: $sumPrice руб."
        }
    }
}