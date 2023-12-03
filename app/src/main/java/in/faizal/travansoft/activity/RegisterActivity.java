package in.faizal.travansoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import in.faizal.travansoft.R;
import in.faizal.travansoft.utils.Preference;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    EditText et_name, et_mobile, et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        registerButton = findViewById(R.id.btnRegister);
        et_name = findViewById(R.id.etName);
        et_mobile = findViewById(R.id.etMobile);
        et_email = findViewById(R.id.etEmail);
        et_password = findViewById(R.id.etPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from EditText fields
                String name = et_name.getText().toString();
                String mobile = et_mobile.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                // Validate the input fields
                if (TextUtils.isEmpty(name)) {
                    et_name.setError("Name is required");
                    return;
                }

                if (TextUtils.isEmpty(mobile)) {
                    et_mobile.setError("Mobile number is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    et_password.setError("Password is required");
                    return;
                }


                Preference.setUser(name, mobile, password,RegisterActivity.this,true);
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}