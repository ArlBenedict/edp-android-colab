package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFC107)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "WELCOME",
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .border(
                            5.dp,
                            Color(0xFF2962FF),
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Arl Encarguez",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1)
                )

                Text(
                    text = "BSIT Student",
                    fontSize = 18.sp,
                    color = Color(0xFF42A5F5)
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(20.dp))

                ContactRow(
                    emoji = "📞",
                    text = "0951-887-0297"
                )

                ContactRow(
                    emoji = "✉️",
                    text = "aencarguez89245@liceo.edu.ph"
                )

                ContactRow(
                    emoji = "📍",
                    text = "Iponan , Cagayan de Oro City, Philippines"
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2962FF)
                    )
                ) {
                    Text(
                        text = "Contact Me",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ContactRow(
    emoji: String,
    text: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F9FF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = emoji,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontSize = 17.sp,
                color = Color(0xFF37474F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}