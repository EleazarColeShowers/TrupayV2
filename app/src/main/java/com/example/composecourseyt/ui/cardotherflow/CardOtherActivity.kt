package com.example.composecourseyt.ui.cardotherflow

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cardotherflow.balanceenquiry.AccountTypeBalanceEnquiryActivity
import com.example.composecourseyt.ui.cardotherflow.billspayment.BillsPaymentActivity
import com.example.composecourseyt.ui.cardotherflow.cashadvance.CashAdvanceActivity
import com.example.composecourseyt.ui.cardotherflow.cashback.CashBackActivity
import com.example.composecourseyt.ui.cardotherflow.cashdeposit.AccountTypeActivity
import com.example.composecourseyt.ui.cardotherflow.cashrefund.CashRefundActivity
import com.example.composecourseyt.ui.cardotherflow.cashwithdraw.AccountTypeWithdrawalActivity
import com.example.composecourseyt.ui.cardotherflow.micropayment.MicroPayment
import com.example.composecourseyt.ui.cardotherflow.preauth.PreAuthActivity
import com.example.composecourseyt.ui.cardotherflow.reversaltransfer.TransactionHistoryActivity
import com.example.composecourseyt.ui.cardotherflow.salescompletion.SalesCompletionActivity
import com.example.composecourseyt.ui.transferflow.TransferActivity

class CardOtherActivity: ComponentActivity() {
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
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
//                PriceForInsertWithdraw(amount)
                CardOtherPage(amount, onBackClicked = { onBackPressed() })


            }
        }
    }
}
@Composable
fun CardOtherPage(
    amount: String?,
    onBackClicked: () -> Unit

){
    val context= LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        CardOtherHeader(onBackClicked)
        CardOptions(
            onBalanceEnquiryClick =  {
                val intent = Intent(context, AccountTypeBalanceEnquiryActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashRefundClick =  {
                val intent = Intent(context, CashRefundActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashAdvanceClick =  {
                val intent = Intent(context, CashAdvanceActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashBackClick =  {
                val intent = Intent(context, CashBackActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onReversalTransferClick= {
                val intent = Intent(context, TransactionHistoryActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashDepositClick= {
                val intent = Intent(context, AccountTypeActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashWithdrawalClick= {
                val intent = Intent(context, AccountTypeWithdrawalActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onTransferClick= {
                val intent = Intent(context, TransferActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onPreAuthClick= {
                val intent = Intent(context, PreAuthActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onSalesCompletionClick= {
                val intent = Intent(context, SalesCompletionActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },

            onBillsPaymentClick= {
                val intent = Intent(context, BillsPaymentActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onMicroPaymentClick= {
                val intent = Intent(context, MicroPayment::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }

        )
    }
}

@Composable
fun CardOtherHeader(onBackClicked: () -> Unit){

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
            text = "Others",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun CardOptions(
    onBalanceEnquiryClick: ()-> Unit,
    onCashRefundClick: ()->Unit,
    onCashAdvanceClick: ()->Unit,
    onCashBackClick: ()->Unit,
    onReversalTransferClick: ()->Unit,
    onCashDepositClick:() ->Unit,
    onCashWithdrawalClick:() ->Unit,
    onTransferClick:() ->Unit,
    onPreAuthClick:() ->Unit,
    onSalesCompletionClick:() ->Unit,
    onBillsPaymentClick:() ->Unit,
    onMicroPaymentClick: () ->Unit
){
    val context = LocalContext.current
    val line= painterResource(id = R.drawable.line)
    Spacer(modifier = Modifier.height(54.4.dp))
    Column(
        modifier = Modifier.padding(start = 22.dp, bottom = 100.dp)
    ) {
        Column {
            Text(
                text = "Card(Others)",
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
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onBalanceEnquiryClick() }
        ) {
            Text(
                text = "Balance Enquiry",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.White,
                style = TextStyle(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    )
                )
            )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashDepositClick() }
        ) {
            Text(
                text = "Cash Deposit",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashWithdrawalClick() }
        ) {
            Text(
                text = "Cash Withdrawal",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onTransferClick() }
        ) {
            Text(
                text = "Transfer Via Card",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashRefundClick() }
        ) {
            Text(
                text = "Refund",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

            )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashAdvanceClick() }
        ) {
            Text(
                text = "Cash Advance",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashBackClick() }
        ) {
            Text(
                text = "Cash Back",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onReversalTransferClick() }

        ) {
            Text(
                text = "Reversal",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onPreAuthClick() }
        ) {
            Text(
                text = "Pre-Auth",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onSalesCompletionClick() }
        ) {
            Text(
                text = "Sales Completion",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onBillsPaymentClick() }
        ) {
            Text(
                text = "Bills Payment",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,
                modifier = Modifier
                )
        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                .background(Color.White)
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onMicroPaymentClick() }
        ) {
            Text(
                text = "Micro Payment",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,
                modifier = Modifier
            )
        }
    }
}

