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

    private Button birthDateButton;
    private int year, month, day;
    private String birthDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_layout);

        nameEditText = findViewById(R.id.nameEditText);
        birthDateButton = findViewById(R.id.birthDateButton);
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

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        birthDateButton.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(SurveyActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        year = selectedYear;
                        month = selectedMonth;
                        day = selectedDay;
                        birthDate = String.format("%d-%d-%d", day, month + 1, year); // Update birthDate
                        birthDateButton.setText(birthDate); // Set button text to selected date
                    }, year, month, day);
            datePickerDialog.show();
        });

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
                && !TextUtils.isEmpty(birthDate)
                && !TextUtils.isEmpty(cityEditText.getText().toString().trim())
                && (maleRadioButton.isChecked() || femaleRadioButton.isChecked())
                && !TextUtils.isEmpty(beneficialUseCaseEditText.getText().toString().trim());

        sendButton.setVisibility(isFormFilled ? View.VISIBLE : View.GONE);
    }
}