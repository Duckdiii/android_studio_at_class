package com.example.practiceday1;

import java.io.Serializable;

// Dùng Serializable để có thể gửi dữ liệu giữa các màn hình nếu cần
public class Phone implements Serializable {
    private String name;
    private String brand;
    private String price;
    private int img;
    private String description;
    private String colors;
    private String capacities;
    private String specs;
    private String offers;

    // Constructor (Hàm khởi tạo) phải khớp với thứ tự bạn nhập ở trang home_page
    public Phone(String name, String brand, String price, int img, String description,
                 String colors, String capacities, String specs, String offers) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.img = img;
        this.description = description;
        this.colors = colors;
        this.capacities = capacities;
        this.specs = specs;
        this.offers = offers;
    }

    // Các hàm Getter để lấy dữ liệu
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getPrice() { return price; }
    public int getImg() { return img; }
    public String getDescription() { return description; }
    public String getColors() { return colors; }
    public String getCapacities() { return capacities; }
    public String getSpecs() { return specs; }
    public String getOffers() { return offers; }
}