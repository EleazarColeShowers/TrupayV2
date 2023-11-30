package com.example.composecourseyt.ui.otherflows

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R

@Suppress("DEPRECATION")
class EnterPinActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {

            EnterPinPage(amount){
                onBackPressed()
            }
        }
    }
}

@Composable
fun EnterPinPage(amount: String?, onBackClicked: () -> Unit){
//    val isSwappedNonCash = remember { mutableStateOf(true) }
    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            EnterPinHeader {
                onBackClicked()
            }
            PriceForPin(amount)
            InputPin()
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
            EnterBtn(
                gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                ),
                onClickNext = {
                    val intent = Intent(context, CustomerOtherReceipt::class.java)
                    intent.putExtra("amount", amount)
                    context.startActivity(intent)
                }
            )
        }
    }
}


@Composable
fun EnterPinHeader(onBackClicked: () -> Unit){
    val returnArrow= painterResource(id = R.drawable.returnarrow)
    Row(
        modifier = Modifier
            .padding(top=17.5.dp)
    ){
        Image(
            painter = returnArrow,
            contentDescription = null,
            modifier = Modifier
                .padding(1.dp)
                .width(36.dp)
                .height(36.dp)
                .clickable { onBackClicked.invoke() }

        )
        Spacer(modifier = Modifier.width(82.dp))
        Text(
            text = "Enter Pin",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }
}

@Composable
fun PriceForPin(amount: String?){
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Total Amount",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFDD0A35),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
                .padding(start = 7.dp)
        )

        Text(
            text = " ₦$amount.00",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
            letterSpacing = 1.sp,
            modifier = Modifier
                .padding(top=18.dp, start = 140.dp)
        )
    }

}

@Composable
fun InputPin(){
    val line= painterResource(id = R.drawable.line)
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        Text(
            text = "Input Pin",
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
        Text(
            text = "* * * * ",
            fontSize = 70.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFCBDEF5),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 90.dp, top=7.dp)
        )
        
    }
}

@Composable
fun InputPinNumber(onClickNumber: (String) -> Unit, onDeleteNumber: () -> Unit, onClearAll: ()-> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .padding(top = 10.dp, start = 0.dp)
                .fillMaxWidth(),
        ) {
            val borderColor = Color(0x4967791A)
            val borderWidth = 1.dp
            val rightBorderModifier = Modifier.drawBehind {
                drawRect(
                    color = borderColor,
                    topLeft = Offset(size.width - borderWidth.toPx(), 0f),
                    size = Size(borderWidth.toPx(), size.height)
                )
            }
            val bottomBorderModifier = Modifier.drawWithContent {
                drawContent()
                drawRect(
                    color = borderColor,
                    topLeft = Offset(0f, size.height - borderWidth.toPx()),
                    size = Size(size.width, borderWidth.toPx())
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
                    .width(300.dp)
                    .then(bottomBorderModifier) // Apply the bottomBorderModifier

            ) {
                Column(
                    modifier = Modifier
                        .width(96.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "1"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp, end = 31.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }

                    )



                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "2"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp, start = 5.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "3"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .width(300.dp)
                    .then(bottomBorderModifier)


            ) {
                Column(
                    modifier = Modifier
                        .width(96.dp)
                        .height(60.dp)
//                    .border(1.dp, Color(0xFF000000))
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "4"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 17.dp, end = 31.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )



                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "5"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 17.dp, start = 5.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "6"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 17.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .width(300.dp)
                    .then(bottomBorderModifier)


            ) {
                Column(
                    modifier = Modifier
                        .width(96.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "7"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp, end = 31.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )



                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "8"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp, start = 5.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "9"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth()


            ) {
                Column(
                    modifier = Modifier
                        .width(96.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .clickable { onDeleteNumber() }
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val delete= painterResource(id = R.drawable.deletecalc)
                    Image(painter = delete,
                        contentDescription = null,
                        modifier = Modifier
                            .width(28.0752.dp)
                            .height(41.04749.dp)
                            .padding(top = 15.dp)

                    )



                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .then(rightBorderModifier),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "0"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp, start = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(60.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "C",
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClearAll()
                            }
                    )
                }
            }

        }
    }
}
@Composable
fun EnterBtn( gradient:Brush,onClickNext: () -> Unit){
    Spacer(modifier = Modifier.height(44.dp))

    Column(
        modifier = Modifier
            .shadow(
                elevation = 40.dp,
                spotColor = Color(0x08000000),
                ambientColor = Color(0x08000000)
            )
            .width(350.dp)
            .height(51.dp)
            .padding(start = 16.dp)
            .background(gradient, shape = RoundedCornerShape(size = 8.dp))
            .clickable {
                onClickNext()
            }

    ) {
        Text(
            text = "Enter",
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .padding(start = 150.dp, top = 12.dp)
        )
    }
}

fun Modifier.borderBottom(borderWidth: Dp, borderColor: Color): Modifier =
    drawWithContent {
        drawContent()
        drawRect(
            color = borderColor,
            topLeft = Offset(0f, size.height - borderWidth.toPx()),
            size = Size(size.width, borderWidth.toPx())
        )
    }