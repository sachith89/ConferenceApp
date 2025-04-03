package dev.sachith;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class ConferenceAppApplication extends Application<ConferenceAppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ConferenceAppApplication().run(args);
    }

    @Override
    public String getName() {
        return "conference-app";
    }

    @Override
    public void initialize(final Bootstrap<ConferenceAppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ConferenceAppConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
