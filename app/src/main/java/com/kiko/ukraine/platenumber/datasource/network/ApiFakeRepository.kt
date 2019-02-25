package com.kiko.ukraine.platenumber.datasource.network

import com.kiko.ukraine.platenumber.domain.entity.PlateInfo
import com.kiko.ukraine.platenumber.domain.repository.PlateInfoRepository
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.delay
import java.lang.Thread.sleep
import java.util.*

object ApiFakeRepository : PlateInfoRepository {

    override fun getPlateInformation(plateNumber: String): Single<PlateInfo> {
        val plate = PlateInfo(
            1, "AA9295AA", "Opel", "Combo", 2007, "blue",
            "passenger", "gasoline", 2543, 1000, 1200, "B",
            "SUV", 5, Date(), "6GDKSGOITEMV4363GGSL", "ПЕРЕРЕЄСТРАЦІЯ ТЗ НА НОВ. ВЛАСН. ПО ДОГОВОРУ УКЛАДЕНОМУ В ТСЦ",
            Date(), "ТСЦ 2642",
            "https://firebasestorage.googleapis.com/v0/b/chat-8c185.appspot.com/o/opel.png?alt=media",
            "https://firebasestorage.googleapis.com/v0/b/chat-8c185.appspot.com/o/opel_combo.jpg?alt=media", false
        )

        if (plateNumber == "AA9295AA")
            return Single.just(plate)

        return Single.error(Throwable("PLATE_NOT_EXIST"))
    }
}