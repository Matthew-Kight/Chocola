package com.sosauce.chocola.core.domain.model

data class Album(
    val id: Long = 0,
    val name: String = "",
    val artist: String = "",
    val tracks: List<CuteTrack> = emptyList()
)
