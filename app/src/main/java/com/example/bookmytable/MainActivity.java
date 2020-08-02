package com.example.bookmytable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText emailId,password;
    private Button btnLogin;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailId=findViewById(R.id.login_emailid);
        password=findViewById(R.id.login_password);
        btnLogin=findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "btnLogin clicked", Toast.LENGTH_SHORT).show();
*/

                CreateAccount();
            }
        });

    }

    private void CreateAccount()
    {
        String email=emailId.getText().toString();
        String pass=password.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            emailId.setError("enter valid email-id");
            emailId.requestFocus();
        }

        if(TextUtils.isEmpty(pass) || isPasswordValid(pass) || pass.length()<8 )
        {
            password.setError("enter valid password");
            password.requestFocus();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateAccount(email, pass);
        }
    }

    private void ValidateAccount(String email,String pass)
    {

    }

    public static boolean isPasswordValid(String password)
    {
        String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if(password == null)
        {
            return false;
        }
        return matcher.matches();

    }
}
