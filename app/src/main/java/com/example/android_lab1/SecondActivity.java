package com.example.android_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText nameInput; // Поле для ввода имени

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Инициализация элементов интерфейса
        TextView greeting = findViewById(R.id.greetingView);
        nameInput = findViewById(R.id.nameEditText);
        Button sendNameBtn = findViewById(R.id.buttonActivity2);

        // Получение приветственного сообщения из Intent
        Intent data = getIntent();
        String greetingText = data.getStringExtra("GREETING_MSG");
        greeting.setText(greetingText); // Установка текста приветствия

        // Установка обработчика нажатия для кнопки
        sendNameBtn.setOnClickListener(v -> sendName(greetingText));
    }

    // Метод для отправки имени обратно в MainActivity
    private void sendName(String greeting) {
        String name = nameInput.getText().toString(); // Получение имени из поля ввода
        Intent result = new Intent();
        result.putExtra("NAME", name); // Передача имени
        result.putExtra("GREETING", greeting); // Передача приветствия
        setResult(RESULT_OK, result); // Установка результата
        finish(); // Завершение активности
    }
}
