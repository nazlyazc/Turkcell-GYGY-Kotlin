package com.example.kullanici_listesi_uygulamasi_hw.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable // Tıklanabilirlik için gerekli
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kullanici_listesi_uygulamasi_hw.data.model.User

@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit // BONUS: Tıklama olayını yukarı iletmek için ekledik
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // BONUS: Kartı tıklanabilir hale getirdik
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // SOL BÖLÜM: Dairesel Avatar (Box + Text)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.name.take(1),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // SAĞ BÖLÜM: Column içinde isim, email ve telefon
            Column {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = user.phone,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}