package com.test.begin1.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Entity.Cart
import com.test.begin1.ui.viewmodels.HomeViewmodel

@Composable
fun Searchitem(card: Card, viewModel: HomeViewmodel) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF81D4FA), Color.White)
                )
            )
            .shadow(elevation = 0.1.dp, shape = RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Image
            Image(
                bitmap = card.bitmap!!.asImageBitmap(),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "Selected Image"
            )

            // Title Text
            Text(
                text = card.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Price Text
            Text(
                text = "\u20B9${card.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.SemiBold
            )

            // Button
            Button(
                onClick = {
                    viewModel.addCart(Cart(id = card.id, name = card.title, price = card.price))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFF44336))
            ) {
                Text(
                    text = "Add to Cart",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}