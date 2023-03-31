package com.dentons.raidapp.presentation

import androidx.lifecycle.viewModelScope
import com.dentons.raidapp.data.phone.PhoneRepository
import com.dentons.raidapp.presentation.base.BaseViewModel
import com.dentons.raidapp.presentation.base.ViewEffect
import com.dentons.raidapp.presentation.base.ViewEvent
import com.dentons.raidapp.presentation.base.ViewState
import kotlinx.coroutines.launch

class MainViewModel(
    private val phoneRepository: PhoneRepository
) : BaseViewModel<MainViewModel.Event, MainViewModel.State, MainViewModel.Effect>() {

    init {
        getPhoneNumber()
    }

    private fun getPhoneNumber() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }

            phoneRepository.getPhoneNumber()
                .onSuccess {
                    setState { copy(phoneNumber = phoneNumber, isLoading = false) }
                    setEffect { Effect.PhoneWasLoaded }
                }.onFailure {
                    //todo add error handling
                }
        }
    }

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.MakeCall -> {
                setEffect { Effect.DialNumber }
            }
            is Event.OnAlertClicked -> {
                setEffect { Effect.ShowCallAlert }
            }
        }
    }

    override fun setInitialState() = State(
        phoneNumber = "",
        isLoading = true,
        isError = false,
        isAlertBottomSheetShown = false
    )

    sealed class Event : ViewEvent {
        object MakeCall : Event()
        object OnAlertClicked : Event()
    }

    data class State(
        val isAlertBottomSheetShown: Boolean,
        val phoneNumber: String = "+380737288413",
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewEffect {
        object DialNumber : Effect()
        object PhoneWasLoaded : Effect()
        object ShowCallAlert : Effect()
    }
}

