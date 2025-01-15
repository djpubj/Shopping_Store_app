package com.test.begin1.ui.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.begin1.navigation.ScreenC
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.ui.viewmodels.AddItemViewmodel

@Composable
fun AddItem(navController: NavController) {

    val viewModel = viewModel(modelClass = AddItemViewmodel::class.java)
    val contentResolver = LocalContext.current.contentResolver
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var isPriceValid by remember { mutableStateOf(true) }
    var isTitleValid by remember { mutableStateOf(true) }
    var isDescriptionValid by remember { mutableStateOf(true) }
    var isImageValid by remember { mutableStateOf(true) }
    val context = LocalContext.current // Get the context here

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val bitmap =
                contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it) }
            imageBitmap = bitmap
        }
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF42A5F5))
                .padding(16.dp)
        ) {
            Text(
                text = "Add item",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1565C0)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Add Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                if (imageBitmap != null) {
                    Image(
                        bitmap = imageBitmap!!.asImageBitmap(),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { imagePickerLauncher.launch("image/*") }
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add Image",

                        modifier = Modifier
                            .size(48.dp)
                            .clickable { imagePickerLauncher.launch("image/*") }
                    )
                }
            }
            if (!isImageValid) {
                Text(
                    text = "Image is required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
            ) {
                Text(text = "Add Image")
            }

            // Title
            TextField(
                value = title,
                onValueChange = {
                    title = it
                    isTitleValid = title.isNotBlank()
                },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                isError = !isTitleValid,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
            if (!isTitleValid) {
                Text(
                    text = "Title is required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Description
            TextField(
                value = description,
                onValueChange = {
                    description = it
                    isDescriptionValid = description.isNotBlank()
                },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                isError = !isDescriptionValid,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
            if (!isDescriptionValid) {
                Text(
                    text = "Description is required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Price
            TextField(
                value = price,
                onValueChange = {
                    price = it
                    isPriceValid = price.isNotBlank() && price.toDoubleOrNull() != null
                },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = !isPriceValid,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
            if (!isPriceValid) {
                Text(
                    text = "Valid price is required",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Submit Button
            Button(
                onClick = {
                    isImageValid = imageBitmap != null
                    isTitleValid = title.isNotBlank()
                    isDescriptionValid = description.isNotBlank()
                    isPriceValid = price.isNotBlank() && price.toDoubleOrNull() != null

                    if (isImageValid && isTitleValid && isDescriptionValid && isPriceValid) {
                        viewModel.addCard(
                            Card(
                                bitmap = imageBitmap,
                                title = title,
                                discription = description,
                                price = price.toDoubleOrNull() ?: 0.0
                            )
                        )
                        Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show()
                        navController.navigate(ScreenC)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF44336),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit")
            }
        }
    }
}



