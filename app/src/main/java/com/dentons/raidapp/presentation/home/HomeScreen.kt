package com.dentons.raidapp.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dentons.raidapp.R
import com.dentons.raidapp.presentation.RaidedView
import com.dentons.raidapp.presentation.composables.CountryListView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
    
    ) {
        Column {
            Spacer(modifier = Modifier.padding(top = 40.dp))
            RaidedView(modalBottomSheetState)
        }
        CountryListView(onSelectItem = {
            Log.d("test", "Country selected - $it")
        }, modalBottomSheetState)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}