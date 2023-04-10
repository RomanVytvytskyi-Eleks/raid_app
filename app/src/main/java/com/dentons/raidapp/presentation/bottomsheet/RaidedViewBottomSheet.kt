package com.dentons.raidapp.presentation.bottomsheet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dentons.raidapp.R
import com.dentons.raidapp.presentation.MainViewModel
import com.dentons.raidapp.presentation.RaidedView
import com.dentons.raidapp.presentation.composables.CountryListView
import com.dentons.raidapp.ui.theme.RED
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RaidedViewBottomSheet(
    raidedViewSheetState: ModalBottomSheetState,
    onEventSent: (event: MainViewModel.Event) -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
    ) {
        ModalBottomSheetLayout(
            sheetState = raidedViewSheetState,
            modifier = Modifier.fillMaxSize(),
            sheetContent = { AlertCallContent(raidedViewSheetState, onEventSent) }) {
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlertCallContent(
    sheetState: ModalBottomSheetState,
    onEventSent: (event: MainViewModel.Event) -> Unit
) {
    val raidedViewSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    val countriesViewSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.grey))
            .fillMaxSize()
    ) {
        val coroutineScope = rememberCoroutineScope()
        RaidedView(countriesViewSheetState)
        TextButton(
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .height(100.dp)
                .align(CenterHorizontally)
                .padding(horizontal = 40.dp, vertical = 15.dp)
                .fillMaxWidth(),
            onClick = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            }

        ) {
            Text(stringResource(id = R.string.btn_cancel), color = Color.Gray)
        }
        Button(
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(RED),
            modifier = Modifier
                .height(100.dp)
                .align(CenterHorizontally)
                .padding(horizontal = 40.dp, vertical = 10.dp)
                .fillMaxWidth(),
            onClick = {
                onEventSent(MainViewModel.Event.MakeCall)
                coroutineScope.launch {
                    sheetState.hide()
                }
            }

        ) {
            Text(stringResource(id = R.string.btn_call_dentons), color = Color.White)
        }
    }

    CountryListView(onSelectItem = {
        Log.d("test", "Country selected - $it")
    }, countriesViewSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun RaidedViewBottomSheetPreview() {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )
    RaidedViewBottomSheet(modalBottomSheetState,{})
}