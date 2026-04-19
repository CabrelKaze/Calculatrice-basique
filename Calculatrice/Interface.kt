package com.example.calculatrice

import android.view.Display
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatrice.ui.theme.CalculatriceTheme

@Composable
fun Calcule(modifier: Modifier = Modifier){

        var operator by rememberSaveable { mutableStateOf<Char?>(null) }
        var display by rememberSaveable { mutableStateOf<String>("0") }
        var operand by remember { mutableStateOf<Int?>(null) }

        fun onDigit(C: Char) {
            if (display == "0")
                display = C.toString()
            else
                if (operator == null)
                    display += C.toString()
                else {
                    if (operand == null) {
                        operand = display.toInt()
                        display = C.toString()
                    } else
                        display += C.toString()
                }
        }


        fun onOperator(opt: Char) {
            operator = opt
        }

        fun onEquals() {
            var results = 0.0
            when (operator) {
                '+' -> {
                    results = operand!!.toDouble() + display.toDouble()
                }

                '-' -> {
                    results = operand!!.toDouble() - display.toDouble()
                }

                '*' -> {
                    results = operand!!.toDouble() * display.toDouble()
                }

                '/' -> {
                    results = operand!!.toDouble() / display.toDouble()
                }
            }
            display = results.toString()
            operator = null
            operand = null
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Box(
                modifier = Modifier
//                .weight(1f)
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        Color(0xC6FF9800),
                        RoundedCornerShape(16.dp)
                    ),
                //l'alignment a l'interieur au bout
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = display,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(15.dp)
                )

            }
            Spacer(modifier = Modifier.height(5.dp))

            val btnModifier = Modifier
                .padding(5.dp)
                .weight(1f)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                OutlinedButton(
                    onClick = {},
                    modifier = btnModifier
                ) {
                    Text("C")
                }
                OutlinedButton(
                    onClick = {},
                    modifier = btnModifier
                ) {
                    Text("DEL")
                }
                OutlinedButton(
                    onClick = {},
                    modifier = btnModifier
                ) {
                    Text("+/-")
                }
                OutlinedButton(
                    onClick = {},
                    modifier = btnModifier
                ) {
                    Text("./.")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (number in listOf<Char>('7', '8', '9'))

                    Button(
                        onClick = { onDigit(number) },
                        modifier = btnModifier
                    ) {
                        Text("$number")
                    }
                OutlinedButton(
                    onClick = { onOperator('*') },
                    modifier = btnModifier
                ) {
                    Text("*")
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {

                for (number in listOf<Char>('4', '5', '6'))

                    Button(
                        onClick = { onDigit(number) },
                        modifier = btnModifier
                    ) {
                        Text("$number")
                    }
                OutlinedButton(
                    onClick = { onOperator('-') },
                    modifier = btnModifier
                ) {
                    Text("-")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {

                for (number in listOf<Char>('1', '2', '3'))

                    Button(
                        onClick = { onDigit(number) },
                        modifier = btnModifier
                    ) {
                        Text("$number")
                    }
                OutlinedButton(
                    onClick = { onOperator('+') },
                    modifier = btnModifier
                ) {
                    Text("+")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(2f)
                ) {
                    Text("0")
                }
                OutlinedButton(
                    onClick = { onDigit('0') },
                    modifier = btnModifier
                ) {
                    Text(".")
                }
                OutlinedButton(
                    onClick = { onEquals() },
                    modifier = btnModifier
                ) {
                    Text("=")
                }
            }

        }
    }

@Preview(showBackground = true)
@Composable
fun Calculepreview(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Calcule()
    }
}