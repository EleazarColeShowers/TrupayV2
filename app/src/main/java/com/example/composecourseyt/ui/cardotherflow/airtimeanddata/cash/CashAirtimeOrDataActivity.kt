package com.example.composecourseyt.ui.cardotherflow.airtimeanddata.cash

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.airtimeflow.AirtimeAndDataHeader
import com.example.composecourseyt.ui.cardotherflow.airtimeanddata.card.AirtimeOrData
import com.example.composecourseyt.ui.airtimeflow.airtimecashh.CashAirtimeNetworkProviderActivity
import com.example.composecourseyt.ui.dataflow.datacash.CashDataNetworkProviderActivity

class CashAirtimeOrDataActivity : ComponentActivity() {
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
                SelectCashAirtimeOrDataPage(amount, onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun SelectCashAirtimeOrDataPage(
    amount: String?,
    onBackClicked: () -> Unit
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AirtimeAndDataHeader(onBackClicked)
        Spacer(modifier = Modifier.height(38.dp))
        AirtimeOrData(
            onClickAirtime =  {
                val intent = Intent(context, CashAirtimeNetworkProviderActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onClickData = {
                val intent= Intent(context, CashDataNetworkProviderActivity:: class.java)
                context.startActivity(intent)
            }
        )
    }
}