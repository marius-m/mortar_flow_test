package lt.markmerkk.mortar_flow;

import android.app.Application;

import mortar.MortarScope;

/**
 * @author mariusmerkevicius
 * @since 2016-11-17
 */

public class TestApplication extends Application {

    private MortarScope rootScope;

    @Override public Object getSystemService(String name) {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope().build("Root");
        }
        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }

}
