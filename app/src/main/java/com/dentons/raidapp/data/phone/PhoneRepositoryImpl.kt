package com.dentons.raidapp.data.phone

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhoneRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PhoneRepository {

    override suspend fun getPhoneNumber(): Result<String> {
        //todo implement proper phone number retrieving with api mock
        return runCatching {
            withContext(dispatcher) {
                return@withContext "+49302290880014"
            }
        }
    }
}