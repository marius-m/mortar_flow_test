package lt.markmerkk.mortar_flow;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mortar.MortarScope;
import mortar.bundler.BundleService;
import mortar.bundler.BundleServiceRunner;

import static mortar.MortarScope.buildChild;
import static mortar.MortarScope.findChild;

public class MainActivity extends AppCompatActivity {

    //region Life-cycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override public Object getSystemService(String name) {
        MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
        if (activityScope == null) {
            activityScope = buildChild(getApplicationContext()) //
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(MainPresenter.class.getName(), new MainPresenter())
                    .build(getScopeName());
        }

        return activityScope.hasService(name) ? activityScope.getService(name) : super.getSystemService(name);
    }

    @Override protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }
        super.onDestroy();
    }

    private String getScopeName() {
        return getClass().getName();
    }

    //endregion

}
