package net.tassia.webhook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class is responsible for building and executing a webhook.
 */
public class DiscordWebhookBuilder implements DiscordConstants {

    private final DiscordWebhook webhook;
    private final ObjectMapper mapper;
    private String content = null;
    private String username = null;
    private String avatarUrl = null;
    private boolean tts = false;
    private final Collection<DiscordEmbed> embeds = new ArrayList<>();

    DiscordWebhookBuilder(DiscordWebhook webhook, ObjectMapper mapper) {
        if (webhook == null || mapper == null) throw new NullPointerException();
        this.webhook = webhook;
        this.mapper = mapper;
    }

    @JsonIgnore
    public String getJSON() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    @JsonIgnore
    public String getPrettyJSON() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    @JsonIgnore
    public void execute() throws IOException {
        webhook.execute(getPrettyJSON());
    }

    public DiscordWebhookBuilder withContent(String content) {
        if (content.length() > LIMIT_CONTENT) {
            throw new IllegalArgumentException("Content is too long (max. " + LIMIT_CONTENT + ")");
        }
        this.content = content;
        return this;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    public DiscordWebhookBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public DiscordWebhookBuilder withAvatarURL(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    @JsonProperty("avatar_url")
    public String getAvatarURL() {
        return avatarUrl;
    }

    public DiscordWebhookBuilder withTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    @JsonProperty("tts")
    public boolean isTTS() {
        return tts;
    }

    // TODO: With file

    // TODO: Get files

    public DiscordWebhookBuilder withEmbed(DiscordEmbed embed) {
        if (this.embeds.size() >= LIMIT_EMBEDS) {
            throw new IllegalArgumentException("Too many embeds. (max. " + LIMIT_EMBEDS + ")");
        }
        if (embed == null) throw new NullPointerException("Embed cannot be null.");
        embeds.add(embed);
        return this;
    }

    public DiscordWebhookBuilder withEmbeds(DiscordEmbed...embeds) {
        if (this.embeds.size() + embeds.length > LIMIT_EMBEDS) {
            throw new IllegalArgumentException("Too many embeds. (max. " + LIMIT_EMBEDS + ")");
        }
        this.embeds.addAll(Arrays.asList(embeds));
        return this;
    }

    @JsonProperty("embeds")
    public Collection<DiscordEmbed> getEmbeds() {
        return embeds;
    }

}
