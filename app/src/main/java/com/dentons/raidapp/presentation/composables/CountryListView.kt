package com.dentons.raidapp.presentation.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dentons.raidapp.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountryListView(
    onSelectItem: (String) -> Unit,
    modalBottomSheetState: ModalBottomSheetState
) {
    Box(
        Modifier.fillMaxWidth()
    ) {
        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            modifier = Modifier.fillMaxSize(),
            sheetContent = {
                CountriesListContent(onSelectItem, modalBottomSheetState)
            },
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        ) {}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountriesListContent(
    onSelectItem: (String) -> Unit,
    modalBottomSheetState: ModalBottomSheetState
) {
    val sheetTopPaddingDp = dimensionResource(id = R.dimen.padding_bottom_sheet_top)
    val sheetHeightDp = LocalConfiguration.current.screenHeightDp.dp - sheetTopPaddingDp

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .height(sheetHeightDp)
            .background(Color.White)
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_xlarge))
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.label_select_country),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp
                    )

                    val cancelText = buildAnnotatedString {
                        append(stringResource(id = R.string.btn_cancel))
                        addStyle(
                            SpanStyle(
                                color = colorResource(id = R.color.main_purple),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            ), 0, length
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    fun onClick() {
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }
                    }

                    ClickableText(
                        text = cancelText,
                        onClick = {
                            onClick()
                        },
                        modifier = Modifier.clickable { onClick() },
                        style = MaterialTheme.typography.body1.copy(color = colorResource(id = R.color.main_purple))
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_large))
                        .fillMaxSize()
                        .background(color = Color(0x4D00FF00))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        items(Country.values()) { item ->
                            CountryListItem(item = item, onSelectItem, modalBottomSheetState)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountryListItem(
    item: Country,
    onSelectItem: (String) -> Unit,
    modalBottomSheetState: ModalBottomSheetState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true) {
                onSelectItem(item.code)
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
    ) {
        Text(
            text = item.label,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.LightGray
                )
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun CountryListViewPreview() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Expanded,
            skipHalfExpanded = true
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBB86FC))
    ) {
        CountryListView({
            coroutineScope.launch {
                Toast.makeText(
                    context,
                    "Country code - $it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, modalBottomSheetState)
    }
}


enum class Country(val id: Int, val label: String, val code: String) {
    BELGIUM(1, "Belgium", "BE"),
    CZECH_REPUBLIC(2, "Czech Republic", "CZ"),
    FRANCE(3, "France", "FR"),
    GERMANY(4, "Germany", "DE"),
    HUNGARY(5, "Hungary", "HU"),
    ITALY(6, "Italy", "IT"),
    KAZAKHSTAN(7, "Kazakhstan", "KZ"),
    NETHERLANDS(8, "Netherlands", "NL"),
    POLAND(9, "Poland", "PL"),
    ROMANIA(10, "Romania", "RO"),
    SLOVAKIA(11, "Slovakia", "SK"),
    SPAIN(12, "Spain", "ES"),
    TURKEY(13, "Turkey", "TR"),
    UKRAINE(14, "Ukraine", "UA"),
    UNITED_KINGDOM(15, "United Kingdom", "UK");
}