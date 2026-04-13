package com.example.midterm_project.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.midterm_project.R
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import retrofit2.*

class DetailActivity : AppCompatActivity() {

    private lateinit var product: Product
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        product = intent.getSerializableExtra("product") as Product

        val img = findViewById<ImageView>(R.id.imgDetail)
        val name = findViewById<TextView>(R.id.txtName)
        val desc = findViewById<TextView>(R.id.txtDesc)
        val price = findViewById<TextView>(R.id.txtPrice)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        // hiển thị dữ liệu
        name.text = product.productName
        desc.text = product.description
        price.text = product.price.toString()

        Glide.with(this)
            .load(product.image)
            .into(img)

        // EDIT
        btnEdit.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        // DELETE
        btnDelete.setOnClickListener {
            showDeleteDialog(this) {
                deleteProduct(product.id)
            }
        }
    }

    private fun deleteProduct(id: Int) {
        ApiClient.api.deleteProduct(id).enqueue(object : Callback<okhttp3.ResponseBody> {
            override fun onResponse(call: Call<okhttp3.ResponseBody>, response: Response<okhttp3.ResponseBody>) {
                Toast.makeText(this@DetailActivity, "Đã xoá", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Lỗi", Toast.LENGTH_SHORT).show()
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