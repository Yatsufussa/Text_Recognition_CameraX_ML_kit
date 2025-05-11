package de.yanneckreiss.mlkittutorial.ui.language

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LanguageSelectionScreen(onLanguageSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select your preferred language:")
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onLanguageSelected("ru") }) {
            Text("🇷🇺 Russian")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onLanguageSelected("ko") }) {
            Text("🇰🇷 Korean")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onLanguageSelected("en") }) {
            Text("🇺🇸 English")
        }
    }
}
