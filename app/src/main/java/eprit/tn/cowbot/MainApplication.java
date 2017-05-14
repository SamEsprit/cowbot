package eprit.tn.cowbot;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sami on 01/01/2017.
 */

public class MainApplication extends Application {

    private static Context mContext;
    private static MainApplication instance;

    public static Context getContext() {

        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
    }
}
