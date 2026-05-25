package com.example.practiceday1;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;



public class CategoryActivity extends AppCompatActivity {
    MyDatabaseHelper dbHelper;
    GridAdapter adapter;
    GridView gridView;
    ChipGroup chipGroupRam, chipGroupRom, chipGroupPrice;
    String selectedBrand = "";
    String selectedRam = "";
    String selectedRom = "";
    String selectedPrice = "";

    // Hàm thực hiện lọc tổng hợp
    private void applyFilters() {
        List<Phone> filteredList = dbHelper.getFilteredPhones(
                selectedBrand,
                selectedRam,
                selectedRom,
                selectedPrice
        );
        adapter = new GridAdapter(this, filteredList);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dbHelper = new MyDatabaseHelper(this);

        gridView = findViewById(R.id.gvCategoryProducts);
        chipGroupRam = findViewById(R.id.chipGroupRam);
        chipGroupRom = findViewById(R.id.chipGroupRom);
        chipGroupPrice = findViewById(R.id.chipGroupPrice);

        applyFilters();
        chipGroupRam.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = findViewById(checkedId);
            selectedRam = (chip != null) ? chip.getText().toString() : "";
            applyFilters(); // Gọi lọc mỗi khi có thay đổi
        });

        // Ví dụ xử lý khi chọn ROM
        chipGroupRom.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = findViewById(checkedId);
            selectedRom = (chip != null) ? chip.getText().toString() : "";
            applyFilters();
        });

        // Ví dụ xử lý khi chọn Giá
        chipGroupPrice.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = findViewById(checkedId);
            selectedPrice = (chip != null) ? chip.getText().toString() : "";
            applyFilters();
        });
    }
}
