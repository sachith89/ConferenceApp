package dev.sachith;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

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
    public void run(final ConferenceAppConfiguration config, final Environment environment) {
        final Jdbi jdbi = Jdbi.create(config.getDatabaseUrl(), config.getDatabaseUser(), config.getDatabasePassword());
        jdbi.installPlugin(new SqlObjectPlugin());

        final SessionDAO sessionDAO = jdbi.onDemand(SessionDAO.class);
        final SessionService sessionService = new SessionService(sessionDAO);
        final SessionResource sessionResource = new SessionResource(sessionService, config.getApiKey());

        environment.jersey().register(sessionResource);
        environment.jersey().register(new AuthenticationFilter(config.getApiKey()));
    }

}
