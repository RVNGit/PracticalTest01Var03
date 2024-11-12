package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    Button plus, minus, navigate;
    EditText input1, input2;
    TextView output;

    private int serviceStatus = Constants.SERVICE_STOPPED;
    private final IntentFilter intentFilter = new IntentFilter();

    private boolean plusClicked = false;
    private boolean minusClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        plus = findViewById(R.id.plus_button);
        minus = findViewById(R.id.minus_button);
        navigate = findViewById(R.id.navigate_to_secondary_activity_button);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        output = findViewById(R.id.output);
        //if either value is not a number, print a toast message
        plus.setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(input1.getText().toString());
                int b = Integer.parseInt(input2.getText().toString());
                plusClicked = true;
                // print a + b = (a+b)
                output.setText(String.valueOf(a) + " + " + String.valueOf(b) + " = " + String.valueOf(a + b));
                startServiceIfNeeded();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });

        minus.setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(input1.getText().toString());
                int b = Integer.parseInt(input2.getText().toString());
                minusClicked = true;
                output.setText(String.valueOf(a) + " - " + String.valueOf(b) + " = " + String.valueOf(a - b));
                startServiceIfNeeded();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });

        navigate.setOnClickListener(v -> {
            // navigate to secondary activity
            Intent intent = new Intent(this, PracticalTest01Var03SecondaryActivity.class);
            intent.putExtra(Constants.OUTPUT, output.getText().toString());
            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        });

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.INPUT1, input1.getText().toString());
        savedInstanceState.putString(Constants.INPUT2, input2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String input1Value, input2Value;

        if (savedInstanceState.containsKey(Constants.INPUT1)) {
            input1Value = savedInstanceState.getString(Constants.INPUT1);
            input1.setText(input1Value);
        } else {
            input1Value = "0";
            input1.setText(input1Value);
        }

        if (savedInstanceState.containsKey(Constants.INPUT2)) {
            input2Value = savedInstanceState.getString(Constants.INPUT2);
            input2.setText(input2Value);
        } else {
            input2Value = "0";
            input2.setText(input2Value);
        }

        // Afișăm valorile restaurate într-un Toast
        Toast.makeText(this, "Restored values: input1 = " + input1Value + ", input2 = " + input2Value, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "The activity returned with result CORRECT", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "The activity returned with result INCORRECT", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var03Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private void startServiceIfNeeded() {
        if ((plusClicked || minusClicked) && serviceStatus == Constants.SERVICE_STOPPED) {
            Intent intent = new Intent(this, PracticalTest01Var03Service.class);
            intent.putExtra(Constants.INPUT1, input1.getText().toString());
            intent.putExtra(Constants.INPUT2, input2.getText().toString());
            startService(intent);
            serviceStatus = Constants.SERVICE_STARTED;
        }
    }
}