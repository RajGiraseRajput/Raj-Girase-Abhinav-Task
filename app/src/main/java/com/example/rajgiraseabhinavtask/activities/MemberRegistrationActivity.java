package com.example.rajgiraseabhinavtask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rajgiraseabhinavtask.R;
import com.example.rajgiraseabhinavtask.database.MemberDatabase;
import com.example.rajgiraseabhinavtask.databinding.ActivityMemberRegisterationBinding;

public class MemberRegistrationActivity extends AppCompatActivity {

    private ActivityMemberRegisterationBinding binding;
    private MemberDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);

        binding = ActivityMemberRegisterationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding.toolbarTitleTv.setText(getString(R.string.member_registration));

        database = new MemberDatabase(this);


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String member_name = binding.memberNameEd.getText().toString();
                String member_mobile_no = binding.memberMobileNoEd.getText().toString();
                String member_select_role = "";
                if (binding.secretaryRd.isChecked()) {
                    member_select_role = binding.secretaryRd.getText().toString();
                } else if (binding.memberRd.isChecked()) {
                    member_select_role = binding.memberRd.getText().toString();
                }

                String subscription_fee = binding.memberSubscriptionEd.getText().toString();
                String deposit_amount = binding.memberDepositAmtEd.getText().toString();
                String loan_amount = binding.memberLoanAmtEd.getText().toString();

                String gender = "";
                if (binding.maleRd.isChecked()) {
                    gender = binding.maleRd.getText().toString();
                } else if (binding.femaleRd.isChecked()) {
                    gender = binding.femaleRd.getText().toString();
                } else if (binding.otherRd.isChecked()) {
                    gender = binding.otherRd.getText().toString();
                }

                String date_of_birth = binding.memberDateOfBirthEd.getText().toString();
                String date_of_joining = binding.memberDateFfJoiningEd.getText().toString();

                String marriage_status = "";
                if (binding.marriedRd.isChecked()) {
                    marriage_status = binding.marriedRd.getText().toString();
                } else if (binding.unMarriedRd.isChecked()) {
                    marriage_status = binding.unMarriedRd.getText().toString();
                }

                String date_of_marriage = binding.memberDOMarriageEd.getText().toString();
                String cast = binding.castEd.getText().toString();
                String religion = binding.religionEd.getText().toString();
                String category = binding.categoryEd.getText().toString();
                String aadhar_no = binding.aadharNoEd.getText().toString();

//                if (member_name.isEmpty() && member_mobile_no.isEmpty() && member_select_role.isEmpty() &&
//                        subscription_fee.isEmpty() && deposit_amount.isEmpty() && loan_amount.isEmpty() &&
//                        gender.isEmpty() && date_of_birth.isEmpty() && date_of_joining.isEmpty() && marriage_status.isEmpty() &&
//                        date_of_marriage.isEmpty() && cast.isEmpty() && religion.isEmpty() && category.isEmpty() && aadhar_no.isEmpty()) {
//                    Toast.makeText(MemberRegistrationActivity.this, "Enter All details", Toast.LENGTH_SHORT).show();
//                }
                if (member_mobile_no.isEmpty()) {
                    binding.memberMobileNoEd.setError("Mobile number required");
                    binding.memberMobileNoEd.requestFocus();
                } else if (member_name.isEmpty()) {
                    binding.memberNameEd.setError("Name required");
                    binding.memberNameEd.requestFocus();
                } else if (member_select_role.isEmpty()) {
                    binding.selectRoleRadioGroup.requestFocusFromTouch();
                    Toast.makeText(MemberRegistrationActivity.this, "Please Select Role", Toast.LENGTH_SHORT).show();
                } else if (subscription_fee.isEmpty()) {
                    binding.memberSubscriptionEd.setError("Subscription Fee required");
                    binding.memberSubscriptionEd.requestFocus();
                } else if (deposit_amount.isEmpty()) {
                    binding.memberDepositAmtEd.setError("Deposit amount required");
                    binding.memberDepositAmtEd.requestFocus();
                } else if (loan_amount.isEmpty()) {
                    binding.memberLoanAmtEd.setError("Loan amount required");
                    binding.memberLoanAmtEd.requestFocus();
                } else if (gender.isEmpty()) {
                    binding.genderRadioGroup.requestFocusFromTouch();
                    Toast.makeText(MemberRegistrationActivity.this, "Please select gender", Toast.LENGTH_SHORT).show();
                } else if (date_of_birth.isEmpty()) {
                    binding.memberDateOfBirthEd.setError("Date Of Birth required");
                    binding.memberDateOfBirthEd.requestFocus();
                } else if (date_of_joining.isEmpty()) {
                    binding.memberDateFfJoiningEd.setError("Date of Joining required");
                    binding.memberDateFfJoiningEd.requestFocus();
                } else if (marriage_status.isEmpty()) {
                    binding.maritalStatusRadioGroup.requestFocusFromTouch();
                    Toast.makeText(MemberRegistrationActivity.this, "Please Select Marital status", Toast.LENGTH_SHORT).show();
                } else if (date_of_marriage.isEmpty()) {
                    binding.memberDOMarriageEd.setError("Date of Marriage required");
                    binding.memberDOMarriageEd.requestFocus();
                } else if (cast.isEmpty()) {
                    binding.castEd.setError("Cast required");
                    binding.castEd.requestFocus();
                } else if (religion.isEmpty()) {
                    binding.religionEd.setError("Religion required");
                    binding.religionEd.requestFocus();
                } else if (category.isEmpty()) {
                    binding.categoryEd.setError("Category required");
                    binding.categoryEd.requestFocus();
                } else if (aadhar_no.isEmpty()) {
                    binding.aadharNoEd.setError("Aadhar number required");
                    binding.aadharNoEd.requestFocus();
                } else {
                    boolean dataInserted = database.addMembers(member_name, member_mobile_no, member_select_role,
                            subscription_fee, deposit_amount, loan_amount, gender, date_of_birth, date_of_joining,
                            marriage_status, date_of_marriage, cast, religion, category, aadhar_no);

                    if (dataInserted) {
                        Toast.makeText(MemberRegistrationActivity.this, "Member Add Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(MemberRegistrationActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        binding.changeLanguageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MemberRegistrationActivity.this, "Upcoming Feature Change Language", Toast.LENGTH_SHORT).show();
            }
        });

    }
}