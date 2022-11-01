package dev.android.play.background

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.testing.TestListenableWorkerBuilder
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class AppWorkerTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testMyWork() {
        // Get the ListenableWorker
        val worker =
            TestListenableWorkerBuilder<AppWorker>(context).build()
        // Run the worker synchronously
        val result = worker.startWork().get()
        assertThat(result, `is`(Result.success("")))
    }
}