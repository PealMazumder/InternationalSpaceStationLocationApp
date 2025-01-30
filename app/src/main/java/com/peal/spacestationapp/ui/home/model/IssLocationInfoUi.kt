package com.peal.spacestationapp.ui.home.model

import com.peal.spacestationapp.domain.model.IssLocationInfo
import com.peal.spacestationapp.domain.utils.toFormattedTime
import java.time.ZoneId


/**
 * Created by Peal Mazumder on 30/1/25.
 */

data class IssLocationInfoUi(
    val latitude: String = "",
    val longitude: String = "",
    val localTime: String = "",
    val utcTime: String = "",
    val country: String = "",
)

fun IssLocationInfo.toIssLocationInfoUi(): IssLocationInfoUi {
    return IssLocationInfoUi(
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        localTime = timeStamp.toFormattedTime(),
        utcTime = timeStamp.toFormattedTime(ZoneId.of("UTC"))
    )
}
