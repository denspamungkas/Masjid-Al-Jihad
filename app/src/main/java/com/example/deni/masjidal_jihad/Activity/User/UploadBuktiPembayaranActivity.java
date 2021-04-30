package com.example.deni.masjidal_jihad.Activity.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.EditInformasiAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.LihatInformasiAdmin;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Iuran;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadBuktiPembayaranActivity extends AppCompatActivity {

    public static final String EXTRA_NAMA_BAYAR = "extraNama";
    public static final String EXTRA_BULAN_BAYAR = "extraBulan";
    public static final String EXTRA_IURAN_BAYAR = "extraIuran";
    public static final String EXTRA_USERNAME_BAYAR = "extraUsername";
    public static final String EXTRA_KEY_BAYAR = "extraKey";
    public static final String EXTRA_VALIDASI_BAYAR = "extraValidasi";

    @BindView(R.id.txt_nama_pembayaran)
    TextView txtNamaPembayaran;
    @BindView(R.id.txt_bulan_pembayaran)
    TextView txtBulanPembayaran;
    @BindView(R.id.txt_jumlah_tagihan_pembayaran)
    TextView txtJumlahTagihanPembayaran;
    @BindView(R.id.button_select_image)
    Button buttonSelectImage;
    @BindView(R.id.button_upload_pembayaran)
    Button buttonUploadPembayaran;

    private final int IMG_REQUEST = 22;
    @BindView(R.id.img_bukti_pembayaran)
    ImageView imgBuktiPembayaran;

    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    Bitmap bitmap;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti_pembayaran);
        ButterKnife.bind(this);

        txtNamaPembayaran.setText(getIntent().getStringExtra(EXTRA_NAMA_BAYAR));
        txtBulanPembayaran.setText(getIntent().getStringExtra(EXTRA_BULAN_BAYAR));
        txtJumlahTagihanPembayaran.setText(getIntent().getStringExtra(EXTRA_IURAN_BAYAR));

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        buttonUploadPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void updateIuran(Iuran setIuran) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        String username = getIntent().getStringExtra(EXTRA_USERNAME_BAYAR);


        databaseReference.child("Iuran")
                .child(username)
                .setValue(setIuran)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UploadBuktiPembayaranActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }

    private void PilihGambar() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {

                bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imgBuktiPembayaran.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());



            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(UploadBuktiPembayaranActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String image_url = downloadUrl.toString();
                                    String key = getIntent().getStringExtra(EXTRA_KEY_BAYAR);
                                    String nama = getIntent().getStringExtra(EXTRA_NAMA_BAYAR);
                                    String bulan = getIntent().getStringExtra(EXTRA_BULAN_BAYAR);
                                    String iuran = getIntent().getStringExtra(EXTRA_IURAN_BAYAR);
                                    String validasi = getIntent().getStringExtra(EXTRA_VALIDASI_BAYAR);
                                    Iuran setIuran = new Iuran();
                                    setIuran.setNama(nama);
                                    setIuran.setBulan(bulan);
                                    setIuran.setIuran(iuran);
                                    setIuran.setImage(image_url);
                                    setIuran.setValidasi(validasi);
                                    updateIuran(setIuran);
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(UploadBuktiPembayaranActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}