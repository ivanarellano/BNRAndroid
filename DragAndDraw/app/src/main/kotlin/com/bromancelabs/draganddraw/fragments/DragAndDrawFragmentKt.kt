package com.bromancelabs.draganddraw.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bromancelabs.draganddraw.R

import kotlinx.android.synthetic.main.fragment_drag_and_draw.*

class DragAndDrawFragmentKt : Fragment() {
    companion object {
        fun newInstance() = DragAndDrawFragmentKt()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.kt_fragment_drag_and_draw, container, false)
    }
}