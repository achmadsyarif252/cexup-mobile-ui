package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@ExperimentalMaterialApi
@Composable
fun CardMedicalInspection(
    modifier: Modifier = Modifier,
    name:String,
    userCode:String,
    thumb:String,
    selectedState : Boolean = false,
    onClick : (String) -> Unit
){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.selectable(
            selected = selectedState,
            onClick = {
                onClick(userCode)
            }
        )
    ) {
        Row(
            modifier = modifier
                .width(219.dp)
                .height(74.dp)
                .background(
                    color = if (selectedState) Heading else Color.White
                )
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = if (selectedState) Heading else inactive
                )
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            CoilImage(
                modifier = modifier
                    .clip(CircleShape)
                    .coloredShadow(MaterialTheme.colors.primary)
                    .width(48.dp)
                    .height(48.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile),
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 250),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
            )
            Spacer(modifier = modifier.width(15.dp))
            Column {
                Column {
                    Text(
                        text = name.capitalizeWords(),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(500),
                        color = if (selectedState) Color.White else Heading
                    )
                    Text(
                        text = "ID $userCode",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(300),
                            color = if (selectedState) Color.White else Heading
                        ),

                        )
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onClick(userCode) }) {
                    Image(
                        painter = if(selectedState) painterResource(
                            id = R.drawable.ic_right_arrow_selected
                        ) else painterResource(
                            id = R.drawable.ic_right_arrow_unselected
                        ),
                        contentDescription = "Arrow",
                        modifier = modifier.size(16.dp)
                    )
                }
            }

        }

    }
}