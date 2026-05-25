package com.example.practiceday1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TechStore.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột
    public static final String TABLE_NAME = "phones";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_SPECS = "specs";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Câu lệnh tạo bảng
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BRAND + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_IMAGE + " INTEGER, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_SPECS + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Hàm thêm sản phẩm vào database
    public void addPhone(String name, String brand, String price, int image, String desc, String specs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BRAND, brand);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_SPECS, specs);

        db.insert(TABLE_NAME, null, cv);
    }
    public List<Phone> getAllPhones() {
        List<Phone> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Chỉ cần câu lệnh lấy tất cả, không cần lọc if/else ở đây
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Phone p = new Phone(
                        cursor.getString(1), // Name
                        cursor.getString(2), // Brand
                        cursor.getString(3), // Price
                        cursor.getInt(4),    // Image
                        cursor.getString(5), // Desc
                        "", "",              // Colors, Capacity (tạm để trống)
                        cursor.getString(6), // Specs
                        ""                   // Offers
                );
                list.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<Phone> getFilteredPhones(String brand, String ram, String rom, String priceRange) {
        List<Phone> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        StringBuilder query = new StringBuilder("SELECT * FROM " + TABLE_NAME + " WHERE 1=1");

        if (brand != null && !brand.isEmpty()) {
            query.append(" AND brand = '").append(brand).append("'");
        }

        if (ram != null && !ram.isEmpty()) {
            String ramValue = ram.replaceAll("[^0-9]", ""); // Chỉ lấy số
            query.append(" AND ram = ").append(ramValue);
        }

        if (rom != null && !rom.isEmpty()) {
            String romValue = rom.replaceAll("[^0-9]", ""); // Chỉ lấy số
            query.append(" AND rom = ").append(romValue);
        }

        if (priceRange != null && !priceRange.isEmpty()) {
            String cleanPriceSql = "CAST(REPLACE(REPLACE(price, '.', ''), 'đ', '') AS INTEGER)";

            if (priceRange.contains("Dưới 10tr")) {
                query.append(" AND ").append(cleanPriceSql).append(" < 10000000");
            } else if (priceRange.contains("10tr - 20tr")) {
                query.append(" AND ").append(cleanPriceSql).append(" BETWEEN 10000000 AND 20000000");
            } else if (priceRange.contains("Trên 20tr")) {
                query.append(" AND ").append(cleanPriceSql).append(" > 20000000");
            }
        }

        Cursor cursor = db.rawQuery(query.toString(), null);

        if (cursor.moveToFirst()) {
            do {
                Phone phone = new Phone(
                        cursor.getString(1), // name
                        cursor.getString(2), // brand
                        cursor.getString(3), // price
                        cursor.getInt(4),    // image
                        cursor.getString(5), // description
                        "", "",              // colors, capacities (tạm để trống)
                        cursor.getString(6), // specs
                        ""                   // offers
                );
                list.add(phone);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}