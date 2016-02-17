package com.bromancelabs.beatbox.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bromancelabs.beatbox.R
import com.bromancelabs.beatbox.utils.BeatBoxKt
import com.bromancelabs.beatbox.utils.SoundKt
import kotlinx.android.synthetic.main.fragment_beat_box.*
import kotlinx.android.synthetic.main.list_item_sound_button.view.*
import java.util.ArrayList;

class BeatBoxFragmentKt : Fragment() {

    companion object {
        val GRID_COLUMNS = 3
        fun newInstance() = BeatBoxFragmentKt()
    }

    val beatBox by lazy { BeatBoxKt(activity) }
    val recyclerView by lazy { rv_fragment_beat_box }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_beat_box, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(activity, GRID_COLUMNS)
        recyclerView.adapter = SoundAdapterKt(beatBox.sounds)
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    inner class SoundAdapterKt(val sounds: ArrayList<SoundKt>) : RecyclerView.Adapter<SoundAdapterKt.SoundHolderKt>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolderKt {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sound_button, parent, false)
            return SoundHolderKt(view)
        }

        override fun onBindViewHolder(soundHolder: SoundHolderKt, position: Int) {
            soundHolder.bindSound(sounds.get(position))
        }

        override fun getItemCount() = sounds.size

        inner class SoundHolderKt(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var sound: SoundKt? = null

            fun bindSound(sound: SoundKt) {
                this.sound = sound
                itemView.btn_list_item_sound.text = sound.name
                itemView.btn_list_item_sound.setOnClickListener { beatBox.play(this.sound as SoundKt) }
            }
        }
    }
}