package com.example.composecourseyt.ui.otherflows

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.cashflow.CustomerFooter
import com.example.composecourseyt.ui.cashflow.MerchantInfo

class MerchantOtherReceipt: ComponentActivity() {
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
                            MerchantOtherReceiptPage(amount)
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
fun MerchantOtherReceiptPage(
    amount: String?,
){
    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        MerchantOtherHeader()
        MerchantInfo()
        PageOtherContent(amount)
        CustomerFooter()
    }
}

@Composable
fun MerchantOtherHeader(){
    val logo= painterResource(id = R.drawable.cyberpay_logo)

    Column()
    {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 11.dp, start = 120.dp)
                .size(140.dp)
        )
        Text(
            text = "Merchant's Copy",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color= Color.Black,
            modifier= Modifier
                .padding(start = 145.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "CYBER PAYMENT",
            fontSize = 16.sp,
            fontWeight = FontWeight(800),
            modifier = Modifier
                .padding(start = 135.dp)
        )
        Text(
            text = "Victoria Island, LA-LANG",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF202020),
            textAlign = TextAlign.Center,
            modifier= Modifier
                .padding(start = 125.dp)
        )
    }
}

@Composable
fun MerchantStaticButtons(gradient: Brush,onClickClose: () -> Unit, onClickNext: () -> Unit){
    Column() {
        Column(
            modifier = Modifier

                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x08000000),
                    ambientColor = Color(0x08000000)
                )
                .width(327.dp)
                .height(80.dp)
                .padding(start = 45.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                .clickable {
                    onClickNext()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PRINT MERCHANT'S COPY",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(start = 40.dp, top = 12.dp)

            )
        }
        Text(
            text = "Close",
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .padding(start = 165.dp)
                .clickable {
                    onClickClose()
                }
        )
        Spacer(modifier = Modifier.height(73.dp))

    }
}

