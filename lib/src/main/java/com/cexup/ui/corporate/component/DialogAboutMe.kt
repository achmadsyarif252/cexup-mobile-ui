package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.fontsCorp

@Composable
fun DialogAboutMe(
    show:Boolean= false,
    onDismiss : ()-> Unit
) {
    var text by remember { mutableStateOf("""
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Congue blandit aenean adipiscing varius et tristique. Vitae aliquam, vulputate id mattis libero venenatis ac consectetur aliquet. Risus sed velit ullamcorper quisque
    """.trimIndent())}

    if(show){
        Dialog(onDismissRequest = { onDismiss()}) {
            LayoutDialogAboutMe(texAboutMe = text)
        }
    }
}

@Composable
fun LayoutDialogAboutMe(
    texAboutMe : String,
){
    Column {
        Box(
            modifier = Modifier
                .height(265.dp)
                .width(459.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .background(Color.White)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 23.dp)

            ) {
                Text(
                    text = "Edit About Me",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        color = Heading
                    ),
                )
                Spacer(modifier = Modifier.height(9.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 42.dp, end = 42.dp)
                        .wrapContentHeight()


                ) {
                    OutlinedTextField(
                        value = texAboutMe,
                        onValueChange = {

                        },
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = fontsCorp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Justify
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(),
                        modifier = Modifier.height(111.dp),
                        shape = RoundedCornerShape(10.dp)



                    )


                }
                Spacer(modifier = Modifier.height(13.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(BlueJade),
                        modifier = Modifier
                            .height(36.dp)
                            .width(288.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 11.dp)


                    ) {
                        Text(
                            text = "Save Changes",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),

                            )
                    }

                }


            }

        }

    }

}