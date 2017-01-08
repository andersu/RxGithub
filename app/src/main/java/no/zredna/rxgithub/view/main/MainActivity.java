package no.zredna.rxgithub.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.presenter.MainPresenter;
import no.zredna.rxgithub.presenter.MainPresenterImpl;
import no.zredna.rxgithub.router.Router;
import no.zredna.rxgithub.router.RouterImpl;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private Router router;

    @BindView(R.id.editTextUsername)
    EditText editTextUsername;

    @BindView(R.id.buttonGitHubInformation)
    Button buttonGitHubInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        router = new RouterImpl();
        presenter = new MainPresenterImpl(this);
    }

    @OnClick(R.id.buttonGitHubInformation)
    public void getGitHubInformation() {
        String username = editTextUsername.getText().toString();

        router.goToGitHubInformationActivity(this, username);
    }

    @OnTextChanged(R.id.editTextUsername)
    public void onTextChanged(CharSequence text) {
        String newText = text.toString();
        presenter.onUsernameTextChanged(newText);
    }

    @Override
    public void enableButton() {
        buttonGitHubInformation.setEnabled(true);
    }

    @Override
    public void disableButton() {
        buttonGitHubInformation.setEnabled(false);
    }
}
