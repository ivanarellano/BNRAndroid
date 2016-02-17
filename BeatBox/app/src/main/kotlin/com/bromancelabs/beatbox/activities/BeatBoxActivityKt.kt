package com.bromancelabs.beatbox.activities

import android.support.v4.app.Fragment
import com.bromancelabs.beatbox.fragments.BeatBoxFragmentKt

class BeatBoxActivityKt : SingleFragmentActivityKt() {

    override fun createFragment() = BeatBoxFragmentKt.newInstance()
}