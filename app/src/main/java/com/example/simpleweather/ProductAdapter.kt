package com.example.simpleweather

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


class ProductAdapter(
    private val products: MutableList<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun toggleStrikeThrough(tvProductTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvProductTitle.paintFlags = tvProductTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvProductTitle.paintFlags = tvProductTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addProduct(product: Product) {
        products.add(product)
        notifyItemInserted(products.size - 1)
    }

    fun removeSelectedProducts(balance: Int): Int {
        var remains: Int = balance
        for (product in products) {
            if (product.isChecked) {
                remains -= product.price
                products.remove(product)
            }
        }
        //products.removeAll { it.isChecked }
        notifyDataSetChanged()
        return remains
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val curProduct = products[position]
        holder.itemView.apply {
            tvProductTitle.text = curProduct.title
            cdSelectedProduct.isChecked = curProduct.isChecked
            tvProductPrice.text = curProduct.price.toString()
            toggleStrikeThrough(tvProductTitle, curProduct.isChecked)
            cdSelectedProduct.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvProductTitle, isChecked)
                curProduct.isChecked = !curProduct.isChecked
            }

        }
    }

    override fun getItemCount() = products.size
}