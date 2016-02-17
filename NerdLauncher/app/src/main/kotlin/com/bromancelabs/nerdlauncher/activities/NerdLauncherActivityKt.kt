package com.bromancelabs.nerdlauncher.activities

import android.support.v4.app.Fragment
import com.bromancelabs.nerdlauncher.fragments.NerdLauncherFragmentKt


class NerdLauncherActivityKt : SingleFragmentActivityKt() {

    override fun createFragment() = NerdLauncherFragmentKt.newInstance()
}