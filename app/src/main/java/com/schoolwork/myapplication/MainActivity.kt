package com.schoolwork.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


import com.schoolwork.myapplication.ui.theme.Lab1ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1ComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun App() {
    ComposableLifecycle { source, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            Lifecycle.Event.ON_START -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            Lifecycle.Event.ON_STOP -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            Lifecycle.Event.ON_DESTROY -> {
                Log.d("TAG", "ComposableLifecycle: $source - $event")
            }
            else -> {}
        }
    }

    var toggleInformation by remember { mutableStateOf(false) }
    var showInformation by remember { mutableStateOf(false) }
    val generateRandomInfo = stringArrayResource(R.array.information).random()

    Column(Modifier
        .background(Brush.verticalGradient(colors = listOf(
            Color.Transparent, colorResource(R.color.customblue)), startY = 199f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (showInformation) {
            Box(modifier = Modifier) {
                Image(painter = painterResource(R.drawable.lab1chatbubble),
                    contentDescription = "Speech Bubble",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(270.dp)
                        .absoluteOffset(70.dp))
                Box(modifier = Modifier
                    .padding(12.dp)
                    .matchParentSize()
                    .absoluteOffset(85.dp, 70.dp)) {
                    Text(if (toggleInformation) generateRandomInfo else generateRandomInfo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        } else {
            Spacer(modifier = Modifier.height(270.dp))
        }

        Image(painter = painterResource(R.drawable._00505_hampusandersson),
            contentDescription = "Hampus Andersson",
            modifier = Modifier.clip(RoundedCornerShape(40.dp)))

        Button(onClick = {
            showInformation = true
            toggleInformation = !toggleInformation
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.customblue),
                contentColor = Color.White)) {
            if (!showInformation) {
                Text("Ask me!")
            } else {
                Text(text = "Ask me again!")
            }
        }
    }
}

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}
