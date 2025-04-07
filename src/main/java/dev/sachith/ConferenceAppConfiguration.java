package dev.sachith;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import jakarta.validation.constraints.NotEmpty;

public class ConferenceAppConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String databaseUrl;

    @NotEmpty
    @JsonProperty
    private String databaseUser;

    @NotEmpty
    @JsonProperty
    private String databasePassword;

    @NotEmpty
    @JsonProperty
    private String apiKey;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getApiKey() {
        return apiKey;
    }
}
