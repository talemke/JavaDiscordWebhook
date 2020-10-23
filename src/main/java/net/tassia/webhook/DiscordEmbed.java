package net.tassia.webhook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents an embed.
 */
public class DiscordEmbed implements DiscordConstants {

    private String title = null;
    private String description = null;
    private String url = null;
    private long timestamp = 0L;
    private Color color = null;
    private Footer footer = null;
    private Image image = null;
    private Thumbnail thumbnail = null;
    private Author author = null;
    private Collection<Field> fields = new ArrayList<>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public DiscordEmbed setTitle(String title) throws IllegalArgumentException {
        if (title.length() > LIMIT_EMBED_TITLE) {
            throw new IllegalArgumentException("Title is too long (max. " + LIMIT_EMBED_TITLE + ")");
        }
        this.title = title;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public DiscordEmbed setDescription(String description) throws IllegalArgumentException {
        if (description.length() > LIMIT_EMBED_DESCRIPTION) {
            throw new IllegalArgumentException("Description is too long (max. " + LIMIT_EMBED_DESCRIPTION + ")");
        }
        this.description = description;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public DiscordEmbed setUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("timestamp")
    public String getTimestampAsString() {
        // return DateTimeFormatter.ISO_DATE_TIME.format(new Date(getTimestamp()).toInstant()); FIXME
        return "";
    }

    @JsonIgnore
    public long getTimestamp() {
        return timestamp;
    }

    public DiscordEmbed setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("color")
    public Integer getColorAsInt() {
        if (color == null) return null;
        int c = 0;
        c = (c | color.getRed()) << 8;
        c = (c | color.getGreen()) << 8;
        c = c | color.getBlue();
        return c;
    }

    @JsonIgnore
    public Color getColor() {
        return color;
    }

    @JsonIgnore
    public DiscordEmbed setColor(Color color) {
        this.color = color;
        return this;
    }

    @JsonProperty("footer")
    public Footer getFooter() {
        return footer;
    }

    public DiscordEmbed setFooter(Footer footer) {
        this.footer = footer;
        return this;
    }

    public DiscordEmbed setFooter(String text) {
        return setFooter(new Footer(text));
    }

    public DiscordEmbed setFooter(String text, String iconUrl) {
        return setFooter(new Footer(text, iconUrl));
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    public DiscordEmbed setImage(Image image) {
        this.image = image;
        return this;
    }

    public DiscordEmbed setImage(String url) {
        return setImage(new Image(url));
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public DiscordEmbed setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public DiscordEmbed setThumbnail(String url) {
        return setThumbnail(new Thumbnail(url));
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    public DiscordEmbed setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public DiscordEmbed setAuthor(String name) {
        return setAuthor(new Author(name));
    }

    public DiscordEmbed setAuthor(String name, String url, String iconUrl) {
        return setAuthor(new Author(name, url, iconUrl));
    }

    @JsonProperty("fields")
    public Collection<Field> getFields() {
        return fields;
    }

    public DiscordEmbed setFields(Collection<Field> fields) {
        this.fields = fields;
        return this;
    }

    public DiscordEmbed addField(Field field) {
        fields.add(field);
        return this;
    }

    public DiscordEmbed addField(String title, String value) {
        return addField(new Field(title, value));
    }

    public DiscordEmbed addField(String title, String value, boolean inline) {
        return addField(new Field(title, value, inline));
    }



    public static class Footer {

        private String text;
        private String iconUrl;
        // private String proxyIconUrl;

        public Footer(String text) {
            this(text, null);
        }

        public Footer(String text, String iconUrl) {
            setText(text);
            setIconUrl(iconUrl);
        }

        @JsonProperty("text")
        public String getText() {
            return text;
        }

        public Footer setText(String text) {
            if (text.length() > LIMIT_FOOTER) {
                throw new IllegalArgumentException("Footer is too long (max. " + LIMIT_FOOTER + ")");
            }
            this.text = text;
            return this;
        }

        @JsonProperty("icon_url")
        public String getIconUrl() {
            return iconUrl;
        }

        public Footer setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

    }



    public static class Image {

        private String url;
        // private String proxyUrl;
        // private int height;
        // private int width;

        public Image(String url) {
            setUrl(url);
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        public Image setUrl(String url) {
            this.url = url;
            return this;
        }

    }



    public static class Thumbnail {

        private String url;
        // private String proxyUrl;
        // private int height;
        // private int width;

        public Thumbnail(String url) {
            setUrl(url);
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        public Thumbnail setUrl(String url) {
            this.url = url;
            return this;
        }

    }



    public static class Author {

        private String name;
        private String url;
        private String iconUrl;
        // private String proxyIconUrl;

        public Author(String name) {
            this(name, null, null);
        }

        public Author(String name, String url, String iconUrl) {
            setName(name);
            setUrl(url);
            setIconUrl(iconUrl);
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        public Author setName(String name) {
            if (name.length() > LIMIT_AUTHOR) {
                throw new IllegalArgumentException("Author name is too long (max. " + LIMIT_AUTHOR + ")");
            }
            this.name = name;
            return this;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        public Author setUrl(String url) {
            this.url = url;
            return this;
        }

        @JsonProperty("icon_url")
        public String getIconUrl() {
            return iconUrl;
        }

        public Author setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

    }



    public static class Field {

        private String name;
        private String value;
        private boolean inline;

        public Field(String name, String value) {
            this(name, value, false);
        }

        public Field(String name, String value, boolean inline) {
            setName(name);
            setValue(value);
            setInline(inline);
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        public Field setName(String name) throws IllegalArgumentException {
            if (name.length() > LIMIT_FIELD_NAME) {
                throw new IllegalArgumentException("Field name is too long (max. " + LIMIT_FIELD_NAME + ")");
            }
            this.name = name;
            return this;
        }

        @JsonProperty("value")
        public String getValue() {
            return value;
        }

        public Field setValue(String value) throws IllegalArgumentException {
            if (value.length() > LIMIT_FIELD_VALUE) {
                throw new IllegalArgumentException("Field value is too long (max. " + LIMIT_FIELD_VALUE + ")");
            }
            this.value = value;
            return this;
        }

        @JsonProperty("inline")
        public boolean isInline() {
            return inline;
        }

        public Field setInline(boolean inline) {
            this.inline = inline;
            return this;
        }

    }

}
