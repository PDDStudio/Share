package com.pddstudio.share.conf;
/*
 * This Class was created by Patrick J
 * on 26.02.16. For more Details and licensing information
 * have a look at the README.md
 */

import java.util.ServiceConfigurationError;

public class EmailConfiguration {

    private final String[] sendTo;
    private final String[] sendCc;
    private final String[] sendBcc;
    private final String emailSubject;

    private EmailConfiguration(Builder builder) {
        this.sendTo = builder.sendTo;
        this.sendCc = builder.sendCc;
        this.sendBcc = builder.sendBcc;
        this.emailSubject = builder.emailSubject;
    }

    public String[] getSendTo() {
        return sendTo;
    }

    public String[] getSendCc() {
        return sendCc;
    }

    public String[] getSendBcc() {
        return sendBcc;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public class Builder {

        private String[] sendTo;
        private String[] sendCc;
        private String[] sendBcc;
        private String emailSubject;

        public Builder() {}

        public Builder addEmailAddressTo(String... sendTo) {
            this.sendTo = sendTo;
            return this;
        }

        public Builder addEmailAddressCc(String... sendCc) {
            this.sendCc = sendCc;
            return this;
        }

        public Builder addEmailSendBcc(String... sendBcc) {
            this.sendBcc = sendBcc;
            return this;
        }

        public Builder addEmailSubject(String emailSubject) {
            this.emailSubject = emailSubject;
            return this;
        }

        public EmailConfiguration create() {
            return new EmailConfiguration(this);
        }

    }

}
