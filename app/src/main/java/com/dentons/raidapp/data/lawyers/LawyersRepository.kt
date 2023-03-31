package com.dentons.raidapp.data.lawyers

interface LawyersRepository {

    suspend fun geLawyers(): String
}