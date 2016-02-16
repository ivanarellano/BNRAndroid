package com.bromancelabs.draganddraw.activities

import com.bromancelabs.draganddraw.fragments.DragAndDrawFragmentKt

class DragAndDrawActivityKt : SingleFragmentActivityKt() {
    override fun createFragment() = DragAndDrawFragmentKt.newInstance()
}