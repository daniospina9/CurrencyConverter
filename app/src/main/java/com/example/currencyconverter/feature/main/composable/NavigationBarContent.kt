package com.example.currencyconverter.feature.main.composable

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.currencyconverter.R

@Composable
fun MyNavigationBar(

) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Image(
                    painter = painterResource(R.drawable.currency_dollar_svgrepo_com),
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Image(
                    painter = painterResource(R.drawable.currency_dollar_svgrepo_com),
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Image(
                    painter = painterResource(R.drawable.currency_dollar_svgrepo_com),
                    contentDescription = null
                )
            }
        )
    }
}