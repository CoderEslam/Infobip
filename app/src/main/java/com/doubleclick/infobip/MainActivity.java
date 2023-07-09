package com.doubleclick.infobip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDeliveryResult;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://dmvmer.api.infobip.com";
    private static final String API_KEY = "f559513aa5443a485be22738871b0979-a292877f-c8b1-403b-a15a-a116b4973a39";
    private static final String RECIPIENT = "+201221930858";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Task().execute("");
//        new SendTemplateMessageLib.TaskWhatsApp().execute();
        new SendTemplateMessageBasic.WhatsAppTask().execute();
    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            // Create the API client and the Send SMS API instances.
            ApiClient apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY))
                    .withBaseUrl(BaseUrl.from(BASE_URL))
                    .build();
            SmsApi sendSmsApi = new SmsApi(apiClient);

            // Create a message to send.
            SmsTextualMessage smsMessage = new SmsTextualMessage()
                    .addDestinationsItem(new SmsDestination().to(RECIPIENT))
                    .text("Hello Eslam!");

            // Create a send message request.
            SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
                    .messages(Collections.singletonList(smsMessage));

            try {
                // Send the message.
                SmsResponse smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest).execute();
                System.out.println("Response body: " + smsResponse);

                // Get delivery reports. It may take a few seconds to show the above-sent message.
                SmsDeliveryResult reportsResponse = sendSmsApi.getOutboundSmsMessageDeliveryReports().execute();
                System.out.println(reportsResponse.getResults());
            } catch (ApiException e) {
                System.out.println("HTTP status code: " + e.responseStatusCode());
                System.out.println("Response body: " + e.rawResponseBody());
            }

            return "Done";

        }
    }
}