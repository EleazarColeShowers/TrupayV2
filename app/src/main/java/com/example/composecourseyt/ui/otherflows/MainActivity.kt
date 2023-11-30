package com.example.composecourseyt.ui.otherflows

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecourseyt.R
import com.example.composecourseyt.data.local.entities.Transaction
import com.example.composecourseyt.ui.TransactionViewModel
import com.example.composecourseyt.ui.airtimeflow.AirtimeCardorCash
import com.example.composecourseyt.ui.cardotherflow.CardOtherActivity
import com.example.composecourseyt.ui.cardpurchaseflow.AccountTypePurchaseActivity
import com.example.composecourseyt.ui.cashflow.CustomerPrintActivity
import com.example.composecourseyt.ui.dataflow.DataCardorCash
import com.example.composecourseyt.ui.transferflow.TransferViaCashActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
//ONBOARDING PAGE
class MainActivity : ComponentActivity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OnBoardPage()
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OnBoardPage(transactionViewModel: TransactionViewModel = hiltViewModel()){
    val zone = ZoneId.of("Africa/Lagos")

    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        ImageAndAddress()

        TransactionHistory(
            gradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
        ),
            onClickEod={
                val intent= Intent(context, EODActivity::class.java)
                intent.putExtra("amount",clickedNumber.value)
                context.startActivity(intent)
            }
        )

        AmountPortion(clickedNumber.value){ number ->
            clickedNumber.value += number
        }

        InputPinNumber(onClickNumber = { number ->
            clickedNumber.value += number
        },
            onDeleteNumber = {
                if (clickedNumber.value.isNotEmpty()) {
                    clickedNumber.value = clickedNumber.value.dropLast(1)
                }
            },
            onClearAll = {
                clickedNumber.value = " "
            }
        )
        PaymentPortion(
            onClickCash = {
                val timeStamp = DateTimeFormatter.
                ofPattern("dd-MM-yyyy HH:mm:ss")
                    .withZone(zone)
                    .format(Instant.now())

                val transaction  = Transaction(amount = clickedNumber.value, timestamp = timeStamp)

                transactionViewModel.insertTransaction(transaction)
                val intent = Intent(context, CustomerPrintActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            },
            onClickCardPurchase={
                val intent= Intent(context, AccountTypePurchaseActivity::class.java)
                intent.putExtra("amount",clickedNumber.value)
                context.startActivity(intent)
            },
            onClickTransfer={
                val intent= Intent(context, TransferViaCashActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            },
            onClickCardOther={
                val intent= Intent(context, CardOtherActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            },
            onClickAirtime={
                val intent=Intent(context, AirtimeCardorCash::class.java )
                intent.putExtra("amount",clickedNumber.value)
                context.startActivity(intent)
            },
            onClickData={
                val intent=Intent(context, DataCardorCash::class.java )
                intent.putExtra("amount",clickedNumber.value)
                context.startActivity(intent)
            }
        )
        TransactionPortion()
        Footer()
    }
}

@Composable
fun ImageAndAddress(){
    val calenderIcon= painterResource(id = R.drawable.calender)
    val borderColor = Color(0x0D496779)  // Specify the opacity as 1.0f
    val borderWidth = 1.dp

    val bottomBorderModifier = Modifier.drawWithContent {
        drawContent()
        drawRect(
            color = borderColor,
            topLeft = Offset(0f, size.height - borderWidth.toPx()),
            size = Size(size.width, borderWidth.toPx())
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x5E838282),
                ambientColor = Color(0x5E838282)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
            .padding(horizontal = 22.dp)
            .then(bottomBorderModifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
       Image(
            painter = painterResource(R.drawable.cyberpay_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(108.dp)
                .height(108.dp)
                .padding(start = 2.dp, top = 6.dp)
        )
        
        Column(
            modifier = Modifier
                .padding(top= 20.dp, start=60.dp)
        ) {
            Text(
                text= "John Doe Inc",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF222222),
                modifier= Modifier
                    .width(102.dp)
                    .height(19.dp)
            )
            Text(
                text = "Shop 15, behind computer village, Bariga, Lagos state.",
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF8E9195),
                letterSpacing = 0.03.sp,
                modifier = Modifier
                    .width(131.dp)
                    .height(24.dp)
                )
            Row {
                Image(
                    painter = calenderIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                CurrentDayText()
            }
        }
    }

}

@Composable
fun CurrentDayText() {
    val currentDate = remember { Calendar.getInstance().time }
    val formattedDate = remember {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(currentDate)
    }

    Text(
        text = formattedDate,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF222222),
        letterSpacing = 0.04.sp,
        modifier= Modifier
            .width(91.dp)
            .height(18.dp)
    )
}

@Composable
fun TransactionHistory(gradient: Brush,onClickEod: ()-> Unit, ){
    val eodBtn= painterResource(id = R.drawable.tabler_report_money)
    val historyBtn= painterResource(id = R.drawable.history_icon)
    Spacer(modifier = Modifier.height(13.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x5E838282),
                ambientColor = Color(0x5E838282)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .height(204.dp)
                .padding(top = 14.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
            )
            Text(
                text = "₦235,000.00",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
                letterSpacing = 1.sp,
                modifier = Modifier
                    .padding(top = 19.5.dp)
            )

            Spacer(modifier = Modifier.height(17.dp)) // Add Spacer with 17.dp padding top

            Text(
                text = "7 Transactions",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF222222),
                modifier = Modifier
                    .background(color = Color(0x0D26FF02), shape = RoundedCornerShape(size = 24.dp))
                    .padding() // Adjust padding as needed
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {},
                modifier = Modifier
//                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 1.dp, top = 3.dp, bottom = 3.dp)
                    .width(130.dp)
                    .height(39.dp)

            ) {
                Row(
                    modifier= Modifier.clickable{
                    onClickEod()
                }
                ) {
                        Image(
                            painter = eodBtn,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 6.dp, top = 4.dp)
                                .width(13.dp)
                                .height(13.dp)
                        )
                        Text(
                            text = "End of Day",
                            fontSize = 13.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            modifier= Modifier
                        )
                }
            }
        }
        Column(
            modifier = Modifier
                .height(204.dp)
                .padding(top = 14.dp, end = 22.dp)
        ) {
            Text(
                text = "Yesterday",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF979797),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "₦71,000.00",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF979797),
                letterSpacing = 1.sp,
                modifier = Modifier
                    .padding(top = 19.5.dp)
            )
            Spacer(modifier = Modifier.height(17.dp)) // Add Spacer with 17.dp padding top
            Text(
                text = "4 Transactions",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF979797),
                modifier = Modifier
                    .background(color = Color(0x0D979797), shape = RoundedCornerShape(size = 24.dp))
            )

            Spacer(modifier = Modifier.height(36.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {},
                modifier = Modifier
                    .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 1.dp, top = 3.dp, bottom = 3.dp)
                    .width(130.dp)
                    .height(39.dp)


            ) {
                Row {
                    Image(
                        painter = historyBtn,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp, top = 4.dp)
                            .width(13.dp)
                            .height(13.dp)
                    )
                    Text(
                        text = "History",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        modifier= Modifier
                    )
                }


            }
        }
    }
}

@Composable
fun AmountPortion( value: String,onClickNumber: (String) -> Unit){
    val line= painterResource(id = R.drawable.line)
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 22.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = "Start new Transaction",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(147.dp)
                .height(17.dp)
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


        Spacer(modifier = Modifier.height(30.06.dp))
//        Spacer(modifier = Modifier.width(135.5.dp))
        Text(
            text = "Input Amount",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFDD0A35),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
        )

        Spacer(modifier = Modifier.height(12.94.dp))

        Text(
            text = "₦$value.00",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF042E46),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp,)
                .fillMaxWidth()
                .clickable { onClickNumber("1") }
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun PaymentPortion(onClickCash: () -> Unit, onClickCardPurchase: ()-> Unit, onClickTransfer: ()->Unit, onClickCardOther: ()-> Unit, onClickAirtime: ()->Unit, onClickData:()->Unit) {
    val cash = painterResource(id = R.drawable.newmoney)
    val card = painterResource(id = R.drawable.newcard)
    val transfer = painterResource(id = R.drawable.newtransfer)
    val line= painterResource(id = R.drawable.line)
    val airtime= painterResource(id = R.drawable.newairtime)
    val data= painterResource(id = R.drawable.newdata)
    val others= painterResource(id = R.drawable.newothers)
    Column(
        modifier = Modifier
            .padding(top = 24.95.dp, start = 10.dp)
    ) {
        Text(
            text = "Select Payment Method",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(162.dp)
                .height(17.dp)
                .padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(4.5.dp))
        Row(
            modifier= Modifier.padding(start = 12.dp)
        ) {
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
        }

        Spacer(modifier = Modifier.height(29.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickCash() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = cash, // Replace with your image resource
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(48.dp),

//                            .background(
//                                color = Color(0x5EE1FD38),
//                                shape = CircleShape
//                            )
                    )
                }
                    Text(
                        text = "Cash",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color= Color.White,
                        modifier = Modifier
                            .padding(top = 17.dp, start = 20.dp),
                        style = TextStyle(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                            )
                        ),
                    )

            }
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickCash() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .clickable {
                            onClickCardPurchase()
                        }

                ) {
                    Image(
                        painter = card,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                }
                    Text(
                        text = "Purchase",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 17.dp, start = 20.dp),
                        style = TextStyle(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                            )
                        ),
                    )

            }
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickTransfer() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = transfer,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                    Text(
                        text = "Transfer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 17.dp, start = 20.dp),
                        style = TextStyle(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                            )
                        ),
                    )


            }

        }
        Spacer(modifier =Modifier.height(29.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickAirtime() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = airtime, // Replace with your image resource
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(48.dp),
                    )
                }
                Text(
                    text = "Airtime",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color= Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 20.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )

            }
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickCash() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .clickable {
                            onClickData()
                        }

                ) {
                    Image(
                        painter = data,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                }
                Text(
                    text = "Data",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 20.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )

            }
            Column {
                Column(
                    modifier = Modifier
                        .width(85.dp)
                        .height(76.dp)
                        .clickable { onClickCardOther() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = others,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Text(
                    text = "Others",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 20.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )


            }

        }
    }
}

