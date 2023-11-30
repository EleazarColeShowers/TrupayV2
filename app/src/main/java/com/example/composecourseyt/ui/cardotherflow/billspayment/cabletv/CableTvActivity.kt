package com.example.composecourseyt.ui.cardotherflow.billspayment.cabletv

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.airtimeflow.airtimecard.NetworkProviderItem
import com.example.composecourseyt.ui.cardotherflow.cashdeposit.NextBtn

class CableTvActivity : ComponentActivity() {
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
                CableTvPage( onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun CableTvPage(
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        CableTvHeader(onBackClicked)
        ChooseCableNetwork()
        Spacer(modifier = Modifier.height(120.dp))
        NextBtn(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            ),
            onClickNext = {
                val intent = Intent(context, CableTvReceipt::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun CableTvHeader(onBackClicked: () -> Unit){
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
            text = "Cable Tv",
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
fun ChooseCableNetwork() {
    var phoneNumber by remember { mutableStateOf("") }
    var amountNumber by remember { mutableStateOf("") }
    val dsTvIcon = painterResource(id = R.drawable.dstv_nigeria___jpeg)
    val goTvIcon = painterResource(id = R.drawable.gotv)
    val starTimesIcon = painterResource(id = R.drawable.startimes)

    var expandedFirstDropdown by remember { mutableStateOf(false) }
    var expandedSecondDropdown by remember { mutableStateOf(false) }

    val dropDownArrow = painterResource(id = R.drawable.dropdown_arrow)
    var selectedItem by remember {
        mutableStateOf(
            NetworkProviderItem("Please select your network provider", dropDownArrow)
        )
    }
    var secondSelectedItem by remember {
        mutableStateOf(
            PackageProviderItem("Please select your package")
        )
    }

    val networkProviders = listOf(
        NetworkProviderItem("DSTV", dsTvIcon),
        NetworkProviderItem("GOtv", goTvIcon),
        NetworkProviderItem("Startimes", starTimesIcon),
    )
    val packageProviders = listOf(
        PackageProviderItem("Premium"),
        PackageProviderItem("Compact"),
        PackageProviderItem("Compact Plus"),
    )
    val boxHeight = 189.dp // Adjust the desired height
    val boxWidth = 326.dp  // Adjust the desired width
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 22.dp)
            .fillMaxWidth()
    ) {
        val line = painterResource(id = R.drawable.line)
        Text(
            text = "Choose Network",
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
                    shape = RoundedCornerShape(8.dp)
                    // Rounded corners for the border
                )
                .padding(3.87.dp)
                .width(31.02.dp)
                .height(0.dp)
        )
        Spacer(modifier = Modifier.height(31.25.dp))
        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.clickable { expandedFirstDropdown = !expandedFirstDropdown },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem.name ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF222222),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.04.sp,
                )
                Image(
                    painter = selectedItem.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .padding(1.dp)
                )
            }

            DropdownMenu(
                expanded = expandedFirstDropdown,
                onDismissRequest = { expandedFirstDropdown = false }
            ) {
                Column(
                    modifier = Modifier
                        .width(boxWidth)
                        .height(boxHeight)
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier= Modifier
                            .width(IntrinsicSize.Min)
                    ){
                        networkProviders.forEach { provider ->
                            DropdownMenuItem(
                                text = { Text(text = provider.name) },
//                            image= {Image(painter = provider.icon,)},
                                trailingIcon = {
                                    Image(
                                        painter=provider.icon,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    selectedItem = provider
                                    expandedFirstDropdown = false
                                },
                            )
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(29.dp))

        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.clickable { expandedSecondDropdown = !expandedSecondDropdown },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = secondSelectedItem.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF222222),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.04.sp,
                )
                Image(
                    painter = dropDownArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .padding(1.dp)
                )
            }

            DropdownMenu(
                expanded = expandedSecondDropdown,
                onDismissRequest = { expandedSecondDropdown = false }
            ) {
                Column(
                    modifier = Modifier
                        .width(boxWidth)
                        .height(boxHeight)
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                    ) {
                        packageProviders.forEach { provider ->
                            DropdownMenuItem(
                                text = { Text(text = provider.name) },
                                onClick = {
                                    secondSelectedItem = provider
                                    expandedSecondDropdown= false
                                },
                            )
                        }
                    }
                }
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
                value = phoneNumber,
                onValueChange = {
                    // Only allow numeric characters
                    val newText = it.filter { char -> char.isDigit() }

                    // Limit input to 11 characters
                    if (newText.length <= 10) {
                        phoneNumber = newText
                    }
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxSize()
            )

            if (phoneNumber.isEmpty()) {
                Text(
                    text = "Enter your Smartcard Number",
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
                    text = "Enter Amount",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 1.dp)
                )
            }
        }

    }
}

data class NetworkProviderItem(val name: String, val icon: Painter)

data class PackageProviderItem(val name: String)