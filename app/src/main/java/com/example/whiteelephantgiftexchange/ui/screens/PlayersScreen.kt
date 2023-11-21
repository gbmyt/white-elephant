package com.example.whiteelephantgiftexchange.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whiteelephantgiftexchange.R
import com.example.whiteelephantgiftexchange.exampleData.DataSource

@Composable
fun PlayersList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
//        item {
//            Text(
//                text = "Players",
//                textAlign = TextAlign.Center,
//                fontSize = 24.sp,
//                color = Color.White,
//                modifier = modifier
//                    .fillMaxSize()
//                    .size(75.dp)
//                    .background(color = Color.DarkGray)
//                    .wrapContentHeight(Alignment.CenterVertically)
//            )
//        }

        item {
            Button(
                content = { Text(text = stringResource(R.string.import_contacts)) },
                onClick = { /*TODO*/ },
                modifier = modifier.padding(vertical = 16.dp)
            )
        }

        DataSource().players.forEach {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {

                    if (it.giftUploaded) {
                        Icon(
                            painter = painterResource(id = R.drawable.done_24px),
                            contentDescription = null,
                            modifier = modifier.padding(horizontal = 12.dp)
                        )
                    } else {
                        Checkbox(checked = false, onCheckedChange = { /* TODO */ })
                    }

                    Text(
                        text = "${it.name}",
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersListPreview() {
    PlayersList()
}
