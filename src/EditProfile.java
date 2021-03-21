package com.example.profilescreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mName, mPhone;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    ImageView profilePic;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mName = (EditText) findViewById(R.id.newName);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        mPhone = (EditText) findViewById(R.id.newPhone);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Button saveChangesButton = (Button) findViewById(R.id.saveChangesButton);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        userID = fAuth.getCurrentUser().getUid();

        StorageReference pathRef = storageReference.child("images/" + userID);
        try {
            final File file= File.createTempFile("image","jpeg");
            pathRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    profilePic.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(EditProfile.this, "fudeu",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        saveChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //final String email = mEmail.getText().toString().trim();
                String fullName = mName.getText().toString();
                final String phone = mPhone.getText().toString();
                userID = fAuth.getCurrentUser().getUid();

                //it needs this email otherwise it will set to blank
                final String email = fAuth.getCurrentUser().getEmail();
                //

                if(TextUtils.isEmpty(fullName)){
                    mName.setError("Name cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone cannot be empty");
                    return;
                }
                if(TextUtils.getTrimmedLength(phone) < 10 || TextUtils.getTrimmedLength(phone) > 10){
                    mPhone.setError("Phone has to be 10 digits long");
                    return;
                }

                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("fName", fullName);
                user.put("email", email);
                user.put("phone", phone);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                    }
                });
                saveChangesProfile();
            }
        });

        Button cancelChangesButton = (Button) findViewById(R.id.cancelChangesButton);
        cancelChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelChangesProfile();
            }
        });
    }

    public void saveChangesProfile(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void cancelChangesProfile(){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    public void uploadPicture(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Profile Image...");
        pd.show();

        //generate ID for picture (so each user has it's own
        final String key = fAuth.getUid();

        StorageReference imagesRef = storageReference.child("images/" + key);

        imagesRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Image uploaded.", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Progress: " + (int) progressPercent + "%");
            }
        });



    }
}