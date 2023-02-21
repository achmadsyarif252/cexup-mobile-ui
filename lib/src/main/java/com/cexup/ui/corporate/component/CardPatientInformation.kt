package com.cexup.ui.corporate.component

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.R
import com.cexup.ui.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

@ExperimentalComposeUiApi
@Composable
fun CardPatientInformation(
    modifier: Modifier = Modifier,
    onSubmitRegisterPatient: (
        name: String,
        no_type: String,
        home_address: String,
        phone_number: String,
        place_of_birth: String,
        date_of_birth: String,
        gender: String,
    ) -> Unit,
) {
    val ctx = LocalConfiguration.current
    var patientName by remember { mutableStateOf("") }
    var nik by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var placeOfBirth by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var imageProfileBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val radioOptions = listOf(stringResource(id = R.string.laki_laki), stringResource(id = R.string.perempuan))
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }


    fun onClickNext() {
        onSubmitRegisterPatient(
            patientName,
            nik,
            address,
            phoneNumber,
            placeOfBirth,
            dateOfBirth,
            selectedOption.lowercase()
        )
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(18.dp.from(ctx))
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier.width(208.dp.from(ctx)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier.clickable {

                    }
                ) {
                    Box(
                        modifier = modifier
                            .width(96.dp.from(ctx))
                            .height(94.dp.from(ctx))
                    ) {
                        Image(
                            rememberImagePainter(data = imageProfileBitmap, builder = {
                                crossfade(true)
                                placeholder(R.drawable.dummy_doctor)
                            }),
                            contentDescription = "",
                            modifier = modifier
                                .clip(CircleShape)
                                .coloredShadow(MaterialTheme.colors.primary)
                                .size(96.dp.from(ctx))
                                .border(
                                    width = 1.dp.from(ctx),
                                    color = Heading,
                                    shape = CircleShape
                                ),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Box(
                        modifier = modifier
                            .clip(CircleShape)
                            .size(24.dp.from(ctx))
                            .background(Heading)
                            .align(Alignment.BottomEnd)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit_pencil),
                            contentDescription = "pencil",
                            modifier = modifier.size(12.dp.from(ctx))
                        )
                    }

                }
            }
            FormTextField(
                placeholderText = "John Doe",
                nameTextField = stringResource(id = R.string.full_name),
                valueTextField = patientName,
                onValueChange = {
                    patientName = it
                },
                typeTextField = 2
            )
            FormTextField(
                nameTextField = stringResource(id = R.string.nik),
                placeholderText = "16 digits",
                valueTextField = nik,
                onValueChange = {
                    nik = it
                },
                keyboardType = KeyboardType.Number,
                typeTextField = 2,
                maxCharacter = 16,
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormTextField(
                nameTextField = stringResource(id = R.string.address),
                placeholderText = "Jl. Bhakti",
                valueTextField = address,
                onValueChange = {
                    address = it
                },
                typeTextField = 2
            )

            FormTextField(
                nameTextField = stringResource(id = R.string.place_of_birth),
                placeholderText = "Bogor",
                valueTextField = placeOfBirth,
                onValueChange = {
                    placeOfBirth = it
                },
                typeTextField = 2
            )

            FormTextField(
                nameTextField = stringResource(id = R.string.date_of_birth),
                placeholderText = "yyyy-mm-dd (1993-09-27)",
                valueTextField = dateOfBirth,
                onValueChange = {
                    Log.e("kambing bandot", it)
                    dateOfBirth = it
                },
                typeTextField = 1
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp.from(ctx)),
            horizontalArrangement = Arrangement.spacedBy(36.dp.from(ctx))
        ) {
            FormTextField(
                nameTextField = stringResource(id = R.string.phone_number),
                placeholderText = "081284591248",
                valueTextField = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                keyboardType = KeyboardType.Number,
                typeTextField = 2,
                maxCharacter = 13
            )

            Column(modifier = Modifier.width(180.dp.from(ctx))) {
                Text(
                    text = stringResource(id = R.string.gender),
                    fontSize = 16.sp.from(ctx),
                    fontWeight = FontWeight(400),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    modifier = Modifier.padding(horizontal = 16.dp.from(ctx))
                )
                radioOptions.forEachIndexed() {index, text ->
                    Row(
                        modifier = Modifier
                            .height(35.dp.from(ctx))
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { selectedOption = text }
                            ),
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                selectedOption = text
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier
                                .padding(start = 2.dp.from(ctx))
                                .align(CenterVertically)
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = modifier
                    .width(210.dp.from(ctx))
                    .height(94.dp.from(ctx)),
                horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        keyboardController?.hide()
                    },
                    modifier = modifier
                        .width(90.dp.from(ctx))
                        .height(38.54.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                    shape = RoundedCornerShape(10.dp.from(ctx)),
                    contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 14.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = Color.White
                            ),
                            modifier = modifier.padding(5.dp.from(ctx))
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = "Cancel",
                            modifier = modifier.size(12.dp.from(ctx))
                        )
                    }
                }

                Button(
                    onClick = {
                        keyboardController?.hide()
                        onClickNext()
                    },
                    enabled = nik.length >= 16
                            && dateOfBirth != ""
                            && placeOfBirth != ""
                            && address != ""
                            && patientName != "",
                    modifier = modifier
                        .width(90.dp.from(ctx))
                        .height(38.54.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Heading),
                    shape = RoundedCornerShape(10.dp.from(ctx)),
                    contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.next),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 14.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = Color.White,
                            ),
                            modifier = modifier.padding(5.dp.from(ctx))
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_right_arrow),
                            contentDescription = "Next",
                            modifier = modifier.size(12.dp.from(ctx))
                        )
                    }
                }
            }
        }
    }

}