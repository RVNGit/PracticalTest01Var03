package ro.pub.cs.systems.eim.practicaltest01var03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    Button correct, incorrect;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        correct = findViewById(R.id.correct_button);
        incorrect = findViewById(R.id.incorrect_button);
        output = findViewById(R.id.output);

        output.setText(getIntent().getStringExtra(Constants.OUTPUT));

        correct.setOnClickListener(v -> {
            setResult(RESULT_OK, null);
            finish();
        });

        incorrect.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });
    }
}