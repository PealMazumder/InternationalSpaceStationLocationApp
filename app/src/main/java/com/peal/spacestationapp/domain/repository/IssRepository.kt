package com.peal.spacestationapp.domain.repository

import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.domain.model.IssLocationInfo
import com.peal.spacestationapp.core.domain.util.Result


/**
 * Created by Peal Mazumder on 30/1/25.
 */

interface IssRepository {
    suspend fun getIssLocation(): Result<IssLocationInfo, NetworkError>
}