package org.mathieu.domain.repositories

interface VibrationService {
    /**
     * Triggers a vibration on the device for the specified duration.
     *
     * @param duration The duration in milliseconds for which the device should vibrate.
     */
    fun vibrate(duration: Long)
}
