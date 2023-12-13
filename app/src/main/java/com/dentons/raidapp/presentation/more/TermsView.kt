package com.dentons.raidapp.presentation.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.dentons.raidapp.ui.theme.PURPLE_MAIN
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TermsView(modalBottomSheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote("https://api.printnode.com/static/test/pdf/multipage.pdf"),
        isZoomEnable = true
    )

    Box(
        Modifier.fillMaxWidth()
    ) {
        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            modifier = Modifier.fillMaxSize(),
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.grey))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    modalBottomSheetState.hide()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "close",
                                tint = PURPLE_MAIN,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            text = stringResource(R.string.btn_terms),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.main_purple),
                            textAlign = TextAlign.Start,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                        )
                    }

                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        VerticalPDFReader(
                            state = pdfState,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color. White)
                        )
                    }
                }
            },
        ) {}
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun TermsViewPreview() {
    val modalBottomSheetStateTerms =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Expanded,
            skipHalfExpanded = true
        )

    TermsView(modalBottomSheetStateTerms)
}