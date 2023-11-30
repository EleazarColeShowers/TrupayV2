package com.example.composecourseyt.ui.cardotherflow.billspayment.bettingandlottery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composecourseyt.ui.cashflow.CustomerFooter
import com.example.composecourseyt.ui.cashflow.MerchantInfo
import com.example.composecourseyt.ui.otherflows.MainActivity
import com.example.composecourseyt.ui.otherflows.MerchantOtherHeader
import com.example.composecourseyt.ui.otherflows.MerchantStaticButtons

class BettingMerchantReceipt : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {

            Box {
                val context = LocalContext.current
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
//                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    item {
                        BettingMerchantReceiptPage(amount)
                    }
                }
                // CustomerFooter displayed at the bottom
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.BottomCenter),
                ) {
                    MerchantStaticButtons(

                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        ),
                        onClickClose = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        },
                        onClickNext = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun BettingMerchantReceiptPage(
    phoneNumber: String?
){
    val context = LocalContext.current

    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        MerchantOtherHeader()
        MerchantInfo()
        BettingReceiptContent()
        CustomerFooter()

    }
}

