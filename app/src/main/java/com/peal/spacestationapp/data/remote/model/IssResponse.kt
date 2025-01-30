package com.peal.spacestationapp.data.remote.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Peal Mazumder on 30/1/25.
 */

data class IssResponse(
    @SerializedName("iss_position")
    val issPosition: IssPosition? = null,
    val message: String? = null,
    val timestamp: Long? = null,
)

data class IssPosition(
    val latitude: String,
    val longitude: String
)