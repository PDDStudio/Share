/*
 *
 *     Copyright (c) 2016 Patrick J
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.pddstudio.share.conf;
/*
 * This Class was created by Patrick J
 * on 26.02.16. For more Details and licensing information
 * have a look at the README.md
 */

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
