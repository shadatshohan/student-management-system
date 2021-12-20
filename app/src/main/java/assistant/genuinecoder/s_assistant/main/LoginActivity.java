package assistant.genuinecoder.s_assistant.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.components.AppCompatPreferenceActivity;
import assistant.genuinecoder.s_assistant.main.components.GridAdapter;
import assistant.genuinecoder.s_assistant.main.profile.ProfileActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    private Button loginbutton;
    private EditText username,password;
    private TextView signupbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbutton=(Button) findViewById(R.id.loginbuttonId);
        signupbutton=(TextView) findViewById(R.id.signupbuttonId);

        username=(EditText) findViewById(R.id.usernameId);
        password=(EditText) findViewById(R.id.passwordId);

        loginbutton.setOnClickListener(this);
        signupbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String usernames=username.getText().toString();
        String passwords=password.getText().toString();
        if(view.getId()==R.id.signupbuttonId)
        {
            Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.loginbuttonId)
        {
            if(usernames.equals("")|| passwords.equals(""))
            {
                Toast.makeText(getApplicationContext(), "empty field", Toast.LENGTH_SHORT).show();
            }
            else {
                username.setText("");
                password.setText("");
                Boolean result = databaseHelper.findmatching(usernames, passwords);

                if (result == true) {
                    Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "password and username didn't match", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
}


