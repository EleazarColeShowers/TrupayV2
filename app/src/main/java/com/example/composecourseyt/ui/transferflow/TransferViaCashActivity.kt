package com.example.composecourseyt.ui.transferflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cashflow.CustomerFooter
import com.example.composecourseyt.ui.cashflow.CustomerHeader
import com.example.composecourseyt.ui.cashflow.MerchantInfo
import com.example.composecourseyt.ui.cashflow.StaticButtons
import com.example.composecourseyt.ui.otherflows.MainActivity


class TransferViaCashActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        val intent = Intent(this, PaymentActivity::class.java)

        intent.putExtra("amount", amount)
        setContent {
            var isPopupVisible by remember { mutableStateOf(true) }
            var isCheckStatusPopupVisible by remember { mutableStateOf(false) }
            var isTransactionApprovedPopUpVisible by remember { mutableStateOf(false) }
                if (isPopupVisible) {
                    TransferPopUp(
                        amount = amount,
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        ),
                        onClickCheckStatus = {
                            // Show the "Check Status" pop-up
                            isPopupVisible = false
                            isCheckStatusPopupVisible = true
                        },
                        onClickClose = {
                            val intent = Intent(this@TransferViaCashActivity, MainActivity::class.java)
                            startActivity(intent)
                        },
                        onDismiss = {
                            val intent = Intent(this@TransferViaCashActivity, MainActivity::class.java)
                            startActivity(intent)
                        },

                    )
                } else if (isCheckStatusPopupVisible) {
                    TransferCheckStatusPopUp(
                        amount = amount,
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        ),
                        onClickClose = {
                            val intent = Intent(this@TransferViaCashActivity, MainActivity::class.java)
                            startActivity(intent)
                        },
                        onDismiss = {
                            // Close the "Check Status" pop-up
                            isCheckStatusPopupVisible = false
                        },
                        onClickProceed = {
                            isCheckStatusPopupVisible = false
                            isTransactionApprovedPopUpVisible= true
                        }
                    )
                }else if (isTransactionApprovedPopUpVisible) {
                    TransferApprovedPopUp(
                            onDismiss = { isTransactionApprovedPopUpVisible = false
                            },
                            gradient = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                            ),
                            onClickClose ={
                                val intent = Intent(this@TransferViaCashActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                    )
                } else{
            // Display the main page content here when neither pop-up is visible
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
                                TransferViaCashPage(amount)
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
                                    val intent = Intent(context, TransferMerchantReceipt::class.java)
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
fun TransferPopUp(amount: String?, onDismiss: () -> Unit,gradient: Brush,onClickClose: () -> Unit, onClickCheckStatus: () -> Unit){
    // Define the fixed width and height for the pop-up
    val popupWidth = 344.dp
    val popupHeight = 521.dp
    val successfulTransaction= painterResource(id = R.drawable.transaction_successful)
    val printIcon= painterResource(id = R.drawable.print_icon)
    val context = LocalContext.current
    val copy= painterResource(id = R.drawable.copybtn)


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
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),
            ) {

                Text(
                    text = "Total Amount",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFDD0A35),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 7.dp, top = 25.dp),
                )

                Text(
                    text = " ₦$amount.00",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .padding(top=4.dp, start = 86.dp )
                )
                Text(
                    text = "Please make a Transfer to the account Below",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(top=14.dp, start = 63.dp)
                )

                Column(
                    modifier= Modifier
                        .padding(start=10.dp)

                ) {
                    Column() {
                        Text(
                            text = "Bank",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFDD0A35),
                            modifier = Modifier
                                .padding( top=18.dp)
                        )
                        Row() {
                            Text(
                                text = "Zenith Bank",
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .width(166.dp)
                                    .height(35.dp)
                            )
                            Spacer(modifier = Modifier.width(90.dp))
                            Image(painter = copy,
                                contentDescription = null,
                                modifier= Modifier
                                    .padding(top = 14.dp)
                                    .width(14.dp)
                                    .height(16.dp)
                            )
                        }
                        Text(
                            text = "Account Name",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFDD0A35),
                            modifier = Modifier
                                .padding( top=18.dp)

                        )
                        Row() {
                            Text(
                                text = "Trupay",
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .width(166.dp)
                                    .height(35.dp)
                            )
                            Spacer(modifier = Modifier.width(90.dp))
                            Image(painter = copy,
                                contentDescription = null,
                                modifier= Modifier
                                    .padding(top = 14.dp)
                                    .width(14.dp)
                                    .height(16.dp)
                            )
                        }
                        Text(
                            text = "Account Number",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFDD0A35),
                            modifier = Modifier
                                .padding( top=18.dp)
                        )
                        Row() {
                            Text(
                                text = "3022228442",
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .width(166.dp)
                                    .height(35.dp)
                            )
                            Spacer(modifier = Modifier.width(90.dp))
                            Image(painter = copy,
                                contentDescription = null,
                                modifier= Modifier
                                    .padding(top = 14.dp)
                                    .width(14.dp)
                                    .height(16.dp)
                            )
                        }
                        
                    }
                    Spacer(modifier = Modifier.height(69.dp))
                    Column(
                        modifier = Modifier
                            .background(gradient, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .width(250.dp)
                            .height(20.dp)
                            .clickable {
                                onClickCheckStatus()
                            }
                    ) {
                        Row() {
                            Text(
                                text = "Check Status",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                modifier= Modifier
                                    .padding(start = 70.dp)
                            )

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TransferCheckStatusPopUp(amount: String?, onDismiss: () -> Unit,gradient: Brush,onClickClose: () -> Unit,onClickProceed: () -> Unit){
    val popupWidth = 344.dp
    val popupHeight = 437.dp
    val context = LocalContext.current
    val sending= painterResource(id = R.drawable.send)
    val receiving= painterResource(id = R.drawable.receive)
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
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
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),
            ) {
                Text(
                    text = "Total Amount",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFDD0A35),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 7.dp, top = 25.dp),
                )

                Text(
                    text = " ₦$amount.00",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .padding(top=4.dp, start = 86.dp )
                )

                Text(
                    text = "Please wait for a Few Minutes While We Confirm The Transfer",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(top=10.dp, start = 15.dp)
                )
                Spacer(modifier = Modifier.height(73.dp))
                Row (
                    modifier = Modifier
                        .width(344.dp)
                        .height(50.dp)
                        .padding(start = 10.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically // Align children vertically in the center
                ){
                    Image(
                        painter = sending,
                        contentDescription = null,
                        modifier= Modifier
                            .padding(1.dp)
                            .width(56.dp)
                            .height(54.dp)
                    )

                    Canvas(
                        Modifier
                            .width(180.dp)
                            .height(1.dp)) {

                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            pathEffect = pathEffect
                        )
                    }

                    Image(
                        painter = receiving,
                        contentDescription = null,
                        modifier= Modifier
                            .padding(1.dp)
                            .width(68.dp)
                            .height(68.dp)
                    )
                }
                Text(
                    text = "Sending",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier
                        .padding(start = 131.dp)
                )
                Spacer(modifier = Modifier.height(58.dp))
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(gradient, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .width(250.dp)
                            .height(20.dp)
                            .padding(start = 9.dp)
                            .clickable {
                                onClickProceed()
                            },
                    ) {
                        Row() {
                            Text(
                                text = "Proceed",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                modifier = Modifier
                                    .padding(start = 90.dp)
                            )

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TransferApprovedPopUp( onDismiss: () -> Unit,gradient: Brush,onClickClose: () -> Unit){
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
fun TransferViaCashPage(amount: String?){
    val context = LocalContext.current

    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        CustomerHeader()
        MerchantInfo()
        TransferReceiptContent(amount)
        CustomerFooter()

    }

}

@Composable
fun TransferReceiptContent(amount: String?) {
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
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )  {
            Text(
                text = "Transaction Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "Cash Deposit",
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
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )  {
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
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Acc Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "Savings",
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
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Stan",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Text(
                text = "294082",
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
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )  {
            Text(
                text = "RRN",
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
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(bottomBorderModifier),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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
        Spacer(modifier = Modifier.height(44.14.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(bottomBorderModifier),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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