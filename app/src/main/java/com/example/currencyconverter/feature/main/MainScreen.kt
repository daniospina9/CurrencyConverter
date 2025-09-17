package com.example.currencyconverter.feature.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.currencyconverter.R
import com.example.currencyconverter.feature.main.composable.NavigationBarContent
import com.example.currencyconverter.navigation.history.HistoryRoute
import com.example.currencyconverter.ui.theme.BoxesBorderColor
import com.example.currencyconverter.ui.theme.ConversionTextColor
import com.example.currencyconverter.ui.theme.ConvertButtonColor
import com.example.currencyconverter.ui.theme.InnerBoxesColor

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    val currencies by viewModel.currencies.collectAsStateWithLifecycle()
    val mainComponentsState by viewModel.mainComponentsState.collectAsStateWithLifecycle()
    val lastConversionState by viewModel.lastConversionState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.events.collect { event ->
            when(event) {
                is MainViewModel.Event.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is MainViewModel.Event.NavigateToHistory -> {
                    navController.navigate(
                        HistoryRoute
                    )
                }
            }
        }
    }

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
                text = stringResource(R.string.currency_converter),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.from)
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
                    .height(50.dp)
                    .clickable {
                        viewModel.setFromCurrenciesExpanded(true)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = mainComponentsState.fromCurrencySelected
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.selector),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(InnerBoxesColor)
                    )
                }
                DropdownMenu(
                    modifier = Modifier.sizeIn(maxHeight = 200.dp),
                    expanded = mainComponentsState.fromCurrenciesExpanded,
                    onDismissRequest = {
                        viewModel.setFromCurrenciesExpanded(false)
                    },
                    offset = DpOffset(80.dp, 0.dp)
                ) {
                    currencies.forEach { currency ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "${currency.currencySymbol} - ${currency.currencyName}"
                                )
                            },
                            onClick = {
                                viewModel.setFromCurrencySelected(
                                    "${currency.currencySymbol} - ${currency.currencyName}"
                                )
                                viewModel.setFromCurrencySymbolAndName(
                                    fromCurrencySymbol = currency.currencySymbol,
                                    fromCurrencyName = currency.currencyName
                                )
                                viewModel.setFromCurrenciesExpanded(false)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.to)
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
                    .height(50.dp)
                    .clickable {
                        viewModel.setToCurrenciesExpanded(true)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = mainComponentsState.toCurrencySelected
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.selector),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(InnerBoxesColor)
                    )
                }
                DropdownMenu(
                    modifier = Modifier.sizeIn(maxHeight = 200.dp),
                    expanded = mainComponentsState.toCurrenciesExpanded,
                    onDismissRequest = {
                        viewModel.setToCurrenciesExpanded(false)
                    },
                    offset = DpOffset(80.dp, 0.dp)
                ) {
                    currencies.forEach { currency ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "${currency.currencySymbol} - ${currency.currencyName}"
                                )
                            },
                            onClick = {
                                viewModel.setToCurrencySelected(
                                    "${currency.currencySymbol} - ${currency.currencyName}"
                                )
                                viewModel.setToCurrencySymbolAndName(
                                    toCurrencySymbol = currency.currencySymbol,
                                    toCurrencyName = currency.currencyName
                                )
                                viewModel.setToCurrenciesExpanded(false)
                            }
                        )
                    }
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
                        text = stringResource(R.string.enter_amount),
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
                onClick = {
                    viewModel.makeConversion()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ConvertButtonColor
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.convert),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${lastConversionState.amount} ${lastConversionState.fromCurrencyName}",
                color = ConversionTextColor
            )
            Text(
                text = "${lastConversionState.conversion} ${lastConversionState.toCurrencyName}",
                color = ConversionTextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}