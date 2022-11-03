package com.cexup.ui.consumer.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.component.ButtonTextPrimary
import com.cexup.ui.consumer.component.CardOrderStatus
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ScreenOrderFailed(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    toBrowser:()->Unit,
    toDetailOrder:()->Unit,
    message:String
){

    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            //AppBarDefaults(title = "Order", onBackPressed = {onBackPressed() })
        },
        bottomBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp.from(ctx)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonTextPrimary(
                    text = "Back to Browse",
                    onClick = {toBrowser()},
                    fullWidth = false
                )
                Spacer(modifier = modifier.width(8.dp))

                ButtonTextPrimary(
                    text = "Order Again",
                    onClick = {toDetailOrder()},
                    fullWidth = false
                )

            }
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding= PaddingValues(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
        ) {
            item {
                CardOrderStatus(
                    orderSuccess = false
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
                ) {
                    Text(
                        text = message.ifBlank { "Canceled due to overdue payment" },
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewScreeOrderFailed() {
    ConsumerTheme {

        ScreenOrderFailed(
            onBackPressed = { /*TODO*/ },
            toBrowser = { /*TODO*/ },
            toDetailOrder = { /*TODO*/ },
            message = ""
        )
    }
}