package com.dentons.raidapp.presentation.lawyers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentons.raidapp.R
import com.dentons.raidapp.presentation.composables.CountryListView
import com.dentons.raidapp.presentation.composables.CountryPickerView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LawyersScreen() {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.grey))
        ) {
            Text(
                text = stringResource(R.string.nav_tab_lawyers),
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_purple),
                textAlign = TextAlign.Start,
                fontSize = 28.sp,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
            )

            CountryPickerView {
                coroutineScope.launch {
                    modalBottomSheetState.show()
                }
            }
        }

        CountryListView(onSelectItem = {
            Log.d("test", "Country selected - $it")
        }, modalBottomSheetState)
    }
}

@Preview(showBackground = true)
@Composable
fun LawyersScreenPreview() {
    LawyersScreen()
}