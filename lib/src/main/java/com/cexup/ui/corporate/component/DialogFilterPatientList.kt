package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.noRippleClick

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogFilterPatientList(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onDismiss:()->Unit,
    onApplyClick : () -> Unit ={},
    onClearAll : () -> Unit = {}
){

    if(show) {
        Dialog(onDismissRequest ={ onDismiss()},  properties = DialogProperties(usePlatformDefaultWidth = false)) {
            ScreenDialog(
                onApplyClick = {
                    onApplyClick()
                },
                onClearAll = {
                    onClearAll()
                },
                onDismiss = {
                    onDismiss()
                }
            )
        }
    }

}
@Composable
fun ScreenDialog(
    modifier: Modifier = Modifier,
    onApplyClick : () -> Unit ={},
    onClearAll : () -> Unit = {},
    onDismiss: () -> Unit ={}
){
    var patientIdState by remember { mutableStateOf(false) }
    var patientNameState by remember { mutableStateOf(false) }
    var emailState by remember { mutableStateOf(false) }
    var statusState by remember { mutableStateOf(false) }
    var diseasesState by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White, RoundedCornerShape(topStart = 10.dp))
                .width(183.dp)
                .padding(horizontal = 15.dp, vertical = 19.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Filters",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = inactive
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_cancel_filters),
                    contentDescription = "cancel",
                    modifier = modifier.size(14.52.dp).clickable {
                        onDismiss()
                    }
                )

            }

            Text(
                text = "Keywords",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
            SearchBar(
                hint = "Search..."
            )

            Text(
                text = "Show places by",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = inactive
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            CheckBoxForm(title = "Patient ID", stateCondition = patientIdState)
            Spacer(modifier = Modifier.height(22.dp))
            CheckBoxForm(title = "Patient Name", stateCondition = patientNameState)
            Spacer(modifier = Modifier.height(22.dp))
            CheckBoxForm(title = "Diseases", stateCondition = diseasesState)
            Spacer(modifier = Modifier.height(22.dp))
            CheckBoxForm(title = "Status", stateCondition = statusState)



            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick ={
                    onApplyClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(41.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Heading),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(5.dp)
            ) {

                Text(
                    text = "Apply Filters",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight(300),
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                    modifier = modifier.padding(5.dp))

            }
            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "Clear all",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = inactive
                ),
                modifier = modifier.clickable {
                    onClearAll()
                }
            )

        }
    }
}


@Composable
fun CheckBoxForm(
    modifier: Modifier = Modifier,
    stateCondition : Boolean = false,
    title : String,
){
    var state by remember { mutableStateOf(stateCondition) }
    Row(
        modifier= modifier
            .fillMaxWidth()
            .noRippleClick {

            }
            .height(23.dp)
            .padding(start = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = {
                state = it
            },
            modifier = modifier
                .size(12.dp)
                .clip(shape = RoundedCornerShape(6.dp)),
            colors = CheckboxDefaults.colors(
                checkedColor = Heading,
                uncheckedColor = Heading
            )
        )
        Spacer(modifier = Modifier.width(13.dp))
        Text(
            text = title,
            modifier = modifier
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.body1.copy(
                fontSize = 16.sp,
                color = Heading,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(400),
            ),
            softWrap = true,
        )
    }
}