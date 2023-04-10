package com.dentons.raidapp.presentation.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentons.raidapp.R

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
    ) {
        Text(
            text = stringResource(R.string.nav_tab_more),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_purple),
            textAlign = TextAlign.Start,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
        )
        TextButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .height(40.dp)
                .align(alignment = Alignment.Start)
                .fillMaxWidth(),
            onClick = { }
        ) {
            Text(
                stringResource(id = R.string.btn_contacts),
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                fontSize = 18.sp
            )
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        TextButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),

            onClick = { }
        ) {
            Text(
                stringResource(id = R.string.btn_terms),
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                fontSize = 18.sp
            )
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen()
}