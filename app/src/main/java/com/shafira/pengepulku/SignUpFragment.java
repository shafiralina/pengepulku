package com.shafira.pengepulku;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shafira.pengepulku.Model.Users;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    private Button signUpBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String emailPattern = "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.login_framelayout);

        alreadyHaveAccount = view.findViewById(R.id.tv_already_have_account);

        email = view.findViewById(R.id.sign_up_email);
        fullName = view.findViewById(R.id.sign_up_name);
        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);

        signUpBtn = view.findViewById(R.id.sign_up_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullName.getText())) {
                if(!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (!TextUtils.isEmpty(confirmPassword.getText())) {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(80, 67, 37));
                    }else{
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.rgb(181, 155, 83));
                    }
                }else{
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.rgb(181, 155, 83));
                }
            }else{
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(181, 155, 83));
            }
        }else{
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.rgb(181, 155, 83));
        }
    }

    private void checkEmailAndPassword() {
        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.eror);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());

        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(181, 155, 83));
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Map<Object, String> userdata = new HashMap<>();
                                    userdata.put("fullname", fullName.getText().toString());
                                    userdata.put("email", email.getText().toString());
                                    userdata.put("password", password.getText().toString());

                                    firebaseFirestore.collection("USERS")
                                            .add(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                                        startActivity(mainIntent);
                                                        getActivity().finish();
                                                    }else{
                                                        signUpBtn.setEnabled(false);
                                                        signUpBtn.setTextColor(Color.rgb(181, 155, 83));
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }else{
                                    signUpBtn.setEnabled(false);
                                    signUpBtn.setTextColor(Color.rgb(181, 155, 83));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                confirmPassword.setError("Password doesnt matched!", customErrorIcon);
            }
        } else {
            email.setError("Invalid Email",customErrorIcon);
        }
    }
}
