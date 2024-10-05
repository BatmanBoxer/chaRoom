package com.batman.charoom.features.features_authentication.domain.usecase

import com.batman.charoom.common.utils.Resource
import com.batman.charoom.features.features_authentication.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UseCaseLogIn @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Unit>> = flow {

        emit(Resource.Loading<Unit>())
        val result = repository.login(email, password)
        result.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure { e ->
            emit(Resource.Error<Unit>(e.localizedMessage ?: "Login failed"))
        }

    }.flowOn(Dispatchers.IO)
}

