package net.tassia.webhook;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Used to execute rest requests.
 */
public class SimpleRest {

    /**
     * Execute a rest request.
     * @param path the hostname & path
     * @param method the method (e.g. 'GET', 'POST')
     * @param payload the payload (can be <code>null</code>)
     * @param payloadMimeType the payload mime type (can be <code>null</code>)
     * @throws IOException
     */
    public void execute(String path, String method, String payload, String payloadMimeType) throws IOException {
        URL url = new URL(path);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("User-Agent", "TASSIA710/JavaDiscordWebhook@1.0.0");

        if (payload != null && payloadMimeType != null) {
            connection.setRequestProperty("Content-Type", payloadMimeType + "; charset=utf8");
            connection.setDoOutput(true);
            connection.getOutputStream().write(payload.getBytes(StandardCharsets.UTF_8));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
        }

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

        if (code != 204) {
            throw new RestException(code, status, message);
        }
    }


    /**
     * Thrown if an error has been encountered during a REST request.<br>
     * <b>Note:</b> Error doesn't mean an I/O error here, but a response code >= 400.
     */
    public static class RestException extends IOException {
        private final int statusCode;
        private final String statusText;
        private final String response;

        /**
         * Creates a new RestException with the given details.
         * @param statusCode the status code (e.g. 404)
         * @param statusText the status message (e.g. 'Not Found')
         * @param response the actual response
         */
        public RestException(int statusCode, String statusText, String response) {
            this.statusCode = statusCode;
            this.statusText = statusText;
            this.response = response;
        }

        /**
         * The status code.
         * @return status code
         */
        public int getStatusCode() {
            return statusCode;
        }

        /**
         * The status message.
         * @return stats message
         */
        public String getStatusText() {
            return statusText;
        }

        /**
         * The actual response.
         * @return response
         */
        public String getResponse() {
            return response;
        }

    }

}
