package com.kiko.ukraine.platenumber.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["number"], unique = true)])
data class ShortPlateInfo(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val number: String,
    val vendor: String,
    val model: String,
    val year: Int,
    val color: String,
    @ColumnInfo(name = "logo_url")
    val logoUrl: String,
    @ColumnInfo(name = "timestamp")
    val timestampOfCheck: Long
)
{
    companion object {
        fun fromPlateInfo(plate: PlateInfo): ShortPlateInfo {
            return ShortPlateInfo(
                number = plate.number,
                vendor = plate.vendor,
                model = plate.model,
                year = plate.year,
                color = plate.color,
                logoUrl = plate.logoUrl,
                timestampOfCheck = Date().time
            )
        }
    }
}