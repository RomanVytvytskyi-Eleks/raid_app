package com.dentons.raidapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dentons.raidapp.ui.theme.RaidAppTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaidAppTheme {
                val viewModel = getViewModel<MainViewModel>()
                MainScreen(state = viewModel.viewState.value,
                    effectFlow = viewModel.effect,
                    onEventSent = { event -> viewModel.setEvent(event) })
            }
        }
    }
}

