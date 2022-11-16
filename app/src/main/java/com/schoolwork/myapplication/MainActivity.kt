package com.schoolwork.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.schoolwork.myapplication.ui.theme.Lab1ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    PersonalInformation()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab1ComposeTheme {
        PersonalInformation()

    }
}

@Preview(showBackground = true)
@Composable
fun PersonalInformation() {
    val toggleInformation = remember { mutableStateOf(false) }
    val generateRandomInfo = stringArrayResource(id = R.array.information).random()

    Column(Modifier
        .background(Brush.verticalGradient(colors = listOf(
            Color.Transparent, colorResource(R.color.customblue)), startY = 199f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier) {
            Image(painter = painterResource(id = R.drawable.lab1chatbubble),
                contentDescription = "Speech Bubble",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(270.dp)
                    .absoluteOffset(70.dp))
            Box(modifier = Modifier
                .padding(12.dp)
                .matchParentSize()
                .absoluteOffset(85.dp, 70.dp)) {
                Text(if (toggleInformation.value) generateRandomInfo else generateRandomInfo,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
        Image(painter = painterResource(id = R.drawable._00505_hampusandersson),
            contentDescription = "Hampus Andersson")

        Button(onClick = { toggleInformation.value = !toggleInformation.value },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.customblue),
                contentColor = Color.White)) {
            Text("Ask me!")
        }
    }
}

