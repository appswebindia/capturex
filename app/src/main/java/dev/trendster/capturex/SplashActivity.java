package dev.trendster.capturex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.webicaso.matkaapp.MainActivity;
import com.webicaso.matkaapp.Models.User;
import com.webicaso.matkaapp.Utils.Utils;
import com.webicaso.matkaapp.activities.ui.login.LoginViewModel;
import com.webicaso.matkaapp.databinding.ActivityLoginBinding;
import com.webicaso.matkaapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User user = Utils.getUser(this);
        Log.d(Utils.TAG , user.toString());
        if(user.getId() != 0){//Logged in
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this , RegisterActivity.class);
            startActivity(intent);
        }

    }
}
