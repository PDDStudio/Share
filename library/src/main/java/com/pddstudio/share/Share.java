package com.pddstudio.share;
/*
 * This Class was created by Patrick J
 * on 26.02.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.app.Activity;
import android.content.Context;
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

    public static Share create(@NonNull Activity activity) {
        return new Share(activity);
    }

    public Share withType(Type type) {
        withType(type.getType());
        return this;
    }

    public Share withType(String type) {
        intentBuilder.setType(type);
        return this;
    }

    public Share withText(String text) {
        intentBuilder.setText(text);
        return this;
    }

    public Share withText(@StringRes int text) {
        intentBuilder.setText(activity.getString(text));
        return this;
    }

    public Share withHtmlText(String text) {
        intentBuilder.setHtmlText(text);
        return this;
    }

    public Share withHtmlText(@StringRes int text) {
        intentBuilder.setHtmlText(activity.getString(text));
        return this;
    }

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

    public Share withFile(Type mimeType, File file, String fileProviderAuthority) {
        withFile(mimeType.getType(), file, fileProviderAuthority);
        return this;
    }

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

    public Share withCustomChooserTitle(String chooserTitle) {
        intentBuilder.setChooserTitle(chooserTitle);
        return this;
    }

    public Share withCustomChooserTitle(@StringRes int chooserTitle) {
        intentBuilder.setChooserTitle(activity.getString(chooserTitle));
        return this;
    }

    public Intent getIntent(boolean chooserIntent) {
        if(chooserIntent) return intentBuilder.createChooserIntent();
        else return intentBuilder.getIntent();
    }

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

    public void share() {
        share(false);
    }

}
