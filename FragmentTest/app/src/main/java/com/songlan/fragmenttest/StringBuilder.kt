package com.songlan.fragmenttest

import java.lang.StringBuilder

operator fun StringBuilder.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this).append("\n")
    }
    return builder.toString()
}