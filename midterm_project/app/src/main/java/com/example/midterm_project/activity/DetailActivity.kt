package com.example.midterm_project.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : ComponentActivity() {
    
    // Sử dụng state để Compose tự cập nhật giao diện khi dữ liệu thay đổi
    private var productState = mutableStateOf<Product?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Lấy thông tin sơ bộ truyền từ Intent
        val initialProduct = intent.getSerializableExtra("product") as Product
        productState.value = initialProduct

        setContent {
            MaterialTheme {
                val currentProduct by productState
                
                if (currentProduct != null) {
                    DetailScreen(
                        product = currentProduct!!,
                        onBack = { finish() },
                        onEdit = {
                            val intent = Intent(this, UpdateActivity::class.java)
                            intent.putExtra("product", currentProduct)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Mỗi khi quay lại màn hình này (ví dụ sau khi Sửa), gọi API để lấy dữ liệu mới nhất
        loadProductDetail(productState.value?.id ?: return)
    }

    private fun loadProductDetail(id: Int) {
        // Lưu ý: Nếu server chưa có API getProductById, bạn có thể lấy lại toàn bộ danh sách 
        // và tìm kiếm theo ID, hoặc viết thêm API ở server.
        // Ở đây tôi dùng lại getProducts và tìm đúng ID để đảm bảo dữ liệu luôn mới.
        ApiClient.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val updatedProduct = response.body()?.find { it.id == id }
                    if (updatedProduct != null) {
                        productState.value = updatedProduct
                    }
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {}
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(product: Product, onBack: () -> Unit, onEdit: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết sản phẩm") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = product.productName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(text = "${product.price} đ", fontSize = 20.sp, color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Mô tả:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = product.description, fontSize = 16.sp)

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onEdit,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("CHỈNH SỬA")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        ApiClient.api.deleteProduct(product.id).enqueue(object : Callback<okhttp3.ResponseBody> {
                            override fun onResponse(call: Call<okhttp3.ResponseBody>, response: Response<okhttp3.ResponseBody>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Đã xoá", Toast.LENGTH_SHORT).show()
                                    onBack()
                                }
                            }
                            override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {}
                        })
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("XOÁ")
                }
            }
        }
    }
}
