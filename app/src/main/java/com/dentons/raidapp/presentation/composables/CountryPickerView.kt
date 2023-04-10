package com.dentons.raidapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun CountryPickerView(onClick: () -> Unit) {

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(enabled = true, onClick = {
                onClick()
            }),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Ukraine",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(R.string.label_based_on_your_selection),
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                tint = colorResource(id = R.color.main_purple),
                contentDescription = "",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CountryPickerPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
            .padding(PaddingValues(20.dp))
    ) {
        CountryPickerView({})
    }
}