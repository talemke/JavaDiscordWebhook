package net.tassia.webhook;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an executable Discord webhook.
 */
public class DiscordWebhook {

    /**
     * Defines the maximum content length.
     */
    public static final int LIMIT_CONTENT = 4000;

    /**
     * Defines the maximum length of an embed title.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_EMBED_TITLE = 256;

    /**
     * Defines the maximum length of the embed description.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_EMBED_DESCRIPTION = 2048;

    /**
     * Defines the maximum amount fields an embed can have.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_EMBED_FIELDS = 25;

    /**
     * Defines the maximum length of a field name.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_FIELD_NAME = 256;

    /**
     * Defines the maximum length of a field value.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_FIELD_VALUE = 1024;

    /**
     * Defines the maximum footer length.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_FOOTER = 2048;

    /**
     * Defines the maximum length of the author name.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_AUTHOR = 256;

    /**
     * Defines the maximum amount of embeds a webhook can have.
     */
    public static final int LIMIT_EMBEDS = 10;

    /**
     * Defines the maximum amount of characters in total for an embed.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    public static final int LIMIT_TOTAL = 6000;



    private final long id;
    private final String token;



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
    }



    /**
     * Creates a new Discord webhook object with the given ID and token.
     * @param id the ID
     * @param token the token
     */
    public DiscordWebhook(long id, String token) {
        this.id = id;
        this.token = token;
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
        return new DiscordWebhookBuilder(this);
    }

}
