package com.dentons.raidapp.di

import com.dentons.raidapp.data.lawyers.LawyersRepository
import com.dentons.raidapp.data.lawyers.LawyersRepositoryImpl
import com.dentons.raidapp.data.phone.PhoneRepository
import com.dentons.raidapp.data.phone.PhoneRepositoryImpl
import com.dentons.raidapp.presentation.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}
val repositoryModule = module {
    factory<PhoneRepository> { PhoneRepositoryImpl() }
    factory<LawyersRepository> { LawyersRepositoryImpl() }
}

val appModules = listOf(
    viewModelModule,
    repositoryModule
)