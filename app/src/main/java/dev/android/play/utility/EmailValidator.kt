package dev.android.play.utility

import androidx.core.util.PatternsCompat

object EmailValidator {
    fun isValidEmail(emailId: String) =
        emailId.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(emailId).matches()
}