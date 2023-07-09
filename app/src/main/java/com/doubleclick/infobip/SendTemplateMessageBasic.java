package com.doubleclick.infobip;

import android.os.AsyncTask;
import android.util.Log;


import com.doubleclick.infobip.Button;
import com.doubleclick.infobip.Content;
import com.doubleclick.infobip.Header;
import com.doubleclick.infobip.JsonText;
import com.doubleclick.infobip.Message;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendTemplateMessageBasic {

    private static final String BASE_URL = "https://dmvmer.api.infobip.com";
    private static final String API_KEY = "App f559513aa5443a485be22738871b0979-a292877f-c8b1-403b-a15a-a116b4973a39";
    private static final String MEDIA_TYPE = "application/json";

    private static final String SENDER = "447860099299";
    private static final String RECIPIENT = "+201221930858";

    private static final String TAG = "SendTemplateMessageBasi";

    public static class WhatsAppTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            String bodyJson = String.format(
                    "{\n" +
                            "  \"messages\": [\n" +
                            "    {\n" +
                            "        \"from\": \"%s\",\n" +
                            "        \"to\": \"%s\",\n" +
                            "        \"content\": {\n" +
                            "          \"templateName\": \"registration_success\",\n" +
                            "          \"templateData\": {\n" +
                            "            \"body\": {\n" +
                            "              \"placeholders\": [\n" +
                            "                \"sender\",\n" +
                            "                \"message\",\n" +
                            "                \"delivered\",\n" +
                            "                \"testing\"\n" +
                            "              ]\n" +
                            "            },\n" +
                            "            \"header\": {\n" +
                            "              \"type\": \"IMAGE\",\n" +
                            "              \"mediaUrl\": \"https://api.infobip.com/ott/1/media/infobipLogo\"\n" +
                            "            },\n" +
                            "            \"buttons\": [\n" +
                            "              {\n" +
                            "                \"type\": \"QUICK_REPLY\",\n" +
                            "                \"parameter\": \"yes-payload\"\n" +
                            "              },\n" +
                            "              {\n" +
                            "                \"type\": \"QUICK_REPLY\",\n" +
                            "                \"parameter\": \"no-payload\"\n" +
                            "              },\n" +
                            "              {\n" +
                            "                \"type\": \"QUICK_REPLY\",\n" +
                            "                \"parameter\": \"later-payload\"\n" +
                            "              }\n" +
                            "            ]\n" +
                            "        },\n" +
                            "        \"language\": \"en\"\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}",
                    SENDER, RECIPIENT);
            List<Message> d = new ArrayList<Message>();
            List<String> d1 = new ArrayList<String>();
            List<Button> d3 = new ArrayList<Button>();
            d3.add(new Button("Android","TYPE Henna"));
            d1.add("Hello Eslam");
            d.add(new Message(new Content("en",new TemplateData(new Body(d1),d3,new Header("URL Here","TYpe URL")),""),SENDER,RECIPIENT));
            JsonText message = new JsonText(d);

            Log.e(TAG, "doInBackground: "+ bodyJson );
            RequestBody body = RequestBody.create(bodyJson, MediaType.parse(MEDIA_TYPE));
            Request request = prepareHttpRequest(body);
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("HTTP status code: " + response.code());
            try {
                System.out.println("Response body: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }
    }

    private static Request prepareHttpRequest(RequestBody body) {
        return new Request.Builder()
                .url(String.format("%s/whatsapp/1/message/template", BASE_URL))
                .method("POST", body)
                .addHeader("Authorization", API_KEY)
                .addHeader("Content-Type", MEDIA_TYPE)
                .addHeader("Accept", MEDIA_TYPE)
                .build();
    }

}
