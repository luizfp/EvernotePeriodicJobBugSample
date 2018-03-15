package br.com.luizfp.evernoteperiodicjobbugsample;

import java.util.concurrent.TimeUnit;

/**
 * Created on 15/03/2018
 *
 * @author Luiz Felipe (https://github.com/luizfp)
 */
public class DemoSyncEngine {

    public void sync() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
        }
    }
}
