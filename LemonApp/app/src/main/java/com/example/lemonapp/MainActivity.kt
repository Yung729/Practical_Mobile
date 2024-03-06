package com.example.lemonapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonapp.ui.theme.LemonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonAppTheme {
                LemonApp()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {

    var step by remember { mutableStateOf(1) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade", fontWeight = FontWeight.Bold
                    )
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }) {


        when (step) {
            1 -> LemonImageAndText(textLabelResourceId = R.string.Lemon_tree,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = 1,
                onImageClick = {
                    step = 2
                })

            2 -> LemonImageAndText(textLabelResourceId = R.string.Lemon,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = 2,
                onImageClick = {
                    step = 3
                })

            3 -> LemonImageAndText(textLabelResourceId = R.string.Glass_of_lemonade,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = 3,
                onImageClick = {
                    step = 4
                })

            4 -> LemonImageAndText(textLabelResourceId = R.string.Empty_glass,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = 4,
                onImageClick = {
                    step = 1
                })
        }

    }
}

@Composable
fun LemonImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = onImageClick) {
                Image(
                    painter = painterResource(id = drawableResourceId),
                    contentDescription = contentDescriptionResourceId.toString()
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(textLabelResourceId)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    LemonApp()
}