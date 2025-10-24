package com.tajimz.zigzag.auth;

import android.os.Bundle;
import android.util.Patterns;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.tajimz.zigzag.databinding.ActivitySignupBinding;
import com.tajimz.zigzag.helper.BaseActivity;
import com.tajimz.zigzag.helper.CONSTANTS;

public class SignupActivity extends BaseActivity {
    private ActivitySignupBinding binding;
    private String name, email, pass, conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupEdge();




    }
    private void setupEdge(){
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private boolean isValidInput(){

        name = gettext(binding.edName);
        email = gettext(binding.edMail);
        pass = gettext(binding.edPass);
        conPass = gettext(binding.edConPass);

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()|| conPass.isEmpty()){
            toast("Input all fields");
            return false;

        }

        if (name.length() < 3 || name.length() > 50){
            toast("Enter a valid name (3–50 characters)");
            return false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            toast("Enter a valid email");
            return false;
        }

        if (!pass.equals(conPass)){
            toast("Password not matched");
            return false;
        }
        if (!isPass(pass)){
            toast(CONSTANTS.passCriteria);
            return false;
        }

        String verified = binding.tvVerify.getText().toString().trim();
        if (!"verified".equals(verified)) {
            toast("Please verify your email");
            return false;
        }

        if (!binding.chkBox.isChecked()){
            toast("You must agree to our privacy policy to continue");
            return false;
        }

        return true;






    }








}