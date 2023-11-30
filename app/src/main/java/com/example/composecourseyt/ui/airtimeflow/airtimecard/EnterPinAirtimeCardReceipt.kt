package com.example.composecourseyt.ui.airtimeflow.airtimecard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.composecourseyt.ui.otherflows.EnterBtn
import com.example.composecourseyt.ui.otherflows.EnterPinHeader
import com.example.composecourseyt.ui.otherflows.InputPin
import com.example.composecourseyt.ui.otherflows.InputPinNumber

class EnterPinAirtimeCardReceipt: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        val phoneNumber = intent.getStringExtra("Phone")

        setContent {

            EnterPinAirtimeCardPage(amount){
                onBackPressed()
            }
        }
    }
}

@Composable
fun EnterPinAirtimeCardPage(phoneNumber: String?, onBackClicked: () -> Unit){
//    val isSwappedNonCash = remember { mutableStateOf(true) }
    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            EnterPinHeader {
                onBackClicked()
            }
            InputPin()
            InputPinNumber(onClickNumber = { number ->
                clickedNumber.value += number
            },
                onDeleteNumber = {
                    if (clickedNumber.value.isNotEmpty()) {
                        clickedNumber.value = clickedNumber.value.dropLast(1)
                    }
                },
                onClearAll = {
                    clickedNumber.value = " "
                }
            )
            EnterBtn(
                gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                ),
                onClickNext = {
                    val intent = Intent(context, AirtimeCardReceipt::class.java)
                    intent.putExtra("Phone", phoneNumber)
                    context.startActivity(intent)
                }
            )
        }
    }
}
