package com.peal.spacestationapp.domain.usecases

import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.core.domain.util.Result
import com.peal.spacestationapp.domain.model.IssLocationInfo
import com.peal.spacestationapp.domain.repository.IssRepository
import javax.inject.Inject


class GetIssCurrentLocationUseCase @Inject constructor(
    private val issRepository: IssRepository
) {
    suspend operator fun invoke(): Result<IssLocationInfo, NetworkError> {
        return issRepository.getIssLocation()
    }
}