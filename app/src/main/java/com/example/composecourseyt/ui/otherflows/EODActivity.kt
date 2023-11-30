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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.cashflow.MerchantCashReceiptActivity

class EODActivity: ComponentActivity() {
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
                EodPage( onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun EodPage(
    onBackClicked: () -> Unit
) {

    val context = LocalContext.current
    val eodTransactions = listOf(
        "Approved Transactions",
        "Declined Transactions",
        "Cash Transactions",
        "Card Transactions",
        "Cash Amount",
        "Card Amount",
        "Total Amount"
    )
    var filteredItems by remember { mutableStateOf(eodTransactions) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        EodHeader(onBackClicked)
        DateSelection()
        Warning()
        EodSearchBar(
            items = eodTransactions,
            onFilter = { filteredItems = it }
        )
        ListOfDailyTransactions(
            items = filteredItems,

        )
        PrintBtn(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            ),
            onClickClose = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            onClickNext = {
                val intent = Intent(context, MerchantCashReceiptActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun EodHeader(onBackClicked: () -> Unit){
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
            text = "End Of Day",
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
fun DateSelection(){
    val calendarIcon= painterResource(id = R.drawable.calender)
    var isCalendarVisible by remember{ mutableStateOf(false) }
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(35.dp))
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Image(
                painter = calendarIcon,
                contentDescription = "calendar",
                modifier= Modifier
                    .size(24.dp)
                    .padding(1.dp)
                    .clickable { isCalendarVisible = true }
            )
            Text(
                text = "Select Date",
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Text(
            text = "MM/DD/YY",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )
    }
   if (isCalendarVisible) {
        PopupCalender(
            onDismiss = { isCalendarVisible = false },
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            )

        )
    }


}

@OptIn(ExperimentalTextApi::class)
@Composable
fun PopupCalender(onDismiss: () -> Unit, gradient: Brush) {
    // Define the fixed width and height for the pop-up
    val popupWidth = 356.dp
    val popupHeight = 212.dp

    val context = LocalContext.current

    // Create a Dialog to display the pop-up content
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            modifier = Modifier.width(popupWidth).height(popupHeight)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xA8DFDFDF),
                        shape = RoundedCornerShape(size = 2.dp)
                    )
                    .padding(start = 19.dp, top = 12.dp, bottom = 13.dp, end = 19.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.04.sp,
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                    modifier = Modifier.clickable{
                        onDismiss()
                    }
                )
                Text(
                    text = "Accept",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.04.sp,
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    )
                )
            }

            Row(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)) {
                    item {
                        // Add day options
                        for (day in 1..30) {
                            Text(
                                text = day.toString(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                modifier = Modifier.padding(8.dp)
                                    .clickable{},
                                letterSpacing = 0.06.sp,
                                )

                        }
                    }
                }

                LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)) {
                    item {
                        // Add month options (you can adjust the number of items as needed)
                        val months = listOf(
                            "January", "February", "March", "April",
                            "May", "June", "July", "August", "September",
                            "October", "November", "December"
                        )
                        months.forEach { month ->
                            Text(
                                text = month,
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                modifier = Modifier.padding(8.dp)
                                    .clickable{},
                                letterSpacing = 0.06.sp,
                                )
                        }
                    }
                }

                LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)) {
                    item {
                        // Add year options (2018-2023)
                        val years = (2018..2023).map { it.toString() }
                        years.forEach { year ->
                            Text(
                                text = year,
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                modifier = Modifier.padding(8.dp)
                                    .clickable{},
                                letterSpacing = 0.06.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Warning(){
    val warningIcon= painterResource(id = R.drawable.warning)
    Spacer(modifier = Modifier.height(25.dp))
    Row(
        modifier= Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ){
        Image(
            painter = warningIcon,
            contentDescription = "warning",
            modifier= Modifier
                .size(19.dp)
        )

        Text(
            text = "You can only print/reprint your daily transaction end of day report for the last 7 days from this device",
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(300),
                color = Color(0xFF555555),
                textAlign = TextAlign.Center,

        )
    }

}

@Composable
fun EodSearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    items: List<String>,
    onFilter: (List<String>) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(26.dp))
    Column(
        modifier= Modifier.padding(start = 25.dp)
    ) {
        Box(
            modifier = modifier
                .border(
                    width = 2.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(1.dp)
                .width(330.dp)
                .height(46.dp)
                .padding(start = 8.dp, top = 11.dp, end = 8.dp, bottom = 11.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )

                BasicTextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                        val filteredItems = if (newText.isEmpty()) items else items.filter {
                            it.contains(newText, ignoreCase = true)
                        }
                        onFilter(filteredItems)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            // Handle search if needed
                        }
                    ),
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ListOfDailyTransactions(
    items: List<String>,
) {
    // Create a map that associates each item with a specific number
    val itemNumbers = mapOf(
        "Approved Transactions" to "40",
        "Declined Transactions" to "31",
        "Cash Transactions" to "23",
        "Card Transactions" to "56",
        "Cash Amount" to "₦ 25,000.00",
        "Card Amount" to "₦ 32,000.00",
        "Total Amount" to "₦77,000.00"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        val line= painterResource(id = R.drawable.line)
        Text(
            text = "Today's Transactions",
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
        // Display filtered items with numbers
        items.forEach { item ->
            val itemNumber = itemNumbers[item] // Get the number associated with the item
            Spacer(modifier = Modifier.height(31.dp))
            Column(
                modifier = Modifier
                    .width(330.dp)
                    .height(63.dp)
                    .padding(top = 40.dp))

            {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Display the item number and text
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = itemNumber ?: "", // Use the item number if available
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.04.sp,
                        color = Color.Black
                    )
                }

            }
        }
    }
}

@Composable
fun PrintBtn(gradient: Brush, onClickClose: () -> Unit, onClickNext: () -> Unit){
    Column() {
        Spacer(modifier = Modifier.height(57.dp))
        Column(
            modifier = Modifier

                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x08000000),
                    ambientColor = Color(0x08000000)
                )
                .width(327.dp)
                .height(80.dp)
                .padding(start = 45.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                .clickable {
                    onClickNext()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center text vertically

        ) {
            Text(
                text = "PRINT",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(start = 110.dp, top = 7.dp)

            )
        }
        Text(
            text = "Close",
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .padding(start = 165.dp)
                .clickable {
                    onClickClose()
                }
        )
        Spacer(modifier = Modifier.height(73.dp))

    }
}