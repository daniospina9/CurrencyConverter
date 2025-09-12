package com.example.currencyconverter.feature.main.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.domain.navigationbar.models.NavItem
import com.example.currencyconverter.ui.theme.InnerBoxesColor

@Composable
fun RowScope.CurrencyItems(navItem: NavItem, isSelected: Boolean, onItemClick:() -> Unit) {
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            onItemClick()
        },
        icon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(navItem.icon),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = navItem.name
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Black,
            selectedTextColor = Color.Black,
            indicatorColor = Color.White,
            unselectedIconColor = InnerBoxesColor,
            unselectedTextColor = InnerBoxesColor,
            disabledIconColor = InnerBoxesColor,
            disabledTextColor = InnerBoxesColor
        )
    )
}