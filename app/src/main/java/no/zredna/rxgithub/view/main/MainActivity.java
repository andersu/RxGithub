package no.zredna.rxgithub.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.router.Router;
import no.zredna.rxgithub.router.RouterImpl;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG = "MainActivity";

    private Router router;

    @BindView(R.id.editTextUsername)
    EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        router = new RouterImpl();
    }

    @OnClick(R.id.buttonGitHubInformation)
    public void getGitHubInformation() {
        String username = editTextUsername.getText().toString();

        router.goToGitHubInformationActivity(this, username);
    }
}
