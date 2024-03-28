package com.suslanium.hackathon.createdefect.data.repository

import com.suslanium.hackathon.createdefect.data.model.CreateDefectModel
import com.suslanium.hackathon.createdefect.data.model.DefectType
import kotlinx.coroutines.delay

class CreateDefectRepository {

    suspend fun getDefectTypes(): List<DefectType> {
        delay(1000)
        return listOf(
            DefectType("Стирание краски на знаке", false),
            DefectType("Сминание, изгибание, нарушение целостности дорожного знака", true),
            DefectType("Вандализм (граффити, надписи, наклейки, следы краски, отверстия)", true),
            DefectType("Перекрытие знака растительностью", false),
            DefectType("Снежный налёт на знаке", false),
        )
    }

    suspend fun createRequest(defectModel: CreateDefectModel) {
        delay(1000)
    }

}