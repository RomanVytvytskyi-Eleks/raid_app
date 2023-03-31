package com.dentons.raidapp.data.phone

interface PhoneRepository {

    suspend fun getPhoneNumber(): Result<String>
}