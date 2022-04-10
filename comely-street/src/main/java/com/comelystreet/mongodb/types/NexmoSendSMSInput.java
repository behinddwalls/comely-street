package com.comelystreet.mongodb.types;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public final class NexmoSendSMSInput {

    @JsonProperty("from")
    private final String from;
    @JsonProperty("to")
    private final String to;
    @JsonProperty("text")
    private final String text;
    @JsonProperty("type")
    private final String type;
    @JsonProperty("api_key")
    private final String apiKey;
    @JsonProperty("api_secret")
    private final String apiSecret;

    @JsonIgnore
    private final String API_KEY = "51b3957b";
    @JsonIgnore
    private final String API_SECRET = "0f650477";

    @JsonIgnoreType
    public static class Builder {

        private String from;
        private String to;
        private String text;
        private String type;

        /**
         * To mobile number with country code like for India, 919535829415
         * 
         * @param to
         * @return
         */
        public Builder toNumber(final String to) {
            this.to = to;
            return this;
        }

        /**
         * From name like ComelyStreet, no need to set. By default,
         * ComelyStreet.
         * 
         * @param from
         * @return
         */
        public Builder fromName(final String from) {
            this.from = from;
            return this;
        }

        /**
         * Message to be sent.
         * 
         * @param text
         * @return
         */
        public Builder text(final String text) {
            this.text = text;
            return this;
        }

        /**
         * By default, value is text. For now no need to set this.
         * 
         * @param type
         * @return
         */
        public Builder type(final String type) {
            this.type = "text";
            return this;
        }

        public NexmoSendSMSInput build() {
            return new NexmoSendSMSInput(this);
        }

        @Override
        public String toString() {
            return "Builder [from=" + from + ", to=" + to + ", text=" + text + ", type=" + type + "]";
        }
    }

    public NexmoSendSMSInput(final Builder builder) {
        validateInput(builder);
        setEmptyFieldsIfRequired(builder);
        this.to = builder.to;
        this.text = builder.text;
        this.apiKey = API_KEY;
        this.apiSecret = API_SECRET;
        this.type = builder.type;
        this.from = builder.from;

    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    private void setEmptyFieldsIfRequired(final Builder builder) {
        if (StringUtils.isEmpty(builder.type))
            builder.type("text");

        if (StringUtils.isEmpty(builder.from))
            builder.fromName("ISTYLO");
    }

    private void validateInput(final Builder builder) {
        Preconditions.checkArgument(!StringUtils.isEmpty(builder.to), "Send to number can not be empty.");
        Preconditions.checkArgument(!StringUtils.isEmpty(builder.text), "Text can not be empty.");
    }

    /**
     * Convert Input to JSON API request payload format
     * 
     * @param objectMapper
     * @return
     * @throws JsonProcessingException
     */
    public String toJSONFormat(final ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
