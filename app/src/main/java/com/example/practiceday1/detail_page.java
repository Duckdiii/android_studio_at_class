package com.example.practiceday1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class detail_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        // 1. Ánh xạ các Views từ XML
        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView tvName = findViewById(R.id.tvNameDetail);
        TextView tvPrice = findViewById(R.id.tvPriceDetail);
        TextView tvDesc = findViewById(R.id.tvDescDetail);
        TextView tvSpecs = findViewById(R.id.tvSpecs);
        TextView tvTotal = findViewById(R.id.tvTotalDetail);
        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);

        // 2. Nhận dữ liệu từ Intent (Sửa lại key cho khớp với dữ liệu Phone)
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        int imageRes = getIntent().getIntExtra("image", 0);
        String desc = getIntent().getStringExtra("desc");
        String specs = getIntent().getStringExtra("specs");
        // Bạn có thể nhận thêm colors, capacities nếu muốn xử lý logic chọn lựa

        // 3. Hiển thị dữ liệu lên giao diện
        tvName.setText(name);
        tvPrice.setText(price);
        tvDesc.setText(desc);
        tvSpecs.setText(specs);
        tvTotal.setText(price); // Tạm thời tổng tiền bằng giá sản phẩm

        // Dùng Glide để load ảnh sản phẩm
        Glide.with(this)
                .load(imageRes)
                .placeholder(android.R.drawable.ic_menu_gallery) // Ảnh mặc định nếu lỗi
                .into(imgDetail);

        // 4. Xử lý các sự kiện nút bấm

        // Nút quay lại trang chủ
        btnBack.setOnClickListener(v -> finish());

        // Nút Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm " + name + " vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });

        // Nút Mua ngay
        btnBuyNow.setOnClickListener(v -> {
            Toast.makeText(this, "Đang chuyển đến trang thanh toán...", Toast.LENGTH_SHORT).show();
            // Ở đây bạn có thể mở Activity thanh toán nếu có
        });
    }
}