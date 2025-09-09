package com.example.currencyconverter.feature

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.ui.theme.BoxesBorderColor

@Composable
fun MainScreen(

) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(
                text = "Currency Converter"
            )
            Text(
                text = "to"
            )
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = BoxesBorderColor,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}