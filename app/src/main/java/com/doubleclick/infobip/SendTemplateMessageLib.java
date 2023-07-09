package com.doubleclick.infobip;

import android.os.AsyncTask;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.WhatsAppApi;
import com.infobip.model.*;


public class SendTemplateMessageLib {
    private static final String BASE_URL = "https://dmvmer.api.infobip.com";
    private static final String API_KEY = "f559513aa5443a485be22738871b0979-a292877f-c8b1-403b-a15a-a116b4973a39";

    private static final String SENDER = "447860099299";
    private static final String RECIPIENT = "+201221930858";

    public static class TaskWhatsApp extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            // Create the API client and the Whatsapp API instances.
            ApiClient apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY))
                    .withBaseUrl(BaseUrl.from(BASE_URL))
                    .build();

            WhatsAppApi whatsAppApi = new WhatsAppApi(apiClient);


            // Create the content of the message.
            WhatsAppTemplateContent content = new WhatsAppTemplateContent()
                    .language("en")
                    .templateName("registration_success")
                    .templateData(new WhatsAppTemplateDataContent()
                            .header(new WhatsAppTemplateDocumentHeaderContent()
                                    .mediaUrl("https://api.infobip.com/ott/1/media/infobipLogo")
                                    .filename("infobipLogo")
                            )
                            .body(new WhatsAppTemplateBodyContent()
                                    .addPlaceholdersItem("sender")
                                    .addPlaceholdersItem("WhatsApp message")
                                    .addPlaceholdersItem("delivered")
                                    .addPlaceholdersItem("exploring Infobip API")
                            )
                            .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                    .parameter("Yes"))
                            .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                    .parameter("No"))
                            .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
                                    .parameter("Later"))
                    );

            // Create a message to send and add to bulk.
            WhatsAppMessage message = new WhatsAppMessage()
                    .from(SENDER)
                    .to(RECIPIENT)
                    .content(content);
            WhatsAppBulkMessage bulkMessage = new WhatsAppBulkMessage()
                    .addMessagesItem(message);

            try {
                // Send message.
                WhatsAppBulkMessageInfo whatsAppBulkMessageInfo = whatsAppApi.sendWhatsAppTemplateMessage(bulkMessage).execute();
                System.out.println("Response body: " + whatsAppBulkMessageInfo);
            } catch (ApiException e) {
                System.out.println("HTTP status code: " + e.responseStatusCode());
                System.out.println("Response body: " + e.rawResponseBody());
            }
            return "";
        }
    }
}
