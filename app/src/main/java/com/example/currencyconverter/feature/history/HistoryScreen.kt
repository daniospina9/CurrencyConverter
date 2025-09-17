package com.example.currencyconverter.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.currencyconverter.R
import com.example.currencyconverter.feature.history.composable.NavigationBarContent2
import com.example.currencyconverter.ui.theme.ConversionTextColor
import com.example.currencyconverter.ui.theme.ConvertButtonColor
import com.example.currencyconverter.ui.utils.formatDate

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val conversionsList by viewModel.conversionsList.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.events.collect { event ->
            when (event) {
                is HistoryViewModel.Event.NavigateToMain -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBarContent2()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            items(
                count = conversionsList.size
            ) {
                Text(
                    text = stringResource(R.string.date, formatDate(conversionsList[it].date))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp)
                ) {
                    Text(
                        text = "${conversionsList[it].amount} ${conversionsList[it].fromCurrencyName} = ",
                        color = ConversionTextColor
                    )
                    Text(
                        text = "${conversionsList[it].conversion} ${conversionsList[it].toCurrencyName}",
                        color = ConversionTextColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    viewModel.cleanHistory()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ConvertButtonColor
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.clean_history),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}