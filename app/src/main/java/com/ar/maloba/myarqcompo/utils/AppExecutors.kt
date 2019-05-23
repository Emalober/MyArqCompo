package com.ar.maloba.myarqcompo.utils

import java.util.concurrent.Executor
import javax.inject.Singleton

import android.os.Handler
import android.os.Looper

@Singleton
open class AppExecutors(val diskIO: Executor, val networkIO: Executor, val mainThread: Executor) {

    class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}