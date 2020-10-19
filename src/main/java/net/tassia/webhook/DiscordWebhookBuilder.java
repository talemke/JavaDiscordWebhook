package net.tassia.webhook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This class is responsible for building and executing a webhook.
 */
public class DiscordWebhookBuilder {

    @JsonIgnore private final DiscordWebhook webhook;
    private String content = null;
    private String username = null;
    private String avatar_url = null;
    private boolean tts = false;



    DiscordWebhookBuilder(DiscordWebhook webhook) {
        if (webhook == null) throw new NullPointerException();
        this.webhook = webhook;
    }



    public void execute() throws IOException {
        URL url = new URL("https://discordapp.com/api/webhooks/" + webhook.getID() + "/" + webhook.getToken());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("User-Agent", "TASSIA710/JavaDiscordWebhook@1.0.0");

        byte[] payload = new ObjectMapper().writeValueAsString(this).getBytes(StandardCharsets.UTF_8);
        connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
        connection.setDoOutput(true);
        connection.getOutputStream().write(payload);
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        int code = connection.getResponseCode();
        byte[] response;
        if (code > 299) {
            response = connection.getErrorStream().readAllBytes();
        } else {
            response = connection.getInputStream().readAllBytes();
        }
        String status = connection.getResponseMessage();
        String message = new String(response, StandardCharsets.UTF_8);
        connection.disconnect();

        System.out.println(code + " " + connection.getResponseMessage());
        System.out.println(new String(payload));

        if (code != 204) {
            throw new IllegalStateException("Received " + status + " " + status + "\n\n" + message);
        }
    }



    public DiscordWebhookBuilder withContent(String content) {
        this.content = content;
        return this;
    }



    public String getContent() {
        return content;
    }



    public DiscordWebhookBuilder withUsername(String username) {
        this.username = username;
        return this;
    }



    public String getUsername() {
        return username;
    }



    public DiscordWebhookBuilder withAvatarURL(String avatarUrl) {
        this.avatar_url = avatarUrl;
        return this;
    }



    public String getAvatarURL() {
        return avatar_url;
    }



    public DiscordWebhookBuilder withTTS(boolean tts) {
        this.tts = tts;
        return this;
    }



    public boolean isTTS() {
        return tts;
    }



    // TODO: With file

    // TODO: Get files

    // TODO: With embed

    // TODO: Get embeds

}
