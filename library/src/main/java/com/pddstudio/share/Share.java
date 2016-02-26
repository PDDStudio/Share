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

package com.pddstudio.share;
/*
 * This Class was created by Patrick J
 * on 26.02.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.pddstudio.share.conf.EmailConfiguration;
import com.pddstudio.share.enums.Type;

import java.io.File;

/**
 * The Share class allows you to easily configure & create shareable content
 */
public class Share {

    private final Activity activity;
    private final ShareCompat.IntentBuilder intentBuilder;
    private boolean fileAttached = false;
    private Uri fileUri = null;

    private Share(Activity activity) {
        this.activity = activity;
        this.intentBuilder = ShareCompat.IntentBuilder.from(activity);
    }

    private void checkForAttachements(Intent intent) {
        if(fileAttached && fileUri != null) {
            intent.setData(fileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    /**
     * Creates a new {@link Share} instance.
     * @param activity - Your activity
     * @return a new {@link Share} instance.
     */
    public static Share create(@NonNull Activity activity) {
        return new Share(activity);
    }

    /**
     * Set the MIME type of your share action.
     * @param type - The MIME-Type of your share action
     * @return -
     */
    public Share withType(Type type) {
        withType(type.getType());
        return this;
    }

    /**
     * Set the MIME type of your share action
     * @param type - The MIME-Type as String
     * @return -
     */
    public Share withType(String type) {
        intentBuilder.setType(type);
        return this;
    }

    /**
     * Set the literal text data to be sent as part of the share.
     * @param text - The text which should be part of your share.
     * @return -
     */
    public Share withText(String text) {
        intentBuilder.setText(text);
        return this;
    }

    /**
     * Set the literal text data to be sent as part of the share.
     * @param text - The text which should be part of your share.
     * @return -
     */
    public Share withText(@StringRes int text) {
        intentBuilder.setText(activity.getString(text));
        return this;
    }

    /**
     * Set an HTML string to be sent as part of the share.
     * @param text - HTML styled text which should be part of your share.
     * @return -
     */
    public Share withHtmlText(String text) {
        intentBuilder.setHtmlText(text);
        return this;
    }

    /**
     * Set an HTML string to be sent as part of the share.
     * @param text - HTML styled text which should be part of your share.
     * @return -
     */
    public Share withHtmlText(@StringRes int text) {
        intentBuilder.setHtmlText(activity.getString(text));
        return this;
    }

    /**
     * Append an {@link EmailConfiguration} - this is used in case you want to share something via E-Mail
     * @param emailConfiguration - The {@link EmailConfiguration} instance
     * @return -
     */
    public Share withEmailConfiguration(@NonNull EmailConfiguration emailConfiguration) {

        //set the address for the email
        if(emailConfiguration.getSendTo() != null) {
            intentBuilder.addEmailTo(emailConfiguration.getSendTo());
        } else {
            Log.w("Share", "No Email address provided via EmailConfiguration. Skipping...");
            return this;
        }

        //set the cc address(es) for the email
        if(emailConfiguration.getSendCc() != null) {
            intentBuilder.addEmailCc(emailConfiguration.getSendCc());
        }

        //set the bcc address(es) for the email
        if(emailConfiguration.getSendBcc() != null) {
            intentBuilder.addEmailBcc(emailConfiguration.getSendBcc());
        }

        //set the subject for the email
        if(emailConfiguration.getEmailSubject() != null) {
            intentBuilder.setSubject(emailConfiguration.getEmailSubject());
        } else {
            intentBuilder.setSubject("");
        }

        return this;
    }

    /**
     * Add a file to the content you want to share.
     * <b>Note:</b> This requires a {@linkplain FileProvider}.
     * @param mimeType - The MIME-Type of the file
     * @param file - The File itself
     * @param fileProviderAuthority - The name of your {@linkplain FileProvider}
     * @return -
     */
    public Share withFile(Type mimeType, File file, String fileProviderAuthority) {
        withFile(mimeType.getType(), file, fileProviderAuthority);
        return this;
    }

    /**
     * Add a file to the content you want to share.
     * <b>Note:</b> This requires a {@linkplain FileProvider}.
     * @param mimeType - The MIME-Type of the file
     * @param file - The File itself
     * @param fileProviderAuthority - The name of your {@linkplain FileProvider}
     * @return -
     */
    public Share withFile(String mimeType, File file, String fileProviderAuthority) {
        try {
            Uri fileUri = FileProvider.getUriForFile(activity, fileProviderAuthority, file);
            intentBuilder.setType(mimeType);
            intentBuilder.setStream(fileUri);
            fileAttached = true;
            this.fileUri = fileUri;
        } catch (IllegalArgumentException ex) {
            Log.e("Share", "Unable to retrieve Uri for the given file. The given File might be outside the paths supported by the provider.");
            ex.printStackTrace();
        }

        return this;
    }

    /**
     * Set a custom title for the Chooser (if selected)
     * @param chooserTitle - The title used by the chooser.
     * @return -
     */
    public Share withCustomChooserTitle(String chooserTitle) {
        intentBuilder.setChooserTitle(chooserTitle);
        return this;
    }

    /**
     * Set a custom title for the Chooser (if selected)
     * @param chooserTitle - The title used by the chooser.
     * @return -
     */
    public Share withCustomChooserTitle(@StringRes int chooserTitle) {
        intentBuilder.setChooserTitle(activity.getString(chooserTitle));
        return this;
    }

    /**
     * Get the {@linkplain Intent} configured by this {@link Share} instance.
     * @param chooserIntent - Whether the {@linkplain Intent} should be configured with or without a chooser.
     * @return The {@linkplain Intent} configured by this {@link Share} instance.
     */
    public Intent getIntent(boolean chooserIntent) {
        if(chooserIntent) return intentBuilder.createChooserIntent();
        else return intentBuilder.getIntent();
    }

    /**
     * Immediately share the {@linkplain Intent} configured by this {@link Share} instance.
     * @param chooserIntent - Whether the {@linkplain Intent} should be configured with or without a chooser.
     */
    public void share(boolean chooserIntent) {
        if(chooserIntent) {
            Intent intent = intentBuilder.createChooserIntent();
            checkForAttachements(intent);
            if(intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            } else {
                Log.e("Share", "Unable to start activity. There's no activity that can handle this type of request!");
            }
        } else {
            Intent intent = intentBuilder.getIntent();
            checkForAttachements(intent);
            if(intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            } else {
                Log.e("Share", "Unable to start activity. There's no activity that can handle this type of request!");
            }
        }
    }

    /**
     * Immediately share the {@linkplain Intent} configured by this {@link Share} instance, without a chooser.
     */
    public void share() {
        share(false);
    }

}
