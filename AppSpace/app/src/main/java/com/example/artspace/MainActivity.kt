package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var step by remember { mutableStateOf(1) }

                    if (step == 1)
                        ArtSpaceScreen(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            "Oh My God",
                            "Jibai (2002)",
                            R.drawable.download__1_,
                            previousButton = {
                                step = 3
                            },
                            nextButton = {
                                step += 1
                            }

                        )
                    else if (step == 2)
                        ArtSpaceScreen(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            "Cool Cat",
                            "Joseph (2002)",
                            R.drawable.images,
                            previousButton = {
                                step -= 1
                            },
                            nextButton = {
                                step += 1
                            }
                        )
                    else if (step == 3)
                        ArtSpaceScreen(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            "Shabi Cat",
                            "Jibai (2002)",
                            R.drawable.images__1_,
                            previousButton = {
                                step -= 1
                            },
                            nextButton = {
                                step = 1
                            }
                        )

                }


            }
        }
    }
}

@Composable
fun ArtSpaceScreen(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    image: Int,
    previousButton: () -> Unit,
    nextButton: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .background(color = Color.Black)
                .padding(30.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(300.dp, 300.dp)
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(10.dp)
        ) {
            Text(
                text = title,
                fontSize = 25.sp
            )
            Text(text = author)
        }


        Spacer(modifier = Modifier.size(20.dp))

        Row(horizontalArrangement = Arrangement.Absolute.spacedBy(20.dp)) {
            Button(onClick = previousButton) {
                Text(text = "Previous")
            }

            Button(onClick = nextButton) {
                Text(text = "Next")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {

}