package skodavox.peas.unitbrno.cz.skodavoxapp;

import skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests.DevRunner;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SkodaPro.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        DevRunner.runSometing();
    }
}
