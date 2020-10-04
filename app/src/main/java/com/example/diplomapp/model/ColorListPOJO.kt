package com.example.diplomapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ColorListPOJO {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("pageURL")
    @Expose
    var pageURL: String? = null

    @SerializedName("tags")
    @Expose
    var tags: String? = null

    @SerializedName("webformatURL")
    @Expose
    var webformatURL: String? = null

    @SerializedName("largeImageURL")
    @Expose
    var largeImageURL: String? = null

    @SerializedName("downloads")
    @Expose
    var downloads: Int? = null
}