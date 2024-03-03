package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdaycard.ui.theme.BirthdayCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        message = "Happy Birthday Sam!", from = "From Jibai"
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, modifier: Modifier = Modifier, from: String) {

    Column(modifier = modifier) {
        Text(
            text = message, fontSize = 90.sp, lineHeight = 110.sp, textAlign = TextAlign.Center
        )

        Text(
            text = from, fontSize = 36.sp, lineHeight = 45.sp
        )
    }


}

@Composable
fun GreetingImage(message: String, modifier: Modifier = Modifier, from: String) {
    val image = painterResource(R.drawable.androidparty)

    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message, from = from, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }


}

@Preview(
    showBackground = true, backgroundColor = 0xFFE6BDBD, showSystemUi = false, name = "MyPreview"
)
@Composable
fun BirthdayCardPreview() {
    BirthdayCardTheme {

        GreetingImage(
            message = "Happy Birthday Sam!", from = "From Emma"
        )
    }
}