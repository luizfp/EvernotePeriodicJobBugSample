package br.com.luizfp.evernoteperiodicjobbugsample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created on 15/03/2018
 *
 * @author Luiz Felipe (https://github.com/luizfp)
 */
public class DemoSyncJob extends Job {
    public static final String TAG = "demo_sync_job";
    private static final String DEBUG_LOG_TAG = "LOG::DemoSyncJob";
    private static final long PERIODIC_TIME_MS = BuildConfig.DEBUG
            ? TimeUnit.SECONDS.toMillis(60)
            : TimeUnit.MINUTES.toMillis(30);
    private static final long FLEX_MS = BuildConfig.DEBUG
            ? TimeUnit.SECONDS.toMillis(30)
            : TimeUnit.MINUTES.toMillis(5);

    @NonNull
    @Override
    protected Result onRunJob(@NonNull final Params params) {
        Log.d(DEBUG_LOG_TAG, "onRunJob(params)");
        try {
            final Context context = getContext();
            if (isNetworkAvailable(context)) {
                new DemoSyncEngine().sync();
                cancelAllJobsForTag();
                return Result.SUCCESS;
            } else {
                Log.d(DEBUG_LOG_TAG, "No internet connection");
                if (!params.isPeriodic()) {
                    runPeriodic();
                }
                return Result.FAILURE;
            }
        } catch (Throwable throwable) {
            Log.e(DEBUG_LOG_TAG, "sync job error", throwable);
            if (!params.isPeriodic()) {
                runPeriodic();
            }
            return Result.FAILURE;
        }
    }

    public static void runJobImmediately() {
        Log.d(DEBUG_LOG_TAG, "scheduleJob");
        startNow();
    }

    private static void startNow() {
        Log.d(DEBUG_LOG_TAG, "startNow");

        new JobRequest.Builder(TAG)
                .startNow()
                .build()
                .schedule();
    }

    private static void runPeriodic() {
        Log.d(DEBUG_LOG_TAG, "runPeriodic");

        new JobRequest.Builder(TAG)
                .setPeriodic(PERIODIC_TIME_MS, FLEX_MS)
                .build()
                .schedule();
    }

    private static void cancelAllJobsForTag() {
        Log.d(DEBUG_LOG_TAG, "Cancel job TAG: " + TAG);
        JobManager.instance().cancelAllForTag(TAG);
    }

    private boolean isNetworkAvailable(Context context) {
        if (context == null)
            return false;

        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

        return false;
    }
}