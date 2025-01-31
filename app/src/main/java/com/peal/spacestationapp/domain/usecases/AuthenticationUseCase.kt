package com.peal.spacestationapp.domain.usecases

import com.peal.spacestationapp.domain.repository.AuthenticationRepository
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 31/1/25.
 */

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        idToken: String
    ): Result<Boolean> {
        return authenticationRepository.authenticate(idToken)
    }
}