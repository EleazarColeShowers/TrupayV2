package com.example.composecourseyt.ui.cardotherflow.micropayment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cardotherflow.micropayment.micropension.MicroPension

class MicroPayment : ComponentActivity() {
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
                verticalArrangement = Arrangement.Top
            ){
                val context= LocalContext.current

                MicroPaymentScreen(
                    onMicroPensionClick= {
                        val intent = Intent(context, MicroPension::class.java)
                        intent.putExtra("amount", amount)
                        context.startActivity(intent)
                    }
                )


            }
        }
    }
}

@Composable
fun MicroPaymentScreen(
    onMicroPensionClick: () ->Unit
) {

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "MICRO PAYMENT",
            fontSize = 25.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier= Modifier
                .padding(horizontal = 20.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .width(350.dp)
                .clickable {
                    onMicroPensionClick()
                           },
        ){
            Text(
                text = "Micro Pensions",
                fontSize = 20.sp,
                modifier= Modifier.padding(top= 14.dp, bottom = 14.dp, start= 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier= Modifier
                .padding(horizontal = 20.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .width(350.dp),
//            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Insurance",
                fontSize = 20.sp,
                modifier= Modifier.padding(top= 14.dp, bottom = 14.dp, start= 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier= Modifier
                .padding(horizontal = 20.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .width(350.dp),
//            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Savings",
                fontSize = 20.sp,
                modifier= Modifier.padding(top= 14.dp, bottom = 14.dp, start= 10.dp)
            )
        }
    }
}