package net.tassia.webhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an executable Discord webhook.
 */
public class DiscordWebhook implements DiscordConstants {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final long id;
    private final String token;
    private final ObjectMapper mapper;
    private final OkHttpClient client;

    /**
     * Creates a new Discord webhook object with the given URL.
     * @param url the webhook url
     * @throws IllegalArgumentException if the url does not match a valid Discord webhook url
     */
    public DiscordWebhook(String url) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("https://discordapp\\.com/api/webhooks/([0-9]+)/([a-zA-Z0-9-_]+)");
        Matcher matcher = pattern.matcher(url);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("url does not match a valid Discord webhook url");
        }

        this.id = Long.parseLong(matcher.group(1));
        this.token = matcher.group(2);
        this.mapper = new ObjectMapper();
        this.client = new OkHttpClient();
    }

    /**
     * Creates a new Discord webhook object with the given ID and token.
     * @param id the ID
     * @param token the token
     */
    public DiscordWebhook(long id, String token) {
        this.id = id;
        this.token = token;
        this.mapper = new ObjectMapper();
        this.client = new OkHttpClient();
    }

    /**
     * Returns the public ID of this webhook.
     * @return the ID
     */
    public long getID() {
        return id;
    }

    /**
     * Returns the secret token of this webhook. You should avoid sharing/leaking this value at all cost.
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns a new webhook builder that can be used to execute this webhook.
     * @return the builder
     */
    public DiscordWebhookBuilder builder() {
        return new DiscordWebhookBuilder(this, mapper);
    }

    /**
     * Returns the URL of this webhook.
     * @return the URL
     */
    public String getURL() {
        return "https://discordapp.com/api/webhooks/" + getID() + "/" + getToken();
    }

    /**
     * Sends a JSON payload to this webhook using a POST method.
     * @param jsonPayload the payload
     * @throws IOException if an I/O error occurs
     */
    public void execute(String jsonPayload) throws IOException {
        RequestBody body = RequestBody.create(jsonPayload, JSON);
        Request request = new Request.Builder().url(getURL()).post(body).build();
        Response response = client.newCall(request).execute();
        if (response.code() != 204) {
            throw new IOException("Unexpected response: " + response.code());
        }
    }

    /**
     * Deletes this webhook.
     * @throws IOException if an I/O error occurs
     */
    public void delete() throws IOException {
        Request request = new Request.Builder().url(getURL()).delete().build();
        Response response = client.newCall(request).execute();
        if (response.code() != 204) {
            throw new IOException("Unexpected response: " + response.code());
        }
    }

    private void applySettings(Object update) throws IOException {
        String json = mapper.writeValueAsString(update);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(getURL()).patch(body).build();
        Response response = client.newCall(request).execute();
        if (response.code() != 20) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                throw new IOException("Unexpected response: " + response.code() + "\n\n" + responseBody.string());
            } else {
                throw new IOException("Unexpected response: " + response.code());
            }
        }
    }

    /**
     * Sets the default name of this webhook.
     * @param name the name
     * @throws IOException if an I/O error occurs
     */
    public void setName(String name) throws IOException {
        UpdateNameJson data = new UpdateNameJson();
        data.name = name;
        applySettings(data);
    }

    private static class UpdateNameJson {
        public String name = null;
    }

}