@Composable
fun TransactionPortion(){
    val transfer= painterResource(id = R.drawable.transfers)
    val line= painterResource(id = R.drawable.line)
    val mastercard= painterResource(id = R.drawable.mastercard_logo)
    val visacard= painterResource(id = R.drawable.visa)
    val money= painterResource(id = R.drawable.money)
    val verveCard= painterResource(id = R.drawable.verve_logo)

    Column(
        modifier = Modifier
            .padding(top=24.dp, start = 10.dp)
    ) {
        Row {
            Column {
                Text(
                    text = "Transactions",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF222222),
                    modifier = Modifier
                        .width(88.dp)
                        .height(17.dp)
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
            }

            Spacer(modifier = Modifier.width(210.dp))
            Text(
                text = "See all",
                fontSize = 10.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
                modifier = Modifier
                    .width(32.dp)
                    .height(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.75.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))

                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row{
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "₦7,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ){
                Text(
                    text = "3:45pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {


                Image(
                    painter = mastercard,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(25.6.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Card",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                    )
                Spacer(modifier = Modifier.width(130.dp))
                Text(
                    text = "Failed",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(91.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDEDD0A35),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 30.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row{
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "₦7,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )

            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "12:05pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {
                Image(
                    painter = transfer,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Transfer",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
                Spacer(modifier = Modifier.width(138.dp))

                Text(
                    text = "Successful",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(91.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDE01AD2A),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 22.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row{
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "₦32,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )
            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "01:34pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {

                Image(
                    painter = visacard,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Card",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
                Spacer(modifier = Modifier.width(138.dp))
                Text(
                    text = "Successful",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(91.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDE01AD2A),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 22.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row{
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "₦32,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )

            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "11:37am",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {

                Image(
                    painter = money,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .size(24.dp)
                )
                Text(
                    text = "Cash",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
                Spacer(modifier = Modifier.width(134.dp))
                Text(
                    text = "Successful",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(91.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDE01AD2A),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 22.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row{
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "₦32,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )

            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "3:45pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {
                Image(
                    painter = verveCard,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(34.43479.dp)
                        .height(12.dp)
                )
                Text(
                    text = "Card",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
                Spacer(modifier = Modifier.width(125.dp))

                Text(
                    text = "Failed",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(91.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDEDD0A35),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 30.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
        }
    }
}

@Composable
fun Footer() {
    Spacer(modifier = Modifier.height(28.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF070F13),
                shape = RoundedCornerShape(
                    topStart = 6.dp,
                    topEnd = 6.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp) // Adjust the top padding as needed
    ) {
        Text(
            text = "Powered by TruPay",
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(start= 125.dp)
        )
    }
}
