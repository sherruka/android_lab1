package com.example.android_lab1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1; // Код запроса для SecondActivity
    private EditText greetingInput; // Поле для ввода приветствия
    private TextView mainGreeting; // Текстовое поле для отображения приветствия

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов интерфейса
        greetingInput = findViewById(R.id.greetingEditText);
        mainGreeting = findViewById(R.id.textView);
        Button openActivityBtn = findViewById(R.id.buttonActivity1);

        // Установка обработчика нажатия для кнопки
        openActivityBtn.setOnClickListener(v -> launchSecondActivity());
    }

    // Метод для запуска SecondActivity и передачи приветственного сообщения
    private void launchSecondActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        String greeting = greetingInput.getText().toString(); // Получение текста из поля ввода
        intent.putExtra("GREETING_MSG", greeting); // Передача приветствия в Intent
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE); // Запуск активити с ожиданием результата
    }

    // Обработка результата из SecondActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Проверка кода запроса и результата
        switch (requestCode) {
            case (SECOND_ACTIVITY_REQUEST_CODE):
                if (resultCode == RESULT_OK && data != null) {
                    String receivedName = data.getStringExtra("NAME"); // Получение имени из Intent
                    String receivedGreeting = data.getStringExtra("GREETING"); // Получение приветствия из Intent
                    updateGreeting(receivedName, receivedGreeting); // Обновление приветствия с добавлением имени
                }
                break;
        }
    }

    // Метод для обновления текста приветствия с именем
    private void updateGreeting(String name, String greeting) {
        String fullGreeting = greeting + " " + name;
        mainGreeting.setText(fullGreeting); // Установка обновленного текста в TextView
        mainGreeting.setTextColor(Color.parseColor("#FFFF5722")); // Установка цвета текста
    }
}
