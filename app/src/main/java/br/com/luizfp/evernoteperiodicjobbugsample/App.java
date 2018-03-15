package br.com.luizfp.evernoteperiodicjobbugsample;

import android.app.Application;
import android.os.Build;

import com.evernote.android.job.JobConfig;
import com.evernote.android.job.JobManager;
import com.facebook.stetho.Stetho;

/**
 * Created on 15/03/2018
 *
 * @author Luiz Felipe (https://github.com/luizfp)
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initAndroidJob();
        initStetho();
    }

    private void initAndroidJob() {
        JobManager.create(App.this).addJobCreator(new DemoJobCreator());
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            JobConfig.setAllowSmallerIntervalsForMarshmallow(true);
        }
    }

    private void initStetho() {
        final Stetho.InitializerBuilder builder = Stetho.newInitializerBuilder(this);
        // Enable Chrome DevTools.
        builder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        Stetho.initialize(builder.build());
    }
}
