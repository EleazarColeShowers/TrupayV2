package com.example.composecourseyt.ui.airtimeflow.airtimecashh

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.airtimeflow.airtimecard.AirtimeHeader
import com.example.composecourseyt.ui.airtimeflow.airtimecard.ChooseNetwork
import com.example.composecourseyt.ui.cardotherflow.cashdeposit.NextBtn

class CashAirtimeNetworkProviderActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val amount = intent.getStringExtra("amount")

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("amount", amount)
        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CashAirtimeNetworkProviderPage(onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun CashAirtimeNetworkProviderPage(
    onBackClicked: () -> Unit,

    ){

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AirtimeHeader(onBackClicked)
        Spacer(modifier = Modifier.height(38.dp))
        ChooseNetwork()
        NextBtn(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            ),
            onClickNext = {
                val intent = Intent(context, CashAirtimeReceiptActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}