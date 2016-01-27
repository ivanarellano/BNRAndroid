package com.bromancelabs.geoquiz.utils

import android.view.View

fun View.onClick(listenerFunction: () -> Unit) {
    val listener = View.OnClickListener { listenerFunction.invoke() }
    this.setOnClickListener(listener)
}