# Discontinued

This project is no-longer maintained. A continued and maintained version of this project is available at [TASSIA710/diskord-webhooks](https://github.com/TASSIA710/diskord-webhooks) (updated as a Kotlin multiplatform library).



# Java Discord Webhook

[![](https://jitpack.io/v/TASSIA710/JavaDiscordWebhook.svg)](https://jitpack.io/#TASSIA710/JavaDiscordWebhook)
[![](https://img.shields.io/github/license/TASSIA710/JavaDiscordWebhook)](https://github.com/TASSIA710/JavaDiscordWebhook/blob/main/LICENSE)

Execute Discord webhooks in Java.



## Summary

1. [Examples](#examples)
2. [Download](#download)
3. [Other](#other)



## Examples

### Super Simple Example
```java
DiscordWebhook webhook = new DiscordWebhook("https://discordapp.com/api/webhooks/{id}/{token}");
try {
    webhook.builder().withContent("Test").execute();
} catch (IOException e) {
    e.printStackTrace();
}
```

### Embeds
```java
new DiscordWebhook("https://discordapp.com/api/webhooks/{id}/{token}").builder()
    .withContent("Cool message!")
    .withAvatarURL("https://avatars1.githubusercontent.com/u/38081490")
    .withTTS(false)
    .withUsername("JavaDiscordWebhook")
    .withEmbed(new DiscordEmbed()
            .setTitle("Cool title!")
            .setDescription("Big description! :O")
            .setUrl("https://github.com")
            .setFooter("Nice footer", "https://avatars1.githubusercontent.com/u/583231")
            .setImage("https://avatars1.githubusercontent.com/u/583231")
            .setThumbnail("https://avatars1.githubusercontent.com/u/583231")
            .setAuthor("TASSIA")
            .setColor(Color.RED)
            .setTimestamp(System.currentTimeMillis())
            .addField("Field 1", "Value 1")
    )
    .execute();
```

### Delete a webhook
```java
new DiscordWebhook("https://discordapp.com/api/webhooks/{id}/{token}").delete();
```

### Renaming a webhook
```java
new DiscordWebhook("https://discordapp.com/api/webhooks/{id}/{token}").setName("Sherlock Holmes");
```



## Download

### Maven

Add the following repository and dependency to your `pom.xml`:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.TASSIA710</groupId>
    <artifactId>JavaDiscordWebhook</artifactId>
    <version>0.0.3</version>
</dependency>
```



## Other

### License

```
MIT License

Copyright (c) 2020 Tassilo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

### Related Projects

- [PHP-Discord-Webhooks](https://github.com/TASSIA710/PHP-Discord-Webhooks)
