package com.example.presentation.productDetail

import com.example.domain.Product

interface ProductDetailContract {

    interface View {
        fun showProductDetail(product: Product)
    }

    interface Presenter {
        fun loadProduct()
    }

}
