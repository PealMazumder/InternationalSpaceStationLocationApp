package com.peal.spacestationapp.data.mapper

import com.peal.spacestationapp.data.remote.model.IssResponse
import com.peal.spacestationapp.domain.model.IssLocationInfo


/**
 * Created by Peal Mazumder on 30/1/25.
 */


fun IssResponse.toIssLocationInfo(): IssLocationInfo {
    return IssLocationInfo(
        latitude = issPosition?.latitude?.toDoubleOrNull() ?: 0.0,
        longitude = issPosition?.longitude?.toDoubleOrNull() ?: 0.0,
        timeStamp = timestamp ?: 0L
    )
}
