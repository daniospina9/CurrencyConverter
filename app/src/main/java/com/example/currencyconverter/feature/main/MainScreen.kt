package com.example.currencyconverter.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.feature.main.composable.NavigationBarContent
import com.example.currencyconverter.ui.theme.BoxesBorderColor
import com.example.currencyconverter.ui.theme.ConvertButtonColor
import com.example.currencyconverter.ui.theme.InnerBoxesColor

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val currencies by viewModel.currencies.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            NavigationBarContent()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Currency Converter",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "from"
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = BoxesBorderColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.selector),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(InnerBoxesColor)
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "to"
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = BoxesBorderColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.selector),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(InnerBoxesColor)
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                modifier = Modifier.border(
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = BoxesBorderColor
                ),
                value = state.amount,
                onValueChange = { amount ->
                    viewModel.setAmount(amount)
                },
                placeholder = {
                    Text(
                        text = "Enter amount",
                        color = InnerBoxesColor
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = ConvertButtonColor
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Convert",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            LazyColumn(

            ) {
                items(
                    count = currencies.size
                ) {
                    Text(
                        text = currencies[it].currencySymbol
                    )
                }
            }
        }
    }
}