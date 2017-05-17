package eprit.tn.cowbot.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eprit.tn.cowbot.Entity.User.UserInput;
import eprit.tn.cowbot.Entity.User.UserOutput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.UserService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button Sign_in;
    private CompositeDisposable mCompositeDisposable;
    private UserService userService;
    public SharedPreferences sharedPreferencesLogin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VerifUserConnected();


        Sign_in = (Button) findViewById(R.id.btn_login);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);

        InitializeUtils();
        Login();

    }

    public void InitializeUtils() {

        mCompositeDisposable = new CompositeDisposable();
        userService = ServiceFactory.createRetrofitService(UserService.class, URLS.EndPoint);
    }

    public void Login() {
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Check your email or password ", Toast.LENGTH_SHORT).show();
                else
                mCompositeDisposable.add(userService.SignIn(new UserOutput(email.getText().toString(), password.getText().toString()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleLogin, this::handleLoginError));
            }

            private void handleLoginError(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Check your email or password", Toast.LENGTH_SHORT).show();
            }

            private void handleLogin(UserInput user) {

                sharedPreferencesLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
                editor.putInt("id", user.getId());
                editor.putString("lat", user.getLatitude());
                editor.putString("lat", user.getLongitude());
                editor.putString("name", user.getFirstName());
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Welcome Mr " + user.getFirstName() + " \n on your cowbot Application", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    private void VerifUserConnected() {
        sharedPreferencesLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
        Integer idUser = sharedPreferencesLogin.getInt("id", 0);


        if (idUser != 0) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Re-welcome Mr " + sharedPreferencesLogin.getString("name", ""), Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
