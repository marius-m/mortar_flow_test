package lt.markmerkk.mortar_flow;

import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import mortar.Presenter;
import mortar.ViewPresenter;

/**
 * @author mariusmerkevicius
 * @since 2016-11-17
 */

public class MainPresenterDagger {

    @dagger.Component @Singleton interface Component {
        void inject(MainView view);
    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {
        public static final String SERIAL = "serial";

        private int serial = 0;

        @Inject Presenter() { }

        @Override protected void onLoad(Bundle savedInstanceState) {
            if (savedInstanceState != null && serial == -1) {
                serial = savedInstanceState.getInt(SERIAL);
            }
            getView().show(String.valueOf(this.serial));
        }

        @Override protected void onSave(Bundle outState) {
            outState.putInt(SERIAL, serial);
        }

        public void incrementSerial() {
            this.serial++;
            getView().show(String.valueOf(this.serial));
            if (serial >= 10) {
                getView().openNext();
            }
        }

        public void onSerialChange(int newSerial) {
            this.serial = newSerial;
            getView().show(String.valueOf(this.serial));
        }

        public void onSerialChange(String newSerial) {
            this.serial = Integer.parseInt(newSerial);
            getView().show(String.valueOf(this.serial));
        }

    }

}
