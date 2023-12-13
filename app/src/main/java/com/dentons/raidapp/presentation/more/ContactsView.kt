package com.dentons.raidapp.presentation.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.dentons.raidapp.R
import com.dentons.raidapp.ui.theme.PURPLE_MAIN
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactsView(modalBottomSheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
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
                            text = stringResource(R.string.btn_contacts),
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

                        val email = stringResource(id = R.string.label_contact_email)
                        val contactsInfoString =
                            String.format(stringResource(id = R.string.label_contact_info), email)

                        val emailStartIndex = contactsInfoString.indexOf(email)
                        val emailEndIndex = emailStartIndex + email.length

                        val annotatedString: AnnotatedString = buildAnnotatedString {
                            append(contactsInfoString)
                            addStyle(
                                SpanStyle(
                                    color = Color.DarkGray,
                                ), 0, contactsInfoString.length
                            )
                            addStyle(
                                SpanStyle(
                                    color = PURPLE_MAIN,
                                    textDecoration = TextDecoration.Underline
                                ), emailStartIndex, emailEndIndex
                            )
                            addStringAnnotation("email", email, emailStartIndex, emailEndIndex)
                        }

                        val context = LocalContext.current

                        ClickableText(
                            text = annotatedString,
                            modifier = Modifier.padding(40.dp),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic,
                                fontSize = 20.sp
                            ),
                            onClick = { position ->
                                annotatedString.getStringAnnotations("email", position, position)
                                    .firstOrNull()
                                    ?.let {
                                        sendEmail(context, it.item)
                                    }
                            }
                        )
                    }
                }
            },
        ) {}
    }
}

fun sendEmail(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    }

    startActivity(context, intent, null)
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun ContactsViewPreview() {
    val modalBottomSheetStateContacts =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Expanded,
            skipHalfExpanded = true
        )

    ContactsView(modalBottomSheetStateContacts)
}