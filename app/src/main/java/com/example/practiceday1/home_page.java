package com.example.practiceday1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class home_page extends AppCompatActivity {
    MyDatabaseHelper dbHelper;
    List<Phone> phoneList;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        // 1. Khởi tạo Database
        dbHelper = new MyDatabaseHelper(this);

        // 2. Kiểm tra nếu DB trống thì mới thêm dữ liệu mẫu (để tránh trùng lặp mỗi khi mở app)
        phoneList = dbHelper.getAllPhones();
        if (phoneList.isEmpty()) {
            seedData(); // Hàm thêm dữ liệu mẫu
            phoneList = dbHelper.getAllPhones(); // Lấy lại sau khi thêm
        }

        // 3. Thiết lập GridView với dữ liệu từ SQLite
        GridView gridView = findViewById(R.id.gvProducts);
        adapter = new GridAdapter(this, phoneList);
        gridView.setAdapter(adapter);

        // 4. Sự kiện click (giữ nguyên logic cũ)
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Phone selectedPhone = phoneList.get(position);
            Intent intent = new Intent(home_page.this, detail_page.class);
            intent.putExtra("name", selectedPhone.getName());
            intent.putExtra("price", selectedPhone.getPrice());
            intent.putExtra("image", selectedPhone.getImg());
            intent.putExtra("desc", selectedPhone.getDescription());
            intent.putExtra("specs", selectedPhone.getSpecs());
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

// Đặt mục được chọn mặc định là Home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Đang ở Home rồi nên không cần làm gì
                return true;
            } else if (id == R.id.nav_category) {
                // Chuyển sang trang Danh mục
                startActivity(new Intent(home_page.this, CategoryActivity.class));
                overridePendingTransition(0, 0); // Xóa hiệu ứng chuyển trang để trông mượt hơn
                return true;
            } else if (id == R.id.nav_cart) {
                // Chuyển sang trang Giỏ hàng (nếu bạn đã có CartActivity)
                // startActivity(new Intent(home_page.this, CartActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                // Chuyển sang trang Profile
                return true;
            }

            return false;
        });
    }
    private void seedData() {
        dbHelper.addPhone("iPhone 15 Pro Max", "Apple", "29.990.000đ", R.drawable.phone_apple, "Mô tả iPhone...", "Chip A17 Pro");
        dbHelper.addPhone("iPhone 15 Pro", "Apple", "27.990.000đ", R.drawable.phone_apple, "Mô tả iPhone...", "Chip A17 Pro");
        dbHelper.addPhone("iPhone 15 Plus", "Apple", "22.990.000đ", R.drawable.phone_apple, "Mô tả iPhone...", "Camera 48MP");
        dbHelper.addPhone("iPhone 14 Pro Max", "Apple", "25.990.000đ", R.drawable.phone_apple, "Mô tả iPhone...", "Dynamic Island");
        dbHelper.addPhone("Samsung S24 Ultra", "Samsung", "26.500.000đ", R.drawable.phone_samsung, "Mô tả Samsung...", "Chip Snap Gen 3");
        dbHelper.addPhone("Samsung S24 Plus", "Samsung", "22.990.000đ", R.drawable.phone_samsung, "Mô tả Samsung...", "AMOLED 120Hz");
        dbHelper.addPhone("Samsung S24", "Samsung", "19.990.000đ", R.drawable.phone_samsung, "Mô tả Samsung...", "AMOLED 120Hz");
        dbHelper.addPhone("Samsung Z Fold5", "Samsung", "35.990.000đ", R.drawable.phone_samsung, "Mô tả Samsung...", "Foldable Display");
        dbHelper.addPhone("Xiaomi 14 Ultra", "Xiaomi", "22.990.000đ", R.drawable.phone_xiaomi, "Mô tả Xiaomi...", "Leica Camera");
        dbHelper.addPhone("Xiaomi 14 Pro", "Xiaomi", "19.990.000đ", R.drawable.phone_xiaomi, "Mô tả Xiaomi...", "Snapdragon 8 Gen 3");
        dbHelper.addPhone("Xiaomi 13T Pro", "Xiaomi", "14.990.000đ", R.drawable.phone_xiaomi, "Mô tả Xiaomi...", "120W HyperCharge");
        dbHelper.addPhone("Xiaomi Redmi Note 13 Pro", "Xiaomi", "9.990.000đ", R.drawable.phone_xiaomi, "Mô tả Xiaomi...", "200MP Camera");
        dbHelper.addPhone("OPPO Find X7 Ultra", "OPPO", "24.990.000đ", R.drawable.phone_oppo, "Mô tả OPPO...", "Hasselblad Camera");
        dbHelper.addPhone("OPPO Find X7", "OPPO", "19.990.000đ", R.drawable.phone_oppo, "Mô tả OPPO...", "LTPO AMOLED");
        dbHelper.addPhone("OPPO Reno11 Pro", "OPPO", "12.990.000đ", R.drawable.phone_oppo, "Mô tả OPPO...", "Portrait Camera");
        dbHelper.addPhone("OPPO A98", "OPPO", "8.990.000đ", R.drawable.phone_oppo, "Mô tả OPPO...", "67W SuperVOOC");
    }
}
