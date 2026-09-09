package com.example.lab_activity_9.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(postsVm: PostsViewModel, themeVm: ThemeViewModel) {

    val posts by postsVm.posts.collectAsStateWithLifecycle()
    val darkTheme by themeVm.isDarkTheme.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(12.dp))
            
            // Name - Slightly bigger
            Text(
                text = "Arl Encarguez",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            // Username - with @
            Text(
                text = "@aencarguez",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(Modifier.height(4.dp))
            
            // University
            Text(
                text = "Liceo de Cagayan University",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            // Email
            Text(
                text = "aencarguez89245@liceo.edu.ph",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            
            Spacer(Modifier.height(16.dp))
            
            // Post Count - below Email
            Text(
                text = "${posts.size} posts",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(Modifier.height(20.dp))
            
            // Horizontal Divider - below post count
            HorizontalDivider(thickness = 1.dp)
            
            // Dark theme switch row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Switch on the Left
                Switch(
                    checked = darkTheme,
                    onCheckedChange = { themeVm.onThemeChanged(it) }
                )
                Spacer(Modifier.width(12.dp))
                // Text on the Right
                Text(
                    text = "Dark theme",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Page Number - Bottom Right, slightly smaller
        Text(
            text = "Arl Encarguez Page 11 of 16",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp,
            color = Color.Gray
        )
    }
}
