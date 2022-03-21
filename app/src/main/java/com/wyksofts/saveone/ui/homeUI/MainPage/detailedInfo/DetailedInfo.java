package com.wyksofts.saveone.ui.homeUI.MainPage.detailedInfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.wyksofts.saveone.R;
import com.wyksofts.saveone.ui.homeUI.HelperClasses.makeACall;
import com.wyksofts.saveone.ui.homeUI.PermissionCheck.checkCallPermission;
import com.wyksofts.saveone.util.AlertPopDiag;
import com.wyksofts.saveone.util.getBitmap;
import com.wyksofts.saveone.util.getRandomString;
import com.wyksofts.saveone.util.showAppToast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailedInfo extends Fragment implements OnMapReadyCallback {

    TextView name, description, mpesa, phone_number, country,
            bank_account_name, location, number_of_children, email;

    SupportMapFragment mapFragment;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    String coordinates;
    Double latitude, longitude;

    //values keys
    String pphone_number,pname,pemail, pbank_account, pbank_account_name;
    LatLng curr_location = null;

    //donate
    FloatingActionButton donate;
    Dialog donateDialog, mpesa_dialog;

    //firebase
    FirebaseUser user;
    FirebaseFirestore database;

    private  FirebaseFunctions mFunctions;


    public DetailedInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image);
        setSharedElementEnterTransition(transition);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseFirestore.getInstance();

        mpesa_dialog = new Dialog(getContext());

        donateDialog = new Dialog(getActivity(), R.style.DialogAnimation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_info, container, false);

        //shared preference
        pref = getContext().getSharedPreferences("OrphanageDetailedData", 0);
        editor = pref.edit();

        name = view.findViewById(R.id.orp_name);
        donate = view.findViewById(R.id.donate);
        description = view.findViewById(R.id.orp_description);
        mpesa = view.findViewById(R.id.orp_mpesa);
        phone_number = view.findViewById(R.id.orp_phone_number);
        bank_account_name = view.findViewById(R.id.orp_bank);
        location = view.findViewById(R.id.orp_location);
        number_of_children = view.findViewById(R.id.orp_number_message);
        email = view.findViewById(R.id.orp_email);
        country = view.findViewById(R.id.orp_country);

        //ViewCompat.setTransitionName(name, "name");

        getValues();

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.orp_map);

        //check if coordinates exits
       if(!coordinates.isEmpty()){
           //create a list of latlong from coordinates string
           List<String> latlong = Arrays.asList(coordinates.split(","));

           //set latitude and longitude
           latitude = Double.valueOf(latlong.get(0));
           longitude = Double.valueOf(latlong.get(1));

           curr_location = new LatLng(latitude,longitude);


           if (curr_location !=null){
               if (mapFragment != null) {
                   mapFragment.getMapAsync(this);
               }
           }else{
               new showAppToast().showFailure(getContext(),"Oops, Unable to get location history.");
           }

       }else{
           new showAppToast().showFailure(getContext(),"Oops, Unable to locate orphanage at the moment.");
       }

       //call phone
       phone_number.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(new checkCallPermission(getContext(),pphone_number,getActivity()).isCallPermissionGranted()){
                   new makeACall().callAction(getContext(),pphone_number);
               }else{
                   new showAppToast().showFailure(getContext(),"Your device is not supported");
               }
           }
       });

       //email the orphanage
       email.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent("android.intent.action.SEND");
               intent.putExtra("android.intent.extra.EMAIL", new String[] { pemail });
               intent.setType("text/html");
               intent.setPackage("com.google.android.gm");
               getContext().startActivity(Intent.createChooser(intent, "Send mail\t"+pname));
           }
       });

       //copy bank account number
       bank_account_name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
               ClipData clip = ClipData.newPlainText(pbank_account_name, pbank_account);
               clipboard.setPrimaryClip(clip);

               new showAppToast().showSuccess(getContext(),"Bank Account Copied");
           }
       });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show donate dialog
                showDonateDialog();
            }
        });

        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMpesaDialog();
            }
        });

    }

    private void showMpesaDialog() {
        mpesa_dialog.setContentView(R.layout.mpesa_dialog);

        EditText phone = mpesa_dialog.findViewById(R.id.donor_phone_number);
        EditText amount = mpesa_dialog.findViewById(R.id.donor_amount);
        ProgressBar loading_bar = mpesa_dialog.findViewById(R.id.loading_bar);

        mpesa_dialog.findViewById(R.id.donate_na_mpesa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMoney(phone,amount,loading_bar);
            }
        });

        mpesa_dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpesa_dialog.dismiss();
            }
        });
        mpesa_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        mpesa_dialog.setCancelable(false);
        mpesa_dialog.show();
    }

    private void sendMoney(EditText phone, EditText amount, ProgressBar loading_bar) {

        String phoneNumber = phone.getText().toString();
        String Amount = amount.getText().toString();


        loading_bar.setVisibility(View.VISIBLE);

        //check validity of a number
        if (phoneNumber.length() != 10) {
            phone.setError("Invalid number");
            return;
        }
        //check validity of a number
        else if (Amount.isEmpty()) {
            amount.setError("Amount should be more than 0");
            return;
        }


        onSendMoney(phoneNumber,Amount);

    }


    //Call Mpesa API
    private Task<String> onSendMoney(String phone_number, String amount) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("phoneNumber", phone_number);
        data.put("businessShortCode", "174379");
        data.put("amount", amount);


        mFunctions = FirebaseFunctions.getInstance();

        return mFunctions
                .getHttpsCallable("callMpesaAPI")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();

                        new showAppToast().showSuccess(getContext(),""+result);

                        return result;
                    }
                });
    }

    //show donating dialog
    private void showDonateDialog() {
        donateDialog.setContentView(R.layout.donate_dialog);

        donateDialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donateDialog.dismiss();
            }
        });
        EditText phone_number = donateDialog.findViewById(R.id.donor_phone_number);
        EditText location = donateDialog.findViewById(R.id.donor_location);
        EditText others = donateDialog.findViewById(R.id.others);

        donateDialog.findViewById(R.id.donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordDonation(phone_number,location,others);
            }
        });
        donateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        donateDialog.setCancelable(false);
        donateDialog.show();
    }



    //add donation to database
    private void recordDonation(EditText phone_number, EditText location, EditText others) {

        final String donor_phone_number = phone_number.getText().toString();
        final String donor_location = location.getText().toString();
        final String other_donation = others.getText().toString();
        String clothes, school, food, health;

        CheckBox food_stuffs = donateDialog.findViewById(R.id.food_stuffs);
        CheckBox clothing = donateDialog.findViewById(R.id.clothings);
        CheckBox education_materials = donateDialog.findViewById(R.id.education_materials);

        if (food_stuffs.isChecked()){
            food = "Yes";
        }else{
            food ="";
        }
        if (clothing.isChecked()){
            clothes = "Yes";
        }else{
            clothes = "";
        }
        if (education_materials.isChecked()){
            school = "Yes";
        }else{
            school = "";
        }
        if (education_materials.isChecked()){
            health = "Yes";
        }else{
            health = "";
        }

        if (TextUtils.isEmpty(donor_phone_number)){
            phone_number.setError("Phone is required");
        }else if (TextUtils.isEmpty(donor_location)){
            location.setError("Location is required");
        }


        String orphanage_email = pref.getString("email",null);

        Map<String, Object> data = new HashMap<>();;
        Map<String, Object> docData = new HashMap<>();

        if (user !=null) {
            data.put("clothes", clothes);
            data.put("educational_materials", school);
            data.put("email", user.getEmail());
            data.put("food", food);
            data.put("health", health);
            data.put("location", donor_location);
            data.put("name", user.getDisplayName());
            data.put("other", other_donation);
            data.put("phone_number", donor_phone_number);
            docData.put(user.getEmail(), data);
        }
        else{

            String randomName = new getRandomString().getRandomString(10);
            data.put("clothes", clothes);
            data.put("educational_materials", school);
            data.put("email", randomName);
            data.put("food", food);
            data.put("health", health);
            data.put("location", donor_location);
            data.put("name", "");
            data.put("other", other_donation);
            data.put("phone_number", donor_phone_number);
            docData.put(randomName, data);
        }

        database.collection("Donations")
                .document(orphanage_email)
                .set(docData, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        new showAppToast().showSuccess(getContext(),
                                "Your donation was recorded successfully\t"+
                                        pname
                                        +"\t will contact you for more information");
                        donateDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        new AlertPopDiag(getContext()).show(
                                "Oops we have experienced an error while receiving your donation",
                                "Connection error");
                    }
                });
    }




    @SuppressLint("SetTextI18n")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        //get orphanage name
        pname = pref.getString("name","Orphanage Home");
        name.setText("About\t"+pname);

        googleMap.addMarker(new MarkerOptions()
                .position(curr_location)
                .title(pname)
                .icon(BitmapDescriptorFactory.fromBitmap(new getBitmap()
                        .getBitmap(String.valueOf(R.drawable.custom_maker),
                                120,120,getContext())))
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_maker))
        );

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curr_location, 12.0f));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        googleMap.animateCamera(zoom);
    }



    //get detailed data for the orphanage
    @SuppressLint("SetTextI18n")
    private void getValues() {

        String plocation = pref.getString("location",null);
        coordinates = pref.getString("coordinates","0,0");

        String pcountry = pref.getString("country",null);
        String pdescription = pref.getString("description",null);
        String pnoc = pref.getString("number_of_children",null);
        pphone_number = pref.getString("phone_number",null);
        pbank_account = pref.getString("bank_account", null);
        pbank_account_name = pref.getString("bank_account_name",null);
        String ptill_number = pref.getString("till_number",null);
        pemail = pref.getString("email",null);
        String pwhat_needed = pref.getString("what_needed",null);
        String pverified = pref.getString("verified",null);

        location.setText(plocation);
        country.setText(pcountry);
        description.setText(pdescription);
        number_of_children.setText("We have\t"+pnoc+"\tchildren's in need of\t"+pwhat_needed);
        phone_number.setText(pphone_number);
        bank_account_name.setText("Bank Account:\t"+pbank_account+"\tBank Name:\t"+pbank_account_name);
        mpesa.setText("Donate na mpesa ("+ptill_number+")");
        email.setText(pemail);
    }


}