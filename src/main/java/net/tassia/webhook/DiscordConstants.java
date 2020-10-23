package net.tassia.webhook;

/**
 * Contains all constants for Discord webhooks. Mostly contains all the limits.
 */
public interface DiscordConstants {

    /**
     * Defines the maximum content length.
     */
    int LIMIT_CONTENT = 4000;

    /**
     * Defines the maximum length of an embed title.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_EMBED_TITLE = 256;

    /**
     * Defines the maximum length of the embed description.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_EMBED_DESCRIPTION = 2048;

    /**
     * Defines the maximum amount fields an embed can have.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_EMBED_FIELDS = 25;

    /**
     * Defines the maximum length of a field name.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_FIELD_NAME = 256;

    /**
     * Defines the maximum length of a field value.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_FIELD_VALUE = 1024;

    /**
     * Defines the maximum footer length.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_FOOTER = 2048;

    /**
     * Defines the maximum length of the author name.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_AUTHOR = 256;

    /**
     * Defines the maximum amount of embeds a webhook can have.
     */
    int LIMIT_EMBEDS = 10;

    /**
     * Defines the maximum amount of characters in total for an embed.
     * @see <a href="https://discord.com/developers/docs/resources/channel#embed-limits">Embed Limits</a>
     */
    int LIMIT_TOTAL = 6000;

}
