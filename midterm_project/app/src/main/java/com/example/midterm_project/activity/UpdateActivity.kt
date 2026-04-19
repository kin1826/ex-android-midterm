package com.example.midterm_project.activity

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("product", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("product") as? Product
        }

        if (product == null) {
            finish()
            return
        }

        setContent {
            MaterialTheme {
                UpdateProductScreen(
                    product = product,
                    onBack = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(product: Product, onBack: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(product.productName) }
    var desc by remember { mutableStateOf(product.description) }
    var price by remember { mutableStateOf(product.price.toString()) }
    var imageUrl by remember { mutableStateOf(product.image) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cập nhật sản phẩm") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Tên sản phẩm") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text("Mô tả") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { 
                    if (it.all { char -> char.isDigit() }) {
                        price = it
                    }
                },
                label = { Text("Giá") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Link ảnh") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (name.isEmpty() || desc.isEmpty() || price.isEmpty() || imageUrl.isEmpty()) {
                        Toast.makeText(context, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show()
                    } else {
                        ApiClient.api.updateProduct(product.id, name, desc, price, imageUrl)
                            .enqueue(object : Callback<ResponseBody> {
                                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                                        onBack()
                                    }
                                }
                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                    Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show()
                                }
                            })
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("LƯU THAY ĐỔI")
            }
        }
    }
}