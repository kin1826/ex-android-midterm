package com.example.midterm_project.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.midterm_project.R
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    private lateinit var product: Product
    private lateinit var imgPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        product = intent.getSerializableExtra("product") as Product

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtDesc = findViewById<EditText>(R.id.edtDesc)
        val edtPrice = findViewById<EditText>(R.id.edtPrice)
        val edtImage = findViewById<EditText>(R.id.edtImage)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        imgPreview = findViewById(R.id.imgPreview)

        // Hiển thị dữ liệu cũ
        edtName.setText(product.productName)
        edtDesc.setText(product.description)
        edtPrice.setText(product.price.toString())
        edtImage.setText(product.image)
        
        // Hiển thị ảnh cũ ngay khi mở trang
        if (product.image.isNotEmpty()) {
            Glide.with(this)
                .load(product.image)
                .placeholder(R.drawable.bg_image_preview)
                .error(R.drawable.bg_image_preview)
                .into(imgPreview)
        }

        btnBack.setOnClickListener {
            finish()
        }

        // Tự động load ảnh mới khi người dùng dán link mới vào ô nhập
        edtImage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val url = s.toString().trim()
                if (url.isNotEmpty()) {
                    Glide.with(this@UpdateActivity)
                        .load(url)
                        .placeholder(R.drawable.bg_image_preview)
                        .error(R.drawable.bg_image_preview)
                        .into(imgPreview)
                } else {
                    imgPreview.setImageResource(R.drawable.bg_image_preview)
                }
            }
        })

        btnUpdate.setOnClickListener {
            updateProduct(
                product.id,
                edtName.text.toString(),
                edtDesc.text.toString(),
                edtPrice.text.toString(),
                edtImage.text.toString()
            )
        }
    }

    private fun updateProduct(
        id: Int,
        name: String,
        desc: String,
        price: String,
        image: String
    ) {
        ApiClient.api.updateProduct(id, name, desc, price, image)
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@UpdateActivity, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@UpdateActivity, "Lỗi server: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@UpdateActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun finish() {
        super.finish()
        // Bạn có thể comment dòng dưới nếu không có file anim
        // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}