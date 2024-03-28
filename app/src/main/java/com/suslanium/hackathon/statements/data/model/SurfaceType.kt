package com.suslanium.hackathon.statements.data.model

enum class SurfaceType(val description: String) {
    ASPHALT("Асфальтное"),
    COBBLESTONE("Из булыжника"),
    CRUSHED_STONE("Из измельченного камня"),
    GROUND("Грунтовое"),
    SAND("Песчаное"),
    CONCRETE("Бетонное"),
    REINFORCED_CONCRETE("Железобетонное"),
    COMBINED("Комбинированное"),
    OTHER("Другое")
}