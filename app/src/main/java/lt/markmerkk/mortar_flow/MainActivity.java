package lt.markmerkk.mortar_flow;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import flow.Flow;
import mortar.MortarScope;
import mortar.bundler.BundleService;
import mortar.bundler.BundleServiceRunner;

import static lt.markmerkk.mortar_flow.DaggerService.createComponent;
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
    protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .dispatcher(new BasicDispatcher(this))
                .defaultKey(new MainScreen("test_title"))
                .keyParceler(new BasicParceler())
                .install();
        super.attachBaseContext(baseContext);
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
                    .withService(DaggerService.SERVICE_NAME, createComponent(MainPresenterDagger.Component.class))
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

    @Override public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    //endregion

}
