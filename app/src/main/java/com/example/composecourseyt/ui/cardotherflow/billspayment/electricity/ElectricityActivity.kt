package com.example.composecourseyt.ui.cardotherflow.billspayment.electricity

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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity

class ElectricityActivity : ComponentActivity() {
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
                ElectricityPage( onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun ElectricityPage(
    onBackClicked: () -> Unit
){
    val context = LocalContext.current
    val regionProvider = listOf(
        "AEDC (Abuja, Niger, Kogi & Nassarawa State)",
        "BEDC (Edo, Delta, Ondo & Ekiti State)",
        "EKEDC (Lagos state)",
        "EEDC (Enugu, Abia, Imo, Anambra & Ebonyi state)",
        "EEDC (Enugu, Abia, Imo, Anambra & Ebonyi state)",
        "IE (Lagos state)",
        "JEDC (Benue, Bauchi, Jos & Gombe state)"
    )
    var filteredItems by remember { mutableStateOf(regionProvider) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        ElectricityHeader(onBackClicked)
        ElectricitySearchBar(
            items = regionProvider,
            onFilter = { filteredItems = it }
        )

        ListOfRegionProviders(
            items = filteredItems,
            onItemClick = { clickedItem ->
                // Define actions for each item here
                when (clickedItem) {
                    "AEDC (Abuja, Niger, Kogi & Nassarawa State)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "BEDC (Edo, Delta, Ondo & Ekiti State)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "EKEDC (Lagos state)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "EEDC (Enugu, Abia, Imo, Anambra & Ebonyi state)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "EEDC (Enugu, Abia, Imo, Anambra & Ebonyi state)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "IE (Lagos state)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                    "JEDC (Benue, Bauchi, Jos & Gombe state)" -> {
                        val intent = Intent(context, CardDetailsActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        )
    }
}

@Composable
fun ElectricityHeader(onBackClicked: () -> Unit){
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
            text = "Electricity",
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
fun ElectricitySearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    items: List<String>,
    onFilter: (List<String>) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(51.dp))
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
fun ListOfRegionProviders(
    items: List<String>,
    onItemClick: (String) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        val line= painterResource(id = R.drawable.line)
        Text(
            text = "Select your Region Provider",
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
        // Display filtered items
        items.forEach { item ->
            // You can replace this with your custom item composable
            Spacer(modifier = Modifier.height(31.dp))
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        spotColor = Color(0x5E496779),
                        ambientColor = Color(0x5E496779)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .width(330.dp)
                    .clickable { onItemClick(item) } // Invoke the callback with the clicked item
                    .height(63.dp)
                    .padding(start = 16.dp, top = 22.dp,  bottom = 22.dp))

            {
                Text(
                    text = item,
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

