package appdorks.tk.teamironchamps54thbranchrajendernagar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button mBtnLogIn;
    private EditText mEtPassword;
    private String strUserPassword;

    public static final String TAG = "MainActivity";
    public static final int REQUEST_CODE_CAMERA = 1;
    public static final int REQUEST_CODE_INTERNET = 2;
    public static final int REQUEST_CODE_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLogIn = findViewById(R.id.btn_app_entry_password);
        mEtPassword = findViewById(R.id.et_app_entry_password);

        //check if the phone has a hardware camera
        if (!checkCameraHardware(this))
        {
            Log.e(TAG, "onCreate: camera not found error toast shown");
            Toast.makeText(this, "camera hardware not found! please check the device.", Toast.LENGTH_SHORT).show();
        }

        mBtnLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                strUserPassword = mEtPassword.getText().toString().trim();

                if (strUserPassword.isEmpty())
                {

                    /*hide the software keyboard when log in button is clicked*/
                    // check if no view has focus
                    View currentFocus = MainActivity.this.getCurrentFocus();
                    if (currentFocus != null)
                    {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);

                        Log.e(TAG, "onClick: password field empty, keyboard hidden for Snackbar");

                    }

                    /*replace this Toast with a Snackbar
                    Toast.makeText(MainActivity.this, "please enter password first", Toast.LENGTH_SHORT).show();*/
                    Snackbar.make(v, "please enter a password", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    Log.e(TAG, "onClick: password field empty, Snackbar shown");
                }
                else
                {
                    if (strUserPassword.equals("1248"))
                    {
                        Log.i(TAG, "onClick: password correct, starting dashboard activity");
                        // start new activity
                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                        finish();
                    }
                    else //the password entered was incorrect
                    {
                        /*hide the software keyboard when log in button is clicked*/

                        // check if no view has focus
                        View currentFocus = MainActivity.this.getCurrentFocus();
                        if (currentFocus != null)
                        {
                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);

                            Log.e(TAG, "onClick: password incorrect, keyboard hidden for snackbar");
                        }

                        /*Toast.makeText(MainActivity.this, "incorrect password", Toast.LENGTH_SHORT).show();*/
                        Snackbar.make(v, "incorrect password", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        Log.e(TAG, "onClick: password incorrect, snackbar shown");
                    }
                }
            }
        });

    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context)
    {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            // this device has a camera, toast the number of cameras to the user -- commented
            /*Toast.makeText(context, Camera.getNumberOfCameras() + " cameras found " , Toast.LENGTH_LONG).show();*/
            // check if the phone has camera permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                // camera permission is not granted
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
                Log.i(TAG, "checkCameraHardware: permission prompt for CAMERA");
            }
            else
            {
                // Permission has already been granted
                Log.i(TAG, "checkCameraHardware: camera permission is already granted");
            }

            return true;
        }
        else
        {
            // no camera on this device
            Log.e(TAG, "checkCameraHardware: camera hardware not found on the device!");
            return false;
        }
    }

    boolean backPressedTwice = false;

    @Override
    public void onBackPressed()
    {
        if (backPressedTwice)
        {
            super.onBackPressed();
            return;
        }

        this.backPressedTwice = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                backPressedTwice = false;
            }
        }, 2000);
    }
}
