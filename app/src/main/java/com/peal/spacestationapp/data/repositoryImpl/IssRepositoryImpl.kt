package com.peal.spacestationapp.data.repositoryImpl

import com.peal.spacestationapp.core.data.network.safeCall
import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.core.domain.util.map
import com.peal.spacestationapp.data.mapper.toIssLocationInfo
import com.peal.spacestationapp.data.remote.IssApiService
import com.peal.spacestationapp.data.remote.model.IssResponse
import com.peal.spacestationapp.domain.model.IssLocationInfo
import com.peal.spacestationapp.domain.repository.IssRepository
import com.peal.spacestationapp.core.domain.util.Result
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

class IssRepositoryImpl @Inject constructor(
    private val issApiService: IssApiService,
) : IssRepository {
    override suspend fun getIssLocation(): Result<IssLocationInfo, NetworkError> {
        return safeCall<IssResponse> {
            issApiService.getIssLocation()
        }.map { issResponse ->
            issResponse.toIssLocationInfo()
        }
    }

}