package com.example.composecourseyt.ui.cardotherflow.salescompletion

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.ui.cashflow.CustomerFooter
import com.example.composecourseyt.ui.cashflow.MerchantInfo
import com.example.composecourseyt.ui.cashflow.PopupScreen
import com.example.composecourseyt.ui.cashflow.StaticButtons
import com.example.composecourseyt.ui.otherflows.CustomerOtherHeader
import com.example.composecourseyt.ui.otherflows.MainActivity
import com.example.composecourseyt.ui.otherflows.MerchantOtherReceipt

@Suppress("DEPRECATION")
class SalesCompletionReceiptActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {
            var isPopupVisible by remember { mutableStateOf(true) }
            if (isPopupVisible) {
                PopupScreen(
                    amount = amount,
                    onDismiss = { isPopupVisible = false },
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    ),
                    onClickClose = {
                        val intent = Intent(this@SalesCompletionReceiptActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                )
            } else {
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
                            SalesCompletionReceiptPage(amount)
                        }
                    }
                    // CustomerFooter displayed at the bottom
                    Column(
                        modifier = Modifier
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
fun SalesCompletionReceiptPage(
    amount: String?,
    ){
    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        CustomerOtherHeader()
        MerchantInfo()
        SalesCompletionReceiptContent(amount)
        CustomerFooter()
    }
}

@Composable
fun SalesCompletionReceiptContent(amount: String?){
    val borderColor = Color(0x0D496779)
    val borderWidth = 1.dp

    val bottomBorderModifier = Modifier.drawWithContent {
        drawContent()
        drawRect(
            color = borderColor,
            topLeft = Offset(0f, size.height - borderWidth.toPx()),
            size = Size(size.width, borderWidth.toPx())
        )
    }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 23.86.dp)
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
        Spacer(modifier = Modifier.height(71.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Transaction Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "Sales Completion",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(71.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Name",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "John Doe",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(71.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "RNN",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "004145800636",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(71.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(bottomBorderModifier),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Amount",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "₦$amount.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(bottomBorderModifier),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Total",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "₦$amount.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }

    }
}