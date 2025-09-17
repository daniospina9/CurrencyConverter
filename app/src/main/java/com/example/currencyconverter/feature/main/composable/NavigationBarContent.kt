package com.example.currencyconverter.feature.main.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.navigationbar.models.NavItem
import com.example.currencyconverter.feature.main.MainViewModel
import com.example.currencyconverter.ui.theme.BoxesBorderColor

@Composable
fun NavigationBarContent(
    viewModel: MainViewModel = hiltViewModel()
) {
    val selectedIndex by viewModel.navigationBarState.collectAsStateWithLifecycle()

    val itemList = listOf(
        NavItem(name = stringResource(R.string.converter), icon = R.drawable.currency_dollar),
        NavItem(name = stringResource(R.string.history), icon = R.drawable.history)
    )
    Column {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = BoxesBorderColor,
            thickness = 1.dp
        )
        NavigationBar(
            containerColor = Color.White
        ) {
            itemList.forEachIndexed { index, item ->
                CurrencyItems(navItem = item, isSelected = index == selectedIndex.selectedIndex, onItemClick = {
                    viewModel.navigateToHistory()
                })
            }

        }
    }
}
