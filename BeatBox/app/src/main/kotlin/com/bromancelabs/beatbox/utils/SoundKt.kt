package com.bromancelabs.beatbox.utils

data class SoundKt(val assetPath: String) {
    val name: String
    var id: Int? = null

    init {
        val components = assetPath.split("/")
        val filename = components[components.size - 1]
        name = filename.replace(".wav", "")
    }
}