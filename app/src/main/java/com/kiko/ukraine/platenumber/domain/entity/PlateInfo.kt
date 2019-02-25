package com.kiko.ukraine.platenumber.domain.entity

import java.util.*


data class PlateInfo(
    val id : Int? = null,
    val number : String,
    val vendor : String,
    val model : String,
    val year : Int,
    val color : String,
    val type : String? = null,
    val fuel : String? = null,
    val engineCapacity : Int? = null,
    val mass : Int? = null,
    val maxMass : Int? = null,
    val category : String? = null,
    val body : String? = null,
    val seating : Int? = null,
    val dataOfFirstRegistration : Date? = null,
    val vin : String? = null,
    val lastRecord : String? = null,
    val lastRecordDate : Date? = null,
    val lastPlace : String? = null,
    val logoUrl : String,
    val carPhotoUrl : String? = null,
    val wanted : Boolean = false
)
{
    fun getFullName() = "$vendor $model"
}