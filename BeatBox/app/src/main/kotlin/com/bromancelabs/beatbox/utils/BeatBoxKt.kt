package com.bromancelabs.beatbox.utils

import android.content.Context
import android.content.res.AssetManager
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
import java.util.*

class BeatBoxKt(context: Context) {
    companion object {
        val TAG = BeatBoxKt::class.java.simpleName
        val SOUNDS_FOLDER = "sample_sounds"
        val MAX_SOUNDS = 5
    }

    val assets: AssetManager
    val soundPool: SoundPool
    var sounds = ArrayList<SoundKt>()

    init {
        assets = context.assets
        soundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)
        loadSounds()
    }

    fun loadSounds() {
        var soundNames: Array<String>? = null

        try {
            soundNames = assets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found ${soundNames.size} sounds")
        } catch (e : IOException) {
            Log.e(TAG, "Could not list assets", e)
        }

        if (null != soundNames) {
            for (filename in soundNames) {
                try {
                    val sound = SoundKt("$SOUNDS_FOLDER/$filename")
                    load(sound)
                    sounds.add(sound)
                } catch (e : IOException){
                    Log.e(TAG, "Could not load sound $filename", e)
                }
            }
        }
    }

    fun load(sound: SoundKt) {
        sound.id = soundPool.load(assets.openFd(sound.assetPath), 1)
    }

    fun play(sound: SoundKt) {
        if (null != sound.id) {
            soundPool.play(sound.id as Int, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun release() = soundPool.release()
}