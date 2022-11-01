package dev.android.play.coroutines

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.R
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoroutineViewModel @Inject constructor() : ObservableViewModel() {

    private val TAG = "Coroutine"
    fun onClickHelloCoroutine(view: View) {

        var message = ""

        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            Log.i(TAG, "World!") // print after delay
            message += "World!"
        }
        Log.i(TAG, "Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
        message += "Hello, "
        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
    }

    fun onClickRunBlocking(view: View) {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            Log.i(TAG, "World!")
        }
        Log.i(TAG, "Hello,") // main thread continues here immediately
        runBlocking {     // but this expression blocks the main thread
            delay(5000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
        Log.i(TAG, "Siba")
    }

    fun onClickRunSuspend(view: View) {
        runBlocking {
            doWorld()
        }
        Log.i(TAG, "Hello")
    }

    private suspend fun doWorld() {
        delay(1000)
        Log.i(TAG, "World")
    }


    fun onCoroutineScopeClick(view: View) {
        runBlocking {
            doWorldWithCoroutineScope()
            Log.i(TAG, "DONE")
        }
    }

    // Concurrently executes both sections
    private suspend fun doWorldWithCoroutineScope() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            Log.i(TAG, "World 2")
        }
        launch {
            delay(1000L)
            Log.i(TAG, "World 1")
        }
        Log.i(TAG, "Hello")
    }


    fun onJobClick(view: View) {
        runBlocking {
            val job = launch {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancel() // cancels the job
            job.join() // waits for job's completion
            println("main: Now I can quit.")
        }
    }


    fun onCancellingCoroutine(view: View) {
        runBlocking {
            val job = launch {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancel() // cancels the job
            job.join() // waits for job's completion
            println("main: Now I can quit.")
        }
    }

    fun onCancelAndJoinCoroutine(view: View) {
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = viewModelScope.launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) { // computation loop, just wastes CPU
                    // print a message twice a second
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // cancels the job and waits for its completion
            println("main: Now I can quit.")
        }
    }

    fun onHandleCancellation(view: View) {
        runBlocking {
            val job = viewModelScope.launch(Dispatchers.Default) {
                repeat(5) { i ->
                    try {
                        // print a message twice a second
                        println("job: I'm sleeping $i ...")
                        delay(500)
                    } catch (e: Exception) {
                        // log the exception
                        println(e)
                    }
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // cancels the job and waits for its completion
            println("main: Now I can quit.")
        }
    }


    fun onExceptionClick(view: View) {
        Log.i(TAG, "Coroutines Exception")
        runBlocking {
            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                Log.i(TAG, "Exception Occurred")
            }

            val job =
                GlobalScope.launch(exceptionHandler) { // root coroutine, running in GlobalScope
                    Log.i(TAG, "Exception Triggered from GlobalScope.launch")
                    throw AssertionError()
                }
            val deferred =
                GlobalScope.async(exceptionHandler) { // also root, but async instead of launch
                    Log.i(TAG, "Exception Triggered from GlobalScope.async")
                    throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
                }
            joinAll(job, deferred)
        }
    }

    fun onCancelClick(view: View) {
        Log.i(TAG, "Cancelling Coroutines")

        runBlocking {
            val job = launch {
                val child = launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("Child is cancelled")
                    }
                }
                yield()
                println("Cancelling child")
                child.cancel()
                child.join()
                yield()
                println("Parent is not cancelled")
            }
            job.join()
        }
    }

    fun anotherCancelWithExceptionHandling(view: View) {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                launch { // the first child
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        withContext(NonCancellable) {
                            println("Children are cancelled, but exception is not handled until all children terminate")
                            delay(100)
                            println("The first child finished its non cancellable block")
                        }
                    }
                }
                launch { // the second child
                    delay(10)
                    println("Second child throws an exception")
                    throw ArithmeticException()
                }
            }
            job.join()
        }
    }


    fun onCoroutineAggregationClick(view: View) {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                val inner = launch { // all this stack of coroutines will get cancelled
                    launch {
                        launch {
                            throw IOException() // the original exception
                        }
                    }
                }
                try {
                    inner.join()
                } catch (e: CancellationException) {
                    println("Rethrowing CancellationException with original cause")
                    throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
                }
            }
            job.join()
        }
    }

    fun onLoginClick(view: View, data: String) {
        Log.i("LoginViewModel", data)
    }

    fun onRegisterClick(view: View) {
        view.findNavController().navigate(
            R.id.action_loginFragment_to_registerFragment, null,
            NavOptions.Builder().setPopUpTo(R.id.registerFragment, true).build()
        )
    }
}