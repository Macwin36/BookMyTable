package com.example.bookmytable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private EditText uname,pass,email;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin=findViewById(R.id.signUpBtn);
        uname=findViewById(R.id.UserName);
        email=findViewById(R.id.userEmailId);
        pass=findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });



    }

    private void CreateAccount()
    {
        String emailid=email.getText().toString();
        String password=pass.getText().toString();
        String username=uname.getText().toString();


        if(TextUtils.isEmpty(username))
        {
            uname.setError("Enter valid user name");
            uname.requestFocus();
        }

        if(TextUtils.isEmpty(emailid) || emailValidator(emailid))
        {
            email.setError("Enter valid email-id");
            email.requestFocus();
        }

        if(TextUtils.isEmpty(password) || isPasswordValid(password) )
        {
            pass.setError("enter valid password");
            pass.requestFocus();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            CreateAccount(username,emailid, password);
        }
    }
    private void CreateAccount(String username,String emailid,String password)
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

    /**
     * validate your email address format. Ex-akhi@mani.com
     */
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}