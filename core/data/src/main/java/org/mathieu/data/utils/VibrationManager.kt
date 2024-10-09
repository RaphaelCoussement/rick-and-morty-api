package org.mathieu.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Vibrator
import org.mathieu.domain.repositories.VibrationService

/**
 * Manages vibration functionality for the device, implementing the [VibrationService] interface.
 *
 * @property context The application context used to access system services.
 */

class VibrationManager(private val context: Context) : VibrationService {
    private val vibrator: Vibrator? = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator


    @SuppressLint("MissingPermission")
    override fun vibrate(duration: Long) {
        vibrator?.vibrate(duration)
    }
}
