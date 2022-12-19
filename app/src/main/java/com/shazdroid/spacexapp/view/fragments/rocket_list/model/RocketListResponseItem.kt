package com.shazdroid.spacexapp.view.fragments.rocket_list.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rocket_tbl")
data class RocketListResponseItem(
    @ColumnInfo(name = "active")
    @SerializedName("active")
    val active: Boolean?,
    @ColumnInfo(name = "cost_per_launch")
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int?,
    @ColumnInfo(name = "country")
    @SerializedName("country")
    val country: String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "diameter")
    @SerializedName("diameter")
    val diameter: Diameter?,
    @ColumnInfo(name = "engines")
    @SerializedName("engines")
    val engines: Engines?,
    @ColumnInfo(name = "flickr_images")
    @SerializedName("flickr_images")
    val flickrImages: List<String?>?,
    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: Height?,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "success_rate_pct")
    @SerializedName("success_rate_pct")
    val successRatePct: Int?,
    @ColumnInfo(name = "wikipedia")
    @SerializedName("wikipedia")
    val wikipedia: String?
) {
    data class Diameter(
        @ColumnInfo(name = "feet")
        @SerializedName("feet")
        val feet: Double?,
        @ColumnInfo(name = "meters")
        @SerializedName("meters")
        val meters: Double?
    )

    data class Engines(
        @ColumnInfo(name = "number")
        @SerializedName("number")
        val number: Int?,
    )

    data class Height(
        @SerializedName("feet")
        val feet: Double?,
        @SerializedName("meters")
        val meters: Double?
    )
}

