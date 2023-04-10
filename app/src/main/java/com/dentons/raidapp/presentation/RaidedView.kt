package com.dentons.raidapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentons.raidapp.R
import com.dentons.raidapp.presentation.composables.CountryPickerView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RaidedView(modalBottomSheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.background(colorResource(id = R.color.grey))
    ) {
        Image(
            painter = painterResource(R.drawable.img_dentons_logo),
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp)
                .padding(horizontal = 40.dp),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.label_are_you_being_raided),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 60.sp,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        Text(
            text = stringResource(R.string.label_select_location_and_call),
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 40.dp),
        )

        CountryPickerView {
            coroutineScope.launch {
                modalBottomSheetState.show()
            }
        }

        Text(
            text = stringResource(R.string.label_all_calls_routed),
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 40.dp),
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun RaidedViewPreview() {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )
    RaidedView(modalBottomSheetState)
}