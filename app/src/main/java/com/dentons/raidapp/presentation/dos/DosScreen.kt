package com.dentons.raidapp.presentation.dos

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

    var dosVisible by remember { mutableStateOf(true) }

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
                    .animateContentSize()
                    .fillMaxWidth(if (dosVisible) 0.7f else 0.3f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {},
                onClick = { dosVisible = true }
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
                    .fillMaxWidth().animateContentSize()
                    .fillMaxWidth(if (!dosVisible) 0.7f else 0.3f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {},
                onClick = { dosVisible = false }
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
                .fillMaxSize()
                .padding(top = 10.dp)

        ) {
            if (dosVisible) {
                items(dosItems) { item ->
                    ListItem(item = item, Color.Green)
                }
            } else {
                items(dontsItems) { item ->
                    ListItem(item = item, Color.Red)
                }
            }
        }
    }
}
@Composable
fun TabButton(title: String, selected: Boolean, onClick: () -> Unit){
    TextButton(
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth().animateContentSize()
            .fillMaxWidth(if (selected) 0.7f else 0.3f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {},
        onClick = onClick
    ) {
        Text(
            text = title,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ListItem(item: String, color: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
                    .background(color)
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = item,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DosScreenPreview() {
    DosScreen()
}