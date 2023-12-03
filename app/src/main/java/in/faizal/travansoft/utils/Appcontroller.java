package in.faizal.travansoft.utils;

import android.app.Application;
import android.content.Context;

public class Appcontroller extends Application {
    private static Context context;

    public static final String TAG = Appcontroller.class
            .getSimpleName();


    private static Appcontroller mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Appcontroller.context = getApplicationContext();

    }

    public static Context getAppContext() {
        return Appcontroller.context;
    }

    public static synchronized Appcontroller getInstance() {
        return mInstance;
    }


}
