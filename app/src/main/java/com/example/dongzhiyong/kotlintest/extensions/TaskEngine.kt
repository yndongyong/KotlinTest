package com.example.dongzhiyong.kotlintest.extensions

import java.util.concurrent.*
import kotlin.properties.Delegates

/**
 * Created by Dong on 2016/11/7.
 */
object TaskEngine {

    private var executor: ExecutorService by Delegates.notNull<ExecutorService>()

    fun getExecutor(): Executor {
        if (executor == null) {
            synchronized(TaskEngine) {
                var count = if (Runtime.getRuntime().availableProcessors() > 0) Runtime
                        .getRuntime().availableProcessors() * 2 else 2
                executor = Executors.newFixedThreadPool(count)
            }
        }
        return executor
    }

    fun runOnThread(action: () -> Unit) {
        getExecutor().execute(action)
    }
}