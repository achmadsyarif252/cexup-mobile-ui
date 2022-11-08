package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@Composable
fun QuestionnaireShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .width(20.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
            Spacer(modifier = modifier.width(10.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
        }
        Spacer(modifier = modifier.height(20.dp))
        for (i in 0..3) {
            Card(modifier = modifier.fillMaxWidth()) {
                Column(modifier = modifier.padding(10.dp)) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                            )
                    ) {

                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                        Spacer(modifier = modifier.width(10.dp))
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                    }
                    Spacer(modifier = modifier.height(5.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                        Spacer(modifier = modifier.width(10.dp))
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(10.dp))
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
            Spacer(modifier = modifier.height(10.dp))
            Column(
                modifier = modifier
                    .width(50.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
        }
    }
}
