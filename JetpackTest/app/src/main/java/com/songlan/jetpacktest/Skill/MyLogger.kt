package com.songlan.jetpacktest.Skill

import android.util.Log

object MyLogger {
    private const val VERBOSE = 0
    private const val DEBUG = 1
    private const val INFO = 2
    private const val WARN = 3
    private const val ERROR = 4

    private val level = VERBOSE

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE)
            Log.v(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG)
            Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO)
            Log.i(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN)
            Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR)
            Log.e(tag, msg)
    }

}