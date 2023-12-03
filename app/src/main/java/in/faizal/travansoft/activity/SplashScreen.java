package in.faizal.travansoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.faizal.travansoft.R;
import in.faizal.travansoft.utils.Preference;

public class SplashScreen extends AppCompatActivity {
    private TextView typerText;
    private String welcomeText = "Lorem ipsum dolor";
    private int charIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        decorBg();
        typerText = findViewById(R.id.typerText);
        animateText();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (Preference.isUserRegistered(SplashScreen.this)) {
                    // If registered, navigate to the login screen
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                } else {
                    // If not registered, navigate to the registration screen
                    startActivity(new Intent(SplashScreen.this, RegisterActivity.class));
                }
                finish();
            }
        }, 3000); // 3000 milliseconds (3 seconds)
    }

    private void animateText() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (charIndex <= welcomeText.length()) {
                    typerText.setText(welcomeText.substring(0, charIndex));
                    charIndex++;
                    handler.postDelayed(this, 100); // Adjust the delay for typing speed
                }
            }
        }, 1000); // Initial delay before typing starts
    }

    private void decorBg() {
        ImageView splashImageView = findViewById(R.id.ivSplash);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashImageView.startAnimation(fadeIn);
    }
}