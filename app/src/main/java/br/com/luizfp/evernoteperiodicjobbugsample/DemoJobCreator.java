package br.com.luizfp.evernoteperiodicjobbugsample;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created on 15/03/2018
 *
 * @author Luiz Felipe (https://github.com/luizfp)
 */
public class DemoJobCreator implements JobCreator {

    @Override
    public Job create(@NonNull final String tag) {
        switch (tag) {
            case DemoSyncJob.TAG:
                return new DemoSyncJob();
            default:
                return null;
        }
    }
}