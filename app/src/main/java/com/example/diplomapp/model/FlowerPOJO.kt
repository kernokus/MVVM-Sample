package com.example.diplomapp.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class FlowerPOJO {
    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("totalHits")
    @Expose
    var totalHits: Int? = null

    @SerializedName("hits")
    @Expose
    var colorList: List<ColorListPOJO>? = null

}