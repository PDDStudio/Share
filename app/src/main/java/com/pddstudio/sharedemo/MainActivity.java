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

package com.pddstudio.sharedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.pddstudio.share.Share;
import com.pddstudio.share.enums.Type;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText chooserTitleText;
    EditText shareContentText;
    Button shareButton;
    CheckBox useIntentChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooserTitleText = (EditText) findViewById(R.id.intentChooserText);
        shareContentText = (EditText) findViewById(R.id.sharedContentText);

        shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(this);

        useIntentChooser = (CheckBox) findViewById(R.id.useChooserCheckBox);

    }

    @Override
    public void onClick(View v) {
        String chooserTitle;
        String shareText;

        if(chooserTitleText.getText().toString().isEmpty()) chooserTitle = getString(R.string.share_chooser_title);
        else chooserTitle = chooserTitleText.getText().toString();
        if(shareContentText.getText().toString().isEmpty()) shareText = getString(R.string.share_default_text);
        else shareText = shareContentText.getText().toString();

        //share content via 'Share'

        Share.create(this)
                .withCustomChooserTitle(chooserTitle)
                .withText(shareText)
                .withType(Type.PLAIN_TEXT)
                .share(useIntentChooser.isChecked());

    }

}
