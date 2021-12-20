package assistant.genuinecoder.s_assistant.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assistant.genuinecoder.s_assistant.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    public UserDetails Userdetails;
    DatabaseHelper databaseHelper;
    private Button signup,laterbutton;
    private EditText nametext,usertext,passwordtext,emailtext,contact,rpassword;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Userdetails=new UserDetails();
        databaseHelper=new DatabaseHelper(this);

        signup=(Button) findViewById(R.id.buttonsid);

        text=(TextView) findViewById(R.id.textid);

        nametext=(EditText) findViewById(R.id.namesId);
        usertext=(EditText) findViewById(R.id.usernamesId);
        passwordtext=(EditText) findViewById(R.id.passwordsId);
        emailtext=(EditText) findViewById(R.id.emailsId);
        contact=(EditText) findViewById(R.id.contactId);
        rpassword=(EditText) findViewById(R.id.RpasswordsId);

        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name=nametext.getText().toString();
        String username=usertext.getText().toString();
        String password=passwordtext.getText().toString();
        String contactt=contact.getText().toString();

        Userdetails.setName(name);
        Userdetails.setContact(contactt);
        Userdetails.setPassword(password);
        Userdetails.setUsername(username);
        String email="[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                "\\@"+
                "[A-Za-z0-9][a-zA-Z0-9\\-]{0,64}"+
                "("+
                "\\."+
                "(com)"+
                ")+";
        String emailt=emailtext.getText().toString();
        Userdetails.setEmail(email);

        String rrpassword=passwordtext.getText().toString();
        String RRpassword=rpassword.getText().toString();

        long value=databaseHelper.insert(Userdetails);

        if(value>0)
        {
            if(name.equals(" ") || username.equals("") || password.equals("") || emailt.equals("")|| contactt.equals("")|| RRpassword.equals("") )
            {
                Toast.makeText(getApplicationContext(), "please fill all the blank", Toast.LENGTH_SHORT).show();
            }
            else {

                Matcher matcher= Pattern.
                        compile(email).matcher(emailt);
                Matcher matcherr=Pattern.
                        compile(rrpassword).matcher(RRpassword);
                if(matcher.matches())
                {
                    Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_SHORT).show();
                    if(matcherr.matches()) {

                        nametext.setText("");
                        usertext.setText("");
                        passwordtext.setText("");
                        emailtext.setText("");
                        Toast.makeText(getApplicationContext(), "row is successfully insert " + value, Toast.LENGTH_SHORT).show();
                        text.setText("You have successfully signed in");
                        laterbutton.setText("GO back to log in page");
                        laterbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent INTENT = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(INTENT);
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(this, "password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                }

            }
        }

        else
        {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
        }





    }
}


