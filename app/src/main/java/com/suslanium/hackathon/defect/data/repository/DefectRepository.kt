package com.suslanium.hackathon.defect.data.repository

import android.net.Uri
import com.suslanium.hackathon.defect.data.model.DefectModel
import com.suslanium.hackathon.defect.data.model.DefectStatus
import kotlinx.coroutines.delay

class DefectRepository {

    suspend fun getDefectInfo(defectId: String): DefectModel {
        delay(1000)
        return DefectModel(
            defectId = defectId,
            latitude = 52.6840083333,
            longitude = 35.7913066667,
            defectType = "Поперечная трещина",
            defectDistance = 35.0,
            status = DefectStatus.COMPLETED,
            picturesAfterRepair = listOf(
                "https://images-cdn.cian.site/realty/articles/content/30767/doroga1lrg.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/44/Aminyevskoye_Highway_16.08.2013.jpg",
                "https://dpru.obs.ru-moscow-1.hc.sbercloud.ru/images/article/2023/06/08/62f625aa-b269-4844-b691-49b6a9738739.jpg",
                "https://img.freepik.com/free-photo/shot-of-a-highway-road-surrounded-by-dried-grass-fields-under-a-sky-during-sunset_181624-10934.jpg"
            ),
            picturesBeforeRepair = listOf(
                "https://hydrog-vostok.ru/img/stati/842/1.jpg",
                "https://asfaltok.ru/wp-content/uploads/2019/06/2123345423523www.pxfuel.com_cr.jpg",
                "https://skterra.ru//wp-content/uploads/2019/10/statya-treshhiny-v-asfalte-1-e-foto.jpg"
            )
        )
    }

    suspend fun markDefectAsCompleted(defectId: String, photos: List<Uri>) {
        delay(1000)
    }

    suspend fun rejectDefect(defectId: String) {
        delay(1000)
    }

    suspend fun returnDefect(defectId: String) {
        delay(1000)
    }

}