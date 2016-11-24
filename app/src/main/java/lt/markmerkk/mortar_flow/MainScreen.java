package lt.markmerkk.mortar_flow;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author mariusmerkevicius
 * @since 2016-11-24
 */

public class MainScreen implements Parcelable {

    private final String title;

    public MainScreen(String title) {
        if (title == null) throw new IllegalArgumentException("title == null");
        this.title = title;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainScreen that = (MainScreen) o;

        return title.equals(that.title);
    }

    @Override public int hashCode() {
        return title.hashCode();
    }

    public String getTitle() {
        return title;
    }

    //region Instance save

    private MainScreen(Parcel in) {
        this.title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainScreen> CREATOR = new Creator<MainScreen>() {
        @Override
        public MainScreen createFromParcel(Parcel in) {
            return new MainScreen(in);
        }

        @Override
        public MainScreen[] newArray(int size) {
            return new MainScreen[size];
        }
    };

    //endregion

}
