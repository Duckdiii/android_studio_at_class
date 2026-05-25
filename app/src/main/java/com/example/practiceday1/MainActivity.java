package com.example.practiceday1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import thư viện Log
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on Create: Màn hình đang được tạo");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvSignUp = findViewById(R.id.tvSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, home_page.class);
                startActivity(intent);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, resigter_page.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on Start: Màn hình bắt đầu xuất hiện");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "on Resume: Màn hình đã sẵn sàng tương tác");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "on Pause: Màn hình đang tạm dừng");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "on Stop: Màn hình đã biến mất hoàn toàn");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "on Restart: Màn hình đang được khởi động lại");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on Destroy: Màn hình đã bị hủy hoàn toàn");
    }
}