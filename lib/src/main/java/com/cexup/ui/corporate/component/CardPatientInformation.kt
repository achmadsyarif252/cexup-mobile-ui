package com.cexup.ui.corporate.component

import android.graphics.Bitmap
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.SecondaryCorporate

data class Registration(
    var name: String,
    var no_type: String,
    var home_address: String,
    var phone_number: String,
    var place_of_birth: String,
    var date_of_birth: String,
    var gender: String,
)

@ExperimentalComposeUiApi
@Composable
fun CardPatientInformation(
    modifier: Modifier = Modifier,
    onSubmitRegisterPatient: (registration: Registration) -> Unit,
) {
    var patientName by remember { mutableStateOf("") }
    var nik by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var placeOfBirth by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var imageProfileBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    val radioOptions = listOf("Laki-laki", "Perempuan")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }


    fun onClickNext() {
        val data = Registration(
            name = patientName,
            no_type = nik,
            home_address = address,
            phone_number = phoneNumber,
            place_of_birth = placeOfBirth,
            date_of_birth = dateOfBirth,
            gender = selectedOption.lowercase()
        )
        onSubmitRegisterPatient(data)
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                modifier = modifier.width(210.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier.clickable {

                    }
                ) {
                    Box(
                        modifier = modifier
                            .width(96.dp)
                            .height(94.dp)
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
                                .size(96.dp)
                                .border(width = 1.dp, color = Heading, shape = CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Box(
                        modifier = modifier
                            .clip(CircleShape)
                            .size(24.dp)
                            .background(Heading)
                            .align(Alignment.BottomEnd)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit_pencil),
                            contentDescription = "pencil",
                            modifier = modifier.size(12.dp)
                        )
                    }

                }
            }


            FormTextField(
                placeholderText = "John Doe",
                nameTextField = "Full name",
                valueTextField = patientName,
                onValueChange = {
                    patientName = it
                }
            )

            FormTextField(
                nameTextField = "NIK",
                placeholderText = "16 digits",
                valueTextField = nik,
                onValueChange = {
                    nik = it
                }
            )


        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormTextField(
                nameTextField = "Address",
                placeholderText = "Jl. Bhakti",
                valueTextField = address,
                onValueChange = {
                    address = it
                }
            )

            FormTextField(
                nameTextField = "Place of Birth",
                placeholderText = "Bogor",
                valueTextField = placeOfBirth,
                onValueChange = {
                    placeOfBirth = it
                }
            )

            FormTextField(
                nameTextField = "Date of Birth",
                placeholderText = "yyyy-mm-dd (1993-09-27)",
                valueTextField = dateOfBirth,
                onValueChange = {
                    dateOfBirth = it
                }
            )


        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            FormTextField(
                nameTextField = "Phone Number",
                placeholderText = "081284591248",
                valueTextField = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                }
            )

            Column(modifier = Modifier) {
                Text(
                    text = "Gender",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row {
                    radioOptions.forEach { text ->
                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { selectedOption = text }
                                )
                                .padding(horizontal = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                modifier = Modifier.padding(vertical = 6.dp),
                                onClick = {
                                    selectedOption = text
                                }
                            )
                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    }
                }
            }



        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = modifier
                    .width(208.dp)
                    .height(94.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        keyboardController?.hide()
                    },
                    modifier = modifier
                        .width(100.dp)
                        .height(38.54.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 11.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = "cancel",
                            modifier = modifier.size(12.dp)
                        )
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 14.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(5.dp)
                        )
                    }


                }

                Button(
                    onClick = {
                        keyboardController?.hide()
                        onClickNext()
                    },
                    modifier = modifier
                        .width(100.dp)
                        .height(38.54.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Heading),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 11.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 14.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_right_arrow),
                            contentDescription = "cancel",
                            modifier = modifier.size(12.dp)
                        )
                    }


                }
            }
        }


    }

}