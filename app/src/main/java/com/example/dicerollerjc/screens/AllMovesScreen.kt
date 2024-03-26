package com.example.dicerollerjc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerjc.R
import com.example.dicerollerjc.model.MoveItem

@Composable
fun AllMoves(paddingValues: PaddingValues) {
    val moves = listOf(
        MoveItem(
            number = 1,
            image = painterResource(id = R.drawable.dice_1)
        ),
        MoveItem(
            number = 2,
            image = painterResource(id = R.drawable.dice_2)
        ),
        MoveItem(
            number = 3,
            image = painterResource(id = R.drawable.dice_3)
        ),
        MoveItem(
            number = 4,
            image = painterResource(id = R.drawable.dice_4)
        ),
        MoveItem(
            number = 5,
            image = painterResource(id = R.drawable.dice_5)
        ),
        MoveItem(
            number = 6,
            image = painterResource(id = R.drawable.dice_6)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .scrollable(orientation = Orientation.Vertical, state = rememberScrollState())
    ){
        Spacer(modifier = Modifier.height(8.dp))
        MovesListRow(moves = moves)
        Spacer(modifier = Modifier.height(8.dp))
        MovesListColumn(moves = moves)
    }
}

@Composable
fun MovesListRow(moves: List<MoveItem>) {
    LazyRow {
        items(
            count = moves.size,
            key = {
                moves[it].number
            }
        ){
            val moveItem = moves[it]
            MoveItemViewRow(moveItem = moveItem)
        }
    }
}

@Composable
fun MoveItemViewRow(moveItem: MoveItem) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = moveItem.image,
                contentDescription = moveItem.number.toString(),
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = moveItem.number.toString(),
                fontSize = 50.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MovesListColumn(moves: List<MoveItem>) {
    LazyColumn{
        items(
            count = moves.size,
            key = {
                moves[it].number
            }
        ){
            val moveItem = moves[it]
            MoveItemViewColumn(moveItem = moveItem)
        }
    }
}

@Composable
fun MoveItemViewColumn(moveItem: MoveItem) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = moveItem.image,
                contentDescription = moveItem.number.toString(),
                modifier = Modifier
                    .weight(0.7f)
            )
            Text(
                text = moveItem.number.toString(),
                fontSize = 95.sp,
                modifier = Modifier
                    .weight(0.3f),
                textAlign = TextAlign.Center
            )
        }
    }
}