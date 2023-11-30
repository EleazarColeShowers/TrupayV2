package com.example.composecourseyt.ui.airtimeflow.airtimecard

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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.cashflow.CustomerFooter
import com.example.composecourseyt.ui.cashflow.CustomerHeader
import com.example.composecourseyt.ui.cashflow.MerchantInfo
import com.example.composecourseyt.ui.cashflow.StaticButtons
import com.example.composecourseyt.ui.otherflows.MainActivity
import com.example.composecourseyt.ui.otherflows.MerchantOtherReceipt

class AirtimeCardReceipt: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneNumber = intent.getStringExtra("Phone")

        setContent {
            var isPopupVisible by remember { mutableStateOf(true) }

            if (isPopupVisible) {
                AirtimePopupScreen(
                    phoneNumber = phoneNumber,
                    onDismiss = { isPopupVisible = false },
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    ),
                    onClickClose ={
                        val intent = Intent(this@AirtimeCardReceipt, MainActivity::class.java)
                        startActivity(intent)
                    }
                )
            } else {
                Box(
//                        modifier = Modifier.fillMaxSize()
                ) {
                    val context= LocalContext.current
                    // Scrollable content using LazyColumn
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            AirtimeCardReceiptPage(phoneNumber)
                        }
                    }

                    // CustomerFooter displayed at the bottom
                    Column(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .align(Alignment.BottomCenter),
                    ) {
                        StaticButtons(

                            gradient = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                            ),
                            onClickClose = {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            },
                            onClickNext = {
                                val intent = Intent(context, MerchantOtherReceipt::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }

                }

            }
        }
    }
}

@Composable
fun AirtimePopupScreen(phoneNumber: String?, onDismiss: () -> Unit,gradient: Brush,onClickClose: () -> Unit) {
    // Define the fixed width and height for the pop-up
    val popupWidth = 328.dp
    val popupHeight = 454.dp
    val successfulTransaction= painterResource(id = R.drawable.transaction_successful)
    val printIcon= painterResource(id = R.drawable.print_icon)
    val context = LocalContext.current


    // Create a Dialog to display the pop-up content
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .size(popupWidth, popupHeight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = successfulTransaction,
                    contentDescription = null,
                    modifier = Modifier
                        .size(171.dp)
                )

                Text(
                    text = "Transaction Approved",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
                Spacer(modifier = Modifier.height(69.dp))
                Column(
                    modifier = Modifier
                        .background(gradient, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .width(250.dp)
                        .height(20.dp)
                        .clickable {
                            onDismiss()
                        }
                ) {
                    Row() {
                        Text(
                            text = "Print Receipt",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            modifier= Modifier
                                .padding(start = 70.dp)
                        )
                        Image(
                            painter = printIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(1.dp)

                        )
                    }
                }
                Text(
                    text = "Home",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .padding(start = 5.dp, top = 16.5.dp)
                        .clickable {
                            onClickClose()
                        }
                )
            }
        }
    }
}

@Composable
fun AirtimeCardReceiptPage(
phoneNumber: String?
){
    val context = LocalContext.current

    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        CustomerHeader()
        MerchantInfo()
        AirtimeCardReceiptContent(phoneNumber)
        CustomerFooter()

    }
}

@Composable
fun AirtimeCardReceiptContent(
    phoneNumber: String?
){
    val gloIcon = painterResource(id = R.drawable.gloicon)

    Column(
        modifier = Modifier
            .padding(top = 49.dp, start = 97.dp) // Adjust padding as needed

    ) {
        Text(
            text = "Transaction Approved",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(182.dp)
                .height(37.dp)
                .background(color = Color(0x0D26FF02), shape = RoundedCornerShape(size = 24.dp))
                .padding(start = 16.dp, top = 10.dp)
        )
    }
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Terminal ID",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "2037YU41",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Transaction Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "Airtime(Card)",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Phone Number",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "08123031547",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Network",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Row() {
                Text(
                    text = "Glo",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF202020),
                    textAlign = TextAlign.Right,
                )
                Image(painter = gloIcon, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Card Number",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "5199********8900",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Card Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "Debit Card",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Expiry Date",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "04/24",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Authorization Method",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "PIN",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Amount",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "₦30.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
    }
}