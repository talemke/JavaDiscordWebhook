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

    /**
     * Returns a JSON string of the payload.
     * @return the JSON payload
     * @throws JsonProcessingException if something goes wrong while generating the JSON
     */
    @JsonIgnore
    public String getJSON() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    /**
     * Returns a pretty JSON string of the payload.
     * @return the pretty JSON payload
     * @throws JsonProcessingException if something goes wrong while generating the JSON
     */
    @JsonIgnore
    public String getPrettyJSON() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    /**
     * Executes this webhook.
     * @throws IOException if an I/O error occurs
     */
    @JsonIgnore
    public void execute() throws IOException {
        webhook.execute(getPrettyJSON());
    }

    /**
     * Sets the content (message).
     * @param content the content
     * @return <code>this</code>
     * @throws IllegalArgumentException if <code>content</code> is longer than {@link #LIMIT_CONTENT}
     */
    public DiscordWebhookBuilder withContent(String content) throws IllegalArgumentException {
        if (content.length() > LIMIT_CONTENT) {
            throw new IllegalArgumentException("Content is too long (max. " + LIMIT_CONTENT + ")");
        }
        this.content = content;
        return this;
    }

    /**
     * Returns the content (message).
     * @return the content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * Overrides the username.
     * @param username the username override
     * @return <code>this</code>
     */
    public DiscordWebhookBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Returns the current username override.
     * @return username
     */
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    /**
     * Overrides the avatar URL.
     * @param avatarUrl the avatar URL override
     * @return <code>this</code>
     */
    public DiscordWebhookBuilder withAvatarURL(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    /**
     * Returns the current avatar URL override.
     * @return avatar URL
     */
    @JsonProperty("avatar_url")
    public String getAvatarURL() {
        return avatarUrl;
    }

    /**
     * Sets whether the message is text-to-speech (TTS).
     * @param tts text-to-speech
     * @return <code>this</code>
     */
    public DiscordWebhookBuilder withTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    /**
     * Returns whether the message is text-to-speech (TTS).
     * @return is TTS
     */
    @JsonProperty("tts")
    public boolean isTTS() {
        return tts;
    }

    // TODO: With file

    // TODO: Get files

    /**
     * Adds an embed.
     * @param embed the embed
     * @return <code>this</code>
     */
    public DiscordWebhookBuilder withEmbed(DiscordEmbed embed) {
        if (this.embeds.size() >= LIMIT_EMBEDS) {
            throw new IllegalArgumentException("Too many embeds. (max. " + LIMIT_EMBEDS + ")");
        }
        if (embed == null) throw new NullPointerException("Embed cannot be null.");
        embeds.add(embed);
        return this;
    }

    /**
     * Adds an array of embeds.
     * @param embeds the embeds
     * @return <code>this</code>
     */
    public DiscordWebhookBuilder withEmbeds(DiscordEmbed...embeds) {
        if (this.embeds.size() + embeds.length > LIMIT_EMBEDS) {
            throw new IllegalArgumentException("Too many embeds. (max. " + LIMIT_EMBEDS + ")");
        }
        this.embeds.addAll(Arrays.asList(embeds));
        return this;
    }

    /**
     * Returns a collection of all embeds currently added.
     * @return embeds
     */
    @JsonProperty("embeds")
    public Collection<DiscordEmbed> getEmbeds() {
        return embeds;
    }

}
