package in.faizal.travansoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import in.faizal.travansoft.R;
import in.faizal.travansoft.utils.Preference;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    EditText et_mobile, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        loginBtn = findViewById(R.id.btnLogin);
        et_mobile = findViewById(R.id.etMobileLogin);
        et_password = findViewById(R.id.etPasswordLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from EditText fields
                String enteredMobile = et_mobile.getText().toString();
                String enteredPassword = et_password.getText().toString();

                // Retrieve saved values from SharedPreferences
                String savedMobile = Preference.getUSerMobile(LoginActivity.this);
                String savedPassword = Preference.getUserPassword(LoginActivity.this);
                Log.e("TAG", "onClick: "+savedMobile+";"+savedPassword );

                // Validate the input fields
                if (TextUtils.isEmpty(enteredMobile)) {
                    et_mobile.setError("Mobile number is required");
                    return;
                }

                if (TextUtils.isEmpty(enteredPassword)) {
                    et_password.setError("Password is required");
                    return;
                }

                // Validate mobile number and password
                if (enteredMobile.equals(savedMobile) && enteredPassword.equals(savedPassword)) {
                    // If validation successful, navigate to the home screen
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // Close the login activity
                } else {
                    // If validation fails, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid mobile number or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
