package com.example.composecourseyt.ui.dataflow.datacash

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
import com.example.composecourseyt.ui.transferflow.TransferApprovedPopUp

class DataCashReceipt : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneNumber = intent.getStringExtra("Phone")

        setContent{
            var isConfirmationPopupVisible by remember {
                mutableStateOf(true)
            }
            var isPopupVisible by remember { mutableStateOf(false) }

            if (isConfirmationPopupVisible) {
                ConfirmationPopUp(
                    onBackClicked = {onBackPressed()},
                    onDismiss = {
                        isConfirmationPopupVisible = false
                        isPopupVisible= true
                    },
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    ),
                )
            }
            else if (isPopupVisible) {
                TransferApprovedPopUp(
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    ),
                    onClickClose = {
                        val intent = Intent(this@DataCashReceipt, MainActivity::class.java)
                        startActivity(intent)
                    },
                    onDismiss = {
                        isPopupVisible = false
                    }
                )
            }
            else {
                Box(
                ) {
                    val context= LocalContext.current
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            DataCashReceiptPage(phoneNumber)
                        }
                    }
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
fun ConfirmationPopUp(onDismiss: () -> Unit,gradient: Brush,onBackClicked: () -> Unit){
    val popupWidth = 342.dp
    val popupHeight = 424.dp
    val successfulTransaction= painterResource(id = R.drawable.transaction_successful)
    val printIcon= painterResource(id = R.drawable.print_icon)
    val context = LocalContext.current
    val copy= painterResource(id = R.drawable.copybtn)
    val gloIcon= painterResource(id = R.drawable.gloicon)

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
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),
            ) {

                Text(
                    text = "Please confirm the payment below",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                )
                Spacer(modifier = Modifier.height(52.dp))
                Row (
                    modifier= Modifier
                        .width(257.dp)
                        .align(Alignment.CenterHorizontally)

                ){
                    Text(
                        text = "Phone Number",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0x78222222),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                    Spacer(modifier = Modifier.width(31.dp))
                    Text(
                        text = "08062819024",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Row (
                    modifier= Modifier
                        .width(257.dp)
                        .align(Alignment.CenterHorizontally)

                ){
                    Text(
                        text = "Network",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0x78222222),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                    Spacer(modifier = Modifier.width(80.dp))
                    Text(
                        text = "Glo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                    Image(painter = gloIcon, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(36.dp))
                Row (
                    modifier= Modifier
                        .width(257.dp)
                        .align(Alignment.CenterHorizontally)
//                    horizontalArrangement = Arrangement.SpaceBetween,

                ){
                    Text(
                        text = "Amount",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0x78222222),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                    Spacer(modifier = Modifier.width(86.dp))
                    Text(
                        text = "₦500.00",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                    )
                }
                Spacer(modifier = Modifier.height(55.dp))
                Column(
                    modifier = Modifier
                        .background(gradient, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .width(250.dp)
                        .height(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            onDismiss()
                        }
                ) {
                    Row() {
                        Text(
                            text = "Pay",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            modifier= Modifier
                                .padding(start = 110.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Back",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    modifier= Modifier.padding(start= 137.5.dp).clickable{onBackClicked()}
                )
            }
        }

    }
}


@Composable
fun DataCashReceiptPage(
    phoneNumber: String?
){
    val context = LocalContext.current

    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        CustomerHeader()
        MerchantInfo()
        DataCardReceiptContent(phoneNumber)
        CustomerFooter()

    }
}

@Composable
fun DataCardReceiptContent(
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
                text = "Data(Cash)",
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
        ){
            Text(
                text = "Data Amount",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "100MB",
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
                text = "NGN 100.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
    }
}