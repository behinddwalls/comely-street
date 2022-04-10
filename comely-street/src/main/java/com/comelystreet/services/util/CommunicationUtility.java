package com.comelystreet.services.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.comelystreet.dao.mongodb.model.BookingServicesDetail;
import com.comelystreet.exceptions.HttpClientException;
import com.comelystreet.exceptions.InvalidRequestException;
import com.comelystreet.httpclient.ComelyStreetHttpClient;
import com.comelystreet.httpclient.ComelyStreetHttpRequest;
import com.comelystreet.mongodb.types.MandrillSendMailInput;
import com.comelystreet.mongodb.types.NexmoSendSMSInput;
import com.comelystreet.services.mail.MandrillMailService;
import com.comelystreet.services.mongo.BookingDetailService;
import com.comelystreet.services.sms.NexmoSMSService;
import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;

@Service
@EnableAsync
public class CommunicationUtility {

    @Autowired
    private MandrillMailService mandrillMailService;
    @Autowired
    private NexmoSMSService nexmoSMSService;
    @Autowired
    private ComelyStreetHttpClient httpClient;

    private final static Logger log = LoggerFactory.getLogger(CommunicationUtility.class);

    @Async
    public void communicateCustomer(BookingDetailService bookingDetailService, final String bookingId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            BookingServicesDetail bookingServicesDetail = bookingDetailService.findByBookingId(bookingId);
            String customerEmail = bookingServicesDetail.getCustomerEmail(); // "sandeep.gond@gmail.com";
            String customerMobile = "919008601541"; // "919535829415";
                                                    // //bookingServicesDetail.getCustomerMobileNumber();

            String date = DateTimeUtility.convertLongDateToUIStringDate(bookingServicesDetail.getProposedStartTime()
                    .getDate());
            String time = DateTimeUtility.convertLongTimeToUIStringTime(bookingServicesDetail.getProposedStartTime()
                    .getTimeSlot());

            String smsContent = "Your booking is confirmed for " + date + "; " + time + " at "
                    + bookingServicesDetail.getStoreDataModel().getName() + ", "
                    + bookingServicesDetail.getStoreDataModel().getArea();
            String emailContent = getBookingReceipt(bookingServicesDetail.getId());
            System.out.println("Email content: " + emailContent);
            System.out.println("SMS content: " + smsContent);
            // if (sendEmail(customerEmail, emailContent, "Comely Street",
            // "no-reply@comelystreet.com",
            // "ComelyStreet booking confirmation")) {
            // bookingServicesDetail.getCustomerCommunicationStatus().setEmailStatus("SUCCESS: "+
            // DateTimeUtility.getCurrentUTCDateTime());
            // }
            // if (sendSMS(customerMobile, smsContent)) {
            // bookingServicesDetail.getCustomerCommunicationStatus().setSmsStatus("SUCCESS: "
            // + DateTimeUtility.getCurrentUTCDateTime());
            // }
            bookingDetailService.updateBookingDetail(bookingServicesDetail);
        } catch (ParseException e) {
            // TODO Auto-generated catch blocked
            System.out.println("Customer communication failed: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private String getBookingReceipt(String bookingId) {
        try {
            ComelyStreetHttpRequest.Builder builder = new ComelyStreetHttpRequest.Builder();
            ComelyStreetHttpRequest request = builder.fullUri(
                    "http://localhost:8080/comely-street/appointment/bookingReceipt?bookingId=" + bookingId).build();
            final HttpResponse response = httpClient.executePost(request);
            System.out.println("Response: " + response.toString());
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String output = EntityUtils.toString(response.getEntity());
                System.out.println(output);
                return output;
            }
        } catch (IOException | HttpClientException e) {
            log.error("Failed to get receipt. " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Failed to get receipt.");
        log.error("Failed to get receipt.");
        return null;
    }

    public void communicateClient(BookingServicesDetail bookingServicesDetail) {

    }

    public boolean sendSMS(String mobileNumber, String smsContent) {
        try {
            NexmoSendSMSInput input = new NexmoSendSMSInput.Builder().toNumber(mobileNumber).text(smsContent).build();
            nexmoSMSService.sendSMS(input);
            return true;
        } catch (Exception e) {
            log.error("Failed to send SMS to: " + mobileNumber + " with content: " + smsContent);
        }
        return false;
    }

    public boolean sendEmail(String emailId, String emailContent, String fromName, String fromEmailId, String subject) {
        MandrillSendMailInput input = new MandrillSendMailInput.Builder(fromName, fromEmailId).toEmailId(emailId)
                .subject(subject).htmlMessage(emailContent).build();
        try {
            mandrillMailService.sendMail(input);
            return true;
        } catch (InvalidRequestException | RequestFailedException e) {
            log.error("Failed to send email to: " + emailId + " with content: " + emailContent);
        }
        return false;
    }
}
