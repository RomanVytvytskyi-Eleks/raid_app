package com.dentons.raidapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
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

@Composable
fun RaidedView() {
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
        CountryPicker()
//        Text(
//            text = "location picker placeholder",
//            fontWeight = FontWeight.Light,
//            color = Color.Black,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .background(colorResource(id = R.color.red))
//                .align(Alignment.CenterHorizontally)
//                .padding(vertical = 15.dp)
//                .fillMaxWidth(0.9f),
//            fontSize = 20.sp
//        )
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


@Composable
fun CountryPicker() {
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .background(colorResource(id = R.color.white))
            .fillMaxWidth(1f)
            .padding(horizontal = 15.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(0.85f)) {
            Text(
                text = "Ukraine",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = stringResource(R.string.label_based_on_your_selection),
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
        Box(
            modifier = Modifier.align(CenterVertically)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RaidedViewPreview() {
    RaidedView()
}