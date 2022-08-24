package com.cexup.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItemMicroCamera(
    modifier : Modifier = Modifier,
    text : String,
    isSelected :Boolean = false,
    type : typeMenuMicroCamera,
){
    Row(
        modifier = modifier.height(33.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(14.62.dp))
        when(type){
            typeMenuMicroCamera.Images,typeMenuMicroCamera.Videos ->{
                Text(
                    text = text,
                    color = Color.White,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600)
                    ),
                    modifier = modifier.align(CenterVertically)
                )
            }
        }

    }
}

enum class typeMenuMicroCamera{
    Images,
    Videos,
}