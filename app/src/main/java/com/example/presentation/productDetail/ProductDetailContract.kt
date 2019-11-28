package com.example.presentation.productDetail

import com.example.domain.Product

interface ProductDetailContract {

    interface View {
        fun showProductDetail(products: Product)
    }

    interface Presenter {
        fun loadProduct()
    }

}
