package appdorks.tk.teamironchamps54thbranchrajendernagar;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button btn;
    private EditText editText;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userPassword = editText.getText().toString().trim();

                if (userPassword.isEmpty())
                {

                    /*hide the software keyboard when log in button is clicked*/
                    // check if no view has focus
                    View currenScreenView = MainActivity.this.getCurrentFocus();
                    if (currenScreenView != null)
                    {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(currenScreenView.getWindowToken(), 0);
                    }

                    // replace this Toast with a Snackbar
                    /*Toast.makeText(MainActivity.this, "please enter password first", Toast.LENGTH_SHORT).show();*/
                    Snackbar.make(v, "please enter a password", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else
                {
                    if (userPassword.equals("1248"))
                    {
                        // start new activity
                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                        finish();
                    }
                    else
                    {
                        /*Toast.makeText(MainActivity.this, "incorrect password", Toast.LENGTH_SHORT).show();*/
                        Snackbar.make(v, "incorrect password", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
            }
        });

    }
}
