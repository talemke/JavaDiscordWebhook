# Java Discord Webhook
Execute Discord webhooks in Java.



## Example

### Super Simple Example
```java
DiscordWebhook webhook = new DiscordWebhook("https://discordapp.com/api/webhooks/{id}/{token}");
try {
    webhook.builder().withContent("Test").execute();
} catch (IOException e) {
    e.printStackTrace();
}
```
