package com.example.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.turkcellintro.ui.theme.TurkcellIntroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TurkcellIntroTheme {
                // Scaffold artık arka plan rengini temaya göre yönetecek
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    // --- Dark Mode Durumu (Hafızada tutulur) ---
    var isDarkMode by remember { mutableStateOf(false) }

    // Temaya göre değişen renk tanımlamaları
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF8F9FA)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val subTextColor = if (isDarkMode) Color.LightGray else Color.Gray
    val softPink = Color(0xFFF8BBD0)
    val nameColor = if (isDarkMode) Color(0xFFFF80AB) else Color(0xFF880E4F)

    val skills = listOf("C, C#", "Python", "Arduino", "Proteus", "Matlab", "SQL/NoSQL", "İngilizce")

    // Tüm ekranı kaplayan ana kolon
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp, vertical = 40.dp), // Üstten biraz daha pay bıraktık
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dark Mode Switch Satırı
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isDarkMode) "Koyu Mod 🌙" else "Aydınlık Mod ☀️",
                    color = subTextColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Profil Fotoğrafı
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(softPink.copy(alpha = 0.3f))
                    .border(2.dp, nameColor.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = R.drawable.profil_foto,
                    contentDescription = "Profil Fotoğrafı",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // İsim ve Bilgiler
            Text(
                text = "Nazlı Yazıcı",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = nameColor
            )
            Text(
                text = "Eskişehir Teknik Üniversitesi",
                fontSize = 22.sp,
                color = subTextColor,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "Elektrik-Elektronik Mühendisi",
                fontSize = 18.sp,
                color = subTextColor,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Yetenekler",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                modifier = Modifier.align(Alignment.Start),
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Yetenek Listesi
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(skills) { skill ->
                    SkillItem(skillName = skill, color = softPink, isDarkMode = isDarkMode)
                }
            }
        }
    }
}

@Composable
fun SkillItem(skillName: String, color: Color, isDarkMode: Boolean) {
    // Karanlık modda kutucukların rengini biraz daha belirgin yapalım
    val cardColor = if (isDarkMode) Color(0xFF2C2C2C) else color
    val itemTextColor = if (isDarkMode) Color(0xFFFF80AB) else Color(0xFF880E4F)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = cardColor,
        shadowElevation = if (isDarkMode) 0.dp else 6.dp // Karanlık modda gölge yerine hafif renk farkı daha iyidir
    ) {
        Text(
            text = skillName,
            color = itemTextColor,
            modifier = Modifier.padding(14.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace
        )
    }
}