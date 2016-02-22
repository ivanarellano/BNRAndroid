package com.bromancelabs.nerdlauncher.activities

import com.bromancelabs.nerdlauncher.fragments.NerdLauncherFragmentKt

class NerdLauncherActivityKt : SingleFragmentActivityKt() {

    override fun createFragment() = NerdLauncherFragmentKt.newInstance()
}