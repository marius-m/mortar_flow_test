package lt.markmerkk.mortar_flow;

import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mortar.ViewPresenter;

/**
 * @author mariusmerkevicius
 * @since 2016-11-17
 */

public class MainPresenter extends ViewPresenter<MainView> {
    public static final String SERIAL = "serial";

    private final DateFormat format = new SimpleDateFormat();
    private int serial = -1;

    @Override protected void onLoad(Bundle savedInstanceState) {
        if (savedInstanceState != null && serial == -1) {
            serial = savedInstanceState.getInt(SERIAL);
        }
        getView().show("Update #" + serial + " at " + format.format(new Date()));
    }

    @Override protected void onSave(Bundle outState) {
        outState.putInt(SERIAL, serial);
    }

    public void onSerialChange(int newSerial) {
        this.serial = newSerial;
        getView().show("New serial: "+this.serial);
    }

}
