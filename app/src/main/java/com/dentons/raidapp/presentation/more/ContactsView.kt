package com.dentons.raidapp.presentation.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
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
fun ContactsView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
    ) {
        Text(
            text = stringResource(R.string.btn_contacts),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_purple),
            textAlign = TextAlign.Start,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            //todo make email as link
            Text(
                text = stringResource(id = R.string.label_contact_info),
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsViewPreview() {
    ContactsView()
}