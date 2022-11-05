package dev.android.play.test

import com.google.firebase.installations.Utils
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


@RunWith(MockitoJUnitRunner::class)
internal class ValidatePrivateMethodsViewModelTest {


    lateinit var validatePrivateMethodsViewModel: ValidatePrivateMethodsViewModel

    @Before
    fun setUp() {
        validatePrivateMethodsViewModel = ValidatePrivateMethodsViewModel()
    }

    @After
    fun tearDown() {
    }

    fun `Return true if email and password is not empty`() {
        //assertTrue(validatePrivateMethodsViewModel.validateLoginCredentials("Siba@~gmail.com", "asdasdasd"))
    }


    @Test
    @Throws(
        NoSuchMethodException::class,
        InvocationTargetException::class,
        IllegalAccessException::class
    )
    fun givenANonNullInteger_WhenDoubleInteger_ThenDoubleIt() {
        assertEquals(
            true,
            getValidateLoginCredentialsMethod().invoke(true, "NASDAQ", "asdasdasdasd")
        )
        getValidateLoginCredentialsMethod().invoke(true, "", "")
    }


    @Throws(NoSuchMethodException::class)
    private fun getValidateLoginCredentialsMethod(): Method {
        val method: Method = Utils::class.java.getDeclaredMethod(
            "validateLoginCredentials",
            ValidatePrivateMethodsViewModel::class.java
        )
        method.isAccessible = true
        return method
    }

}