package com.example.composecourseyt.ui.cardotherflow.billspayment.bettingandlottery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cardotherflow.cashdeposit.NextBtn

class CredentialsActivity : ComponentActivity() {
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
            ){
                CredentialsPage( onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun CredentialsPage(
    onBackClicked: () -> Unit
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        BettingAndLotteryHeader(onBackClicked)
        EnterCredentials()
        Spacer(modifier = Modifier.height(200.dp))
        NextBtn(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            ),
            onClickNext = {
                val intent = Intent(context, BettingReceiptActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun EnterCredentials(){
    var userId by remember { mutableStateOf("") }
    var amountNumber by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(41.5.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        val line = painterResource(id = R.drawable.line)
        Text(
            text = "Enter Credentials",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(260.dp)
                .height(17.dp)
                .padding(start = 0.dp)
        )

        Spacer(modifier = Modifier.height(4.5.dp))
        Image(
            painter = line,
            contentDescription = null,
            modifier = Modifier
                .border(
                    width = 3.87.dp,
                    color = Color(0xFF25495E),
                    shape = RoundedCornerShape(8.dp) // Rounded corners for the border
                )
                .padding(3.87.dp)
                .width(31.02.dp)
                .height(0.dp)
        )
        Spacer(modifier = Modifier.height(29.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            BasicTextField(
                value = userId,
                onValueChange = {
                    // Only allow numeric characters
                    val newText = it.filter { char -> char.isDigit() }

                    // Limit input to 11 characters
                    if (newText.length <= 11) {
                        userId = newText
                    }
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxSize()
            )

            if (userId.isEmpty()) {
                Text(
                    text = "Enter User I.D",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 1.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(29.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            BasicTextField(
                value = amountNumber,
                onValueChange = {
                    // Only allow numeric characters
                    val newText = it.filter { char -> char.isDigit() }

                    // Limit input to 11 characters
                    if (newText.length <= 11) {
                        amountNumber = newText
                    }
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxSize()
            )

            if (amountNumber.isEmpty()) {
                Text(
                    text = "Enter amount",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 1.dp)
                )
            }
        }
    }
}