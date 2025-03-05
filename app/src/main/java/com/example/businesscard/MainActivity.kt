package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // color = MaterialTheme.colorScheme.background
                    color = Color.Yellow
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sahar Ramezani Jolfaei",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Kotlin Programmer",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
        ContactInfo()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedComputer()
    }
}

@Composable
fun ContactInfo() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ContactRow(R.drawable.baseline_local_phone_24, "3165264589")
        ContactRow(R.drawable.baseline_alternate_email_24, "sahar.ramezani@unige.com")
        ContactRow(R.drawable.baseline_account_circle_24, "https://github.com/SaharRamezani")  // Use custom drawable
    }
}


@Composable
fun ContactRow(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun AnimatedComputer() {
    var colorIndex by remember { mutableStateOf(0) }
    val colors = listOf(Color.Green, Color.Red, Color.Blue)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)  // Change color every second
            colorIndex = (colorIndex + 1) % colors.size
        }
    }

    Canvas(
        modifier = Modifier
            .width(100.dp)
            .height(80.dp)
    ) {
        // Draw computer screen
        drawRoundRect(
            color = colors[colorIndex],
            size = size.copy(width = size.width * 0.9f, height = size.height * 0.6f),
            topLeft = androidx.compose.ui.geometry.Offset(x = size.width * 0.05f, y = 0f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f, 8f)
        )
        // Draw computer base
        drawRoundRect(
            color = Color.Black,
            size = size.copy(width = size.width * 0.4f, height = size.height * 0.1f),
            topLeft = androidx.compose.ui.geometry.Offset(
                x = size.width * 0.3f,
                y = size.height * 0.7f
            )
        )
        // Draw computer stand
        drawRoundRect(
            color = Color.Black,
            size = size.copy(width = size.width * 0.05f, height = size.height * 0.1f),
            topLeft = androidx.compose.ui.geometry.Offset(
                x = size.width * 0.475f,
                y = size.height * 0.6f
            )
        )
    }
}
