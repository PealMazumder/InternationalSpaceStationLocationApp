package com.peal.spacestationapp.domain.utils
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Created by Peal Mazumder on 30/1/25.
 */

fun Long?.toFormattedTime(
    zoneId: ZoneId = ZoneId.systemDefault(),
    pattern: String = "MMMM dd, yyyy hh:mm a"
): String {
    return try {
        this?.let { timestamp ->
            val instant = Instant.ofEpochSecond(timestamp)
            val formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(zoneId)
            formatter.format(instant)
        } ?: "Unknown Time"
    } catch (e: Exception) {
        "Invalid Time"
    }
}