package com.example.midterm_project.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midterm_project.R
import com.example.midterm_project.model.Product

class ProductAdapter(
    private val list: List<Product>,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit,
    private val onClick: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.imgProduct)
        val name = view.findViewById<TextView>(R.id.txtName)
        val price = view.findViewById<TextView>(R.id.txtPrice)
        val menu = view.findViewById<ImageView>(R.id.btnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = list[position]

        holder.name.text = p.productName
        holder.price.text = formatPrice(p.price) + " đ"

        Glide.with(holder.itemView.context)
            .load(p.image)
            .into(holder.img)

        holder.menu.setOnClickListener {

            val popup = PopupMenu(holder.itemView.context, holder.menu)
            popup.inflate(R.menu.popup_menu)

            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_edit -> {
                        onEdit(p)
                        true
                    }
                    R.id.menu_delete -> {
                        onDelete(p)
                        true
                    }
                    else -> false
                }
            }

            popup.show()
        }

        holder.itemView.setOnClickListener {
            onClick(p)
        }
    }

    fun formatPrice(price: Int): String {
        val formatter = java.text.NumberFormat.getInstance(java.util.Locale("vi", "VN"))
        return formatter.format(price)
    }
}