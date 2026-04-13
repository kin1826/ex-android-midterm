package com.example.midterm_project.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_project.R
import com.example.midterm_project.adapter.ProductAdapter
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var adapter: ProductAdapter

    private val list = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mapping
        recyclerView = findViewById(R.id.recyclerView)
        btnAdd = findViewById(R.id.btnAdd)

        adapter = ProductAdapter(
            list = list,
            onEdit = { product ->
                // Mở màn hình Update
                val intent = Intent(this, UpdateActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            },
            onDelete = { product ->
                // Gọi hàm xóa
                showDeleteDialog(this) {
                    deleteProduct(product.id)
                }

            },
            onClick = { product ->
                // Mở màn hình Chi tiết
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // button add
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddNewActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // load data
        loadProducts()
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    private fun loadProducts() {
        ApiClient.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    list.clear()
                    response.body()?.let { list.addAll(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
            }
        })
    }

    private fun deleteProduct(id: Int) {
        ApiClient.api.deleteProduct(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    loadProducts()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("DELETE", t.message ?: "Unknown error")
            }
        })
    }

    fun showDeleteDialog(
        context: Context,
        onConfirm: () -> Unit
    ) {
        val dialog = android.app.AlertDialog.Builder(context)
            .setTitle("Xác nhận")
            .setMessage("Bạn có chắc muốn xoá sản phẩm này không?")
            .setPositiveButton("Xoá") { _, _ ->
                onConfirm()
            }
            .setNegativeButton("Huỷ", null)
            .create()

        dialog.show()

        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
            .setTextColor(android.graphics.Color.RED)
    }
}