package com.example.presentation.listProducts

import com.example.domain.Product

interface ListProductsContract {
    interface View {
        fun showProducts(products: List<Product>)
    }

    interface Presenter {
        fun loadProducts()
    }


}
