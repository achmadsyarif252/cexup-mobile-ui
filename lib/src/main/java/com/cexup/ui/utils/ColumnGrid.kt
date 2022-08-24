package com.cexup.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.gridItems(
    count:Int,
    columnCount:Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal= Arrangement.Start,
    itemContent:@Composable BoxScope.(Int)->Unit

){
    gridItems(
        data = List(count){it},
        columnCount=columnCount,
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        itemContent = itemContent,
    )
}

@Composable
fun <T> ColumnScope.gridItems(
    data:List<T>,
    columnCount:Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: Modifier = Modifier,
    itemContent:@Composable BoxScope.(T)->Unit
){
    val rows = if(data.count() ==0) 0 else 1+(data.count() - 1) / columnCount
    for(i in 0 until rows){
        Row(horizontalArrangement = horizontalArrangement, modifier = modifier) {
            for(columnIndex in 0 until columnCount){
                val itemIndex = i*columnCount+columnIndex
                if(itemIndex < data.count()){
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent.invoke(this,data[itemIndex])
                    }
                }else{
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}