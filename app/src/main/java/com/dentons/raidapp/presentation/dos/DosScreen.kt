package com.dentons.raidapp.presentation.dos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentons.raidapp.R

@Composable
fun DosScreen() {
    val dosItems = stringArrayResource(id = R.array.dos)
    val dontsItems = stringArrayResource(id = R.array.donts)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.nav_tab_do_donts),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_purple),
            textAlign = TextAlign.Start,
            fontSize = 28.sp,
        )
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            TextButton(
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .height(60.dp)
                    .padding(end = 20.dp)
                    .fillMaxWidth(0.5f),
                onClick = {}
            ) {
                Text(
                    stringResource(id = R.string.btn_do),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            TextButton(
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                onClick = {}
            ) {
                Text(
                    stringResource(id = R.string.btn_dont),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .height(600.dp)
                .width(200.dp)
                .fillMaxSize()
        ) {
            items(dosItems) { item ->
                ListItem(item = item)
            }
        }
    }
}

@Composable
fun ListItem(item: String) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DosScreenPreview() {
    DosScreen()
}