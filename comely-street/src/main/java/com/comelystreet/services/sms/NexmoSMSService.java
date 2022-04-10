package com.comelystreet.services.sms;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import com.comelystreet.httpclient.ComelyStreetHttpClient;
import com.comelystreet.httpclient.ComelyStreetHttpRequest;
import com.comelystreet.mongodb.types.ComelyStreetHttpConstant;
import com.comelystreet.mongodb.types.NexmoSendSMSInput;
import com.comelystreet.mongodb.types.NexmoSendSMSResponse;

@Service
public class NexmoSMSService {

    private final static Logger log = LoggerFactory.getLogger(NexmoSMSService.class);

    private final String SMS = "sms";

    @Resource(name = "nexmoServiceEndpoint")
    private Map<String, String> nexmoServiceEndpoint;

    @Resource(name = "nexmoSMSApiEndpoints")
    private Map<String, String> nexmoSMSApiEndpoints;

    @Autowired
    private ComelyStreetHttpClient httpClient;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    private ComelyStreetHttpRequest.Builder getRequestBuilder() {
        ComelyStreetHttpRequest.Builder builder = new ComelyStreetHttpRequest.Builder();
        builder.protocol(nexmoServiceEndpoint.get(ComelyStreetHttpConstant.PROTOCOL))
                .host(nexmoServiceEndpoint.get(ComelyStreetHttpConstant.HOST))
                .port(nexmoServiceEndpoint.get(ComelyStreetHttpConstant.PORT));
        return builder;
    }

    public NexmoSendSMSResponse sendSMS(final NexmoSendSMSInput input) throws Exception {
        try {
            StringEntity engineRequestContextAsStringEntity = new StringEntity(
                    input.toJSONFormat(mappingJackson2HttpMessageConverter.getObjectMapper()),
                    ContentType.APPLICATION_JSON);

            // set the ContentType to 'JSON' .
            ComelyStreetHttpRequest request = getRequestBuilder()
                    .requestHeader(HttpHeaders.CONTENT_TYPE, "application/json").path(nexmoSMSApiEndpoints.get(SMS))
                    .httpEntity(engineRequestContextAsStringEntity).build();
            final HttpResponse response = httpClient.executePost(request);
            System.out.println("SMS request: " + request.toString());
            System.out.println("SMS response: " + response.toString());
            log.debug("SMS request: " + request.toString());
            log.debug("SMS response: " + response.toString());
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return (response.getEntity() != null) ? mappingJackson2HttpMessageConverter.getObjectMapper()
                        .readValue(response.getEntity().getContent(), NexmoSendSMSResponse.class) : null;
            }
        } catch (Exception e) {
            log.error("Nexmo Send SMS API failed ", e);
            throw e;
        }
        return null;

    }
}
