package com.example.diplomapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Flower (@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id")
                    var id: Long,
                    @ColumnInfo(name = "price")
                    var price: String,
                    @ColumnInfo(name = "name")
                    var name: String,
                    @ColumnInfo(name = "url")
                    var url: String) {
}