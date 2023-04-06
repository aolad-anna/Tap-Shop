package com.example.tap_shop.model.response

import com.google.gson.annotations.SerializedName

data class GetProduct(
    @SerializedName("id")
    var id : Int?  = null,
    @SerializedName("title")
    var title : String? = null,
    @SerializedName("price")
    var price : Int? = null,
    @SerializedName("description")
    var description : String? = null,
    @SerializedName("images")
    var images : ArrayList<String> = arrayListOf(),
    @SerializedName("creationAt")
    var creationAt : String? = null,
    @SerializedName("updatedAt")
    var updatedAt : String? = null,
    @SerializedName("category")
    var category : Category? = Category()
  )

data class Category (
    @SerializedName("id")
    var id : Int?    = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("image")
    var image : String? = null,
    @SerializedName("creationAt")
    var creationAt : String? = null,
    @SerializedName("updatedAt")
    var updatedAt  : String? = null
)