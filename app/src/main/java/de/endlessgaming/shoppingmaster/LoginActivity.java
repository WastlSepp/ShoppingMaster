package de.endlessgaming.shoppingmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "";
    private GoogleSignInClient mSignInClient;
    //Drive Options
    //private Object Drive;
    //private static final int RC_DRIVE_PERMS = ;
    //Some Button defines
    SignInButton signInButton;
    Button logoutBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Logout Override
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        //Login Override
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launches the sign in flow, the result is returned in onActivityResult
                Intent intent = mSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        //Doesnt work right now #Todo: Create return when u already logged in.
        if (mSignInClient != null) {
            //If already in bring em back in the meanwhile
            Toast.makeText(getApplicationContext(), "Already logged #Debug", Toast.LENGTH_LONG).show();
            //finish();  Temp Disabled for Logout Tests
        }

        //Call Google API
        final GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestScopes(Drive.SCOPE_FILE) //Temp. Disabled // Save
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, options);
    }

    //Logout Function
    private void signOut() {
        mSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Outlogged",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }

    //Start Token task -> If token task doesn't fail log em in
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    //ADDED TOKEN VERIFICATION
                    handleSignInResult(task);
            if (task.isSuccessful()) {
                // Sign in succeeded, proceed with account
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                finish();
            } else {
                //Error Handeling
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
            }
        }
    }
    //Get token and soon post to Server and verify it.
    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            //Debug Tokenausgabe
            Toast.makeText(getApplicationContext(),idToken,Toast.LENGTH_LONG).show();
            // TODO(developer): send ID Token to server and validate
            //Token kommt raus -> Validate & do it
            //updateUI(account); Update shit
        } catch (ApiException e) {
            Log.w(TAG, "handleSignInResult:error", e);
            Toast.makeText(getApplicationContext(),"No Token received",Toast.LENGTH_LONG).show();
            finish(); //Bring him back to Mainscreen
            //updateUI(null); Update shit
        }
    }
    //Todo:Create Account in Database if it not already exists.
    //Todo:Cleanup Code and Check for Security Holes.
    /*This is where u can get stored Accounts of Android
    private void createDriveFile() {
        // Get currently signed in account (or null)
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Synchronously check for necessary permissions
        if (!GoogleSignIn.hasPermissions(account, Drive.SCOPE_FILE)) {
            // Note: this launches a sign-in flow, however the code to detect
            // the result of the sign-in flow and retry the API call is not
            // shown here.
            GoogleSignIn.requestPermissions(this, RC_DRIVE_PERMS,
                    account, Drive.SCOPE_FILE);
            return;
        }

        DriveResourceClient client = Drive.getDriveResourceClient(this, account);
        client.createContents()
                .addOnCompleteListener(new OnCompleteListener<DriveContents>() {
                    @Override
                    public void onComplete(@NonNull Task<DriveContents> task) {
                        // ...
                    }
                });
    }
    */
}
