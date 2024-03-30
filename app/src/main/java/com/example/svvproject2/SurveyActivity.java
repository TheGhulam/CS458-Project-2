package com.example.svvproject2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SurveyActivity extends AppCompatActivity implements SurveyActivityInterface {

    private EditText nameEditText;
    private Spinner educationLevelSpinner;
    private EditText cityEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private CheckBox chatgptCheckBox;
    private EditText chatgptDefectsEditText;
    private CheckBox bardCheckBox;
    private EditText bardDefectsEditText;
    private CheckBox claudeCheckBox;
    private EditText claudeDefectsEditText;
    private CheckBox copilotCheckBox;
    private EditText copilotDefectsEditText;
    private EditText beneficialUseCaseEditText;
    private Button sendButton;
    private EditText dayEditText;
    private EditText monthEditText;
    private EditText yearEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_layout);

        nameEditText = findViewById(R.id.nameEditText);
        educationLevelSpinner = findViewById(R.id.educationLevelSpinner);
        cityEditText = findViewById(R.id.cityEditText);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        chatgptCheckBox = findViewById(R.id.chatgptCheckBox);
        chatgptDefectsEditText = findViewById(R.id.chatgptDefectsEditText);
        bardCheckBox = findViewById(R.id.bardCheckBox);
        bardDefectsEditText = findViewById(R.id.bardDefectsEditText);
        claudeCheckBox = findViewById(R.id.claudeCheckBox);
        claudeDefectsEditText = findViewById(R.id.claudeDefectsEditText);
        copilotCheckBox = findViewById(R.id.copilotCheckBox);
        copilotDefectsEditText = findViewById(R.id.copilotDefectsEditText);
        beneficialUseCaseEditText = findViewById(R.id.beneficialUseCaseEditText);
        sendButton = findViewById(R.id.sendButton);

        chatgptCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            chatgptDefectsEditText.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateSendButtonVisibility();
        });

        bardCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            bardDefectsEditText.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateSendButtonVisibility();
        });

        claudeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            claudeDefectsEditText.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateSendButtonVisibility();
        });

        copilotCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            copilotDefectsEditText.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateSendButtonVisibility();
        });

        dayEditText = findViewById(R.id.dayEditText);
        monthEditText = findViewById(R.id.monthEditText);
        yearEditText = findViewById(R.id.yearEditText);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateSendButtonVisibility();
            }
        };

        dayEditText.addTextChangedListener(textWatcher);
        monthEditText.addTextChangedListener(textWatcher);
        yearEditText.addTextChangedListener(textWatcher);

        sendButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String educationLevel = educationLevelSpinner.getSelectedItem().toString();
            String city = cityEditText.getText().toString().trim();
            String gender = maleRadioButton.isChecked() ? "Male" : "Female";
            String chatgptDefects = chatgptCheckBox.isChecked() ? chatgptDefectsEditText.getText().toString().trim() : "";
            String bardDefects = bardCheckBox.isChecked() ? bardDefectsEditText.getText().toString().trim() : "";
            String claudeDefects = claudeCheckBox.isChecked() ? claudeDefectsEditText.getText().toString().trim() : "";
            String copilotDefects = copilotCheckBox.isChecked() ? copilotDefectsEditText.getText().toString().trim() : "";
            String beneficialUseCase = beneficialUseCaseEditText.getText().toString().trim();

            // Perform further processing or send the data to a server

            Toast.makeText(SurveyActivity.this, "Survey submitted", Toast.LENGTH_SHORT).show();
        });

        beneficialUseCaseEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateSendButtonVisibility(); // Check if form is filled after text change
            }
        });
    }

    private void updateSendButtonVisibility() {
        boolean isFormFilled = !TextUtils.isEmpty(nameEditText.getText().toString().trim())
                && !TextUtils.isEmpty(dayEditText.getText().toString().trim())
                && !TextUtils.isEmpty(monthEditText.getText().toString().trim())
                && !TextUtils.isEmpty(yearEditText.getText().toString().trim())
                && !TextUtils.isEmpty(cityEditText.getText().toString().trim())
                && (maleRadioButton.isChecked() || femaleRadioButton.isChecked())
                && !TextUtils.isEmpty(beneficialUseCaseEditText.getText().toString().trim());

        TextView birthdayErrorTextView = findViewById(R.id.birthdayErrorTextView);

        if (isFormFilled) {
            int age = calculateAge(
                    Integer.parseInt(yearEditText.getText().toString().trim()),
                    Integer.parseInt(monthEditText.getText().toString().trim()),
                    Integer.parseInt(dayEditText.getText().toString().trim())
            );
            if (age < 0) {
                isFormFilled = false;
                birthdayErrorTextView.setText("Birth date must be in the past");
                birthdayErrorTextView.setVisibility(View.VISIBLE);
            } else if (age < 12) {
                isFormFilled = false;
                birthdayErrorTextView.setText("User must be at least 12 years old");
                birthdayErrorTextView.setVisibility(View.VISIBLE);
            } else {
                birthdayErrorTextView.setVisibility(View.GONE);
            }
        }

        sendButton.setVisibility(isFormFilled ? View.VISIBLE : View.GONE);
    }

    private int calculateAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        dob.set(year, month - 1, day);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}