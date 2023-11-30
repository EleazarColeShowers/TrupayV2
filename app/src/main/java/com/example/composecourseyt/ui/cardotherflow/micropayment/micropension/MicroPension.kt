package com.example.composecourseyt.ui.cardotherflow.micropayment.micropension

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composecourseyt.ui.PaymentActivity

class MicroPension  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        val intent = Intent(this, PaymentActivity::class.java)

        intent.putExtra("amount", amount)
        setContent {

            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White),
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
//                PriceForInsertWithdraw(amount)
                MicroPension(onClickRemit = {})


            }
        }
    }
}

@Composable
fun MicroPension(onClickRemit: (Any?) -> Unit) {
    var pensionNumber by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var isRsaPinFilled by remember { mutableStateOf(false) }
    var selectedPensionProvider by remember { mutableStateOf("Click to Select") }
    var showNextBtn by remember { mutableStateOf(true) }
    var isOneOffClicked by remember { mutableStateOf(false) }
    var isRecurringClicked by remember { mutableStateOf(false) }
    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 16.dp)
            .verticalScroll(rememberScrollState()),
    ){
        Text(
            text = "MICROPENSION",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Provide a valid RSA number and remit to your account instantly",
            fontSize = 13.sp,
            fontWeight = FontWeight.Light,
            color= Color.Black
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            Text(
                text = "RSA PIN",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0x0D496779),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .width(326.dp)
                    .height(50.dp)
                    .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
            ) {
                BasicTextField(
                    value = pensionNumber,
                    onValueChange = {
                        val newText = it.filter { char -> char.isDigit() }

                        // Limit input to 50 characters
                        if (newText.length <= 11) {
                            pensionNumber = newText
                            isRsaPinFilled = newText.isNotEmpty()
                        }
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 13.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxSize()
                )

                if (pensionNumber.isEmpty()) {
                    Text(
                        text = "XXXXXXXXXXX",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 1.dp)
                    )
                }
            }
        }
        if (isRsaPinFilled) {
            Column {
                Text(
                    text = "Name",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x0D496779),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .width(326.dp)
                        .height(50.dp)
                        .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
                ) {
                    BasicTextField(
                        value = name,
                        onValueChange = {
                            val newText = it.filter { char -> char.isLetter() }

                            // Limit input to 50 characters
                            if (newText.length <= 50) {
                                name = newText
                            }
                        },
                        textStyle = TextStyle(color = Color.Black, fontSize = 13.sp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxSize()
                    )

                    if (name.isEmpty()) {
                        Text(
                            text = "Enter your name",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 1.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column {
            Text(
                text = "Pension Provider",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column (
                modifier= Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0x0D496779),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .width(326.dp)
                    .height(51.dp)
                    .padding(start = 10.dp)
                    .clickable {
                        showDialog = true

                    }
            ){
                Text(
                    text = selectedPensionProvider,
                    fontSize = 13.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.04.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                // Show the AlertDialog when showDialog is true
                if (showDialog) {
                    Dialog(
                        onDismissRequest = {
                            showDialog = false
                        }
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.White,
                        ) {
                            PensionProvider { provider ->
                                selectedPensionProvider = provider
                                showDialog = false
                            }
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Payment Type",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color= Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            val oneOffBorderColor = if (isOneOffClicked) Color.Red else Color.Gray
            val recurringBorderColor = if (isRecurringClicked) Color.Red else Color.Gray


            Column(
                modifier= Modifier
                    .clickable { isOneOffClicked = true; isRecurringClicked = false }
                    .height(70.dp)
                    .width(140.dp)
                    .border(1.dp, oneOffBorderColor, shape = RoundedCornerShape(size = 10.dp))
                    .background(Color.White), // Add a background color to cover the entire space if needed
                verticalArrangement = Arrangement.Center, // Center vertically
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "One Off",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

            }
            Column(
                modifier= Modifier
                    .clickable { isRecurringClicked = true; isOneOffClicked = false }
                    .height(70.dp)
                    .width(140.dp)
                    .padding(end = 16.dp)
                    .border(1.dp, recurringBorderColor, shape = RoundedCornerShape(size = 10.dp))
                    .background(Color.White), // Add a background color to cover the entire space if needed
                verticalArrangement = Arrangement.Center, // Center vertically
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Recurring (Monthly)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        if (showNextBtn) {
            SubmitBtn(onClick = {
                // Change the state when the button is clicked
                showNextBtn = false
            })
        } else {
            // Replace NextBtn with a new composable
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Account Owner",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    modifier = Modifier
                        .background(Color.Gray, shape = RoundedCornerShape(size = 8.dp))
                        .width(326.dp)
                        .height(45.dp)
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = "TUNJI ANDREWS",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .padding(top = 14.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .width(326.dp)
                        .height(40.dp)
                        .background(Color.Red, shape = RoundedCornerShape(size = 8.dp))
                        .clickable {
                            onClickRemit {}
                        }
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Continue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(start = 130.dp, top = 12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "No RSA Pin?, Register Here",
                color= Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

            Text(
                text = "Contact Us",
                color= Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

        }
    }
}

@Composable
fun SubmitBtn(onClick: () -> Unit) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(326.dp)
    ) {
        Column(
            modifier = Modifier
                .height(50.dp)
                .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                .clickable {
                    onClick()
                }
                .align(Alignment.Center)
        ) {
            Text(
                text = "Submit",
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(start = 150.dp, top = 12.dp)
            )
        }
    }
}

@Composable
fun PensionProvider(onProviderSelected: (String) -> Unit){
    Column(
        modifier= Modifier
            .padding(start = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "ARM Pensions",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("ARM Pensions") }

        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Access Pensions ",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Access Pensions") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Guaranty Trust Pension Managers",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Guaranty Trust Pension Managers") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Stanbic Ibtc Pension Managers",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Stanbic Ibtc Pension Managers") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Trustfund Pensions",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Trustfund Pensions") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "NLPC Pension Fund Administrators",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("NLPC Pension Fund Administrators") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Tangerine APT Pensions Limited",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Tangerine APT Pensions Limited") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "PAL Pensions",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("PAL Pensions") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Leadway Pensure",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Leadway Pensure") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Crusader Sterling Pensions Limited",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Crusader Sterling Pensions Limited") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Oak Pensions",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Oak Pensions") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Norrenberger Pensions Limited",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Norrenberger Pensions Limited") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Radix Pension Managers Limited",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Radix Pension Managers Limited") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "FCMB Pensions",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("FCMB Pensions") }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Veritas Glanvills Pensions Limited",
            fontSize = 18.sp,
            color= Color.Black,
            fontWeight = FontWeight.Light,
            modifier= Modifier
                .padding(bottom = 15.dp)
                .clickable { onProviderSelected("Veritas Glanvills Pensions Limited") }
        )

    }
}
