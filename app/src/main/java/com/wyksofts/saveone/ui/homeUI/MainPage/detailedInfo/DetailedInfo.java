package com.wyksofts.saveone.ui.homeUI.MainPage.detailedInfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.functions.FirebaseFunctions;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.wyksofts.saveone.Adapters.AlbumAdapter.AlbumsAdapter;
import com.wyksofts.saveone.R;
import com.wyksofts.saveone.models.Albums.AlbumModels;
import com.wyksofts.saveone.ui.homeUI.HelperClasses.makeACall;
import com.wyksofts.saveone.ui.homeUI.HelperClasses.showDonateDialog;
import com.wyksofts.saveone.ui.homeUI.HelperClasses.showMpesaDialog;
import com.wyksofts.saveone.ui.homeUI.PermissionCheck.checkCallPermission;
import com.wyksofts.saveone.util.getBitmap;
import com.wyksofts.saveone.util.showAppToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailedInfo extends Fragment implements OnMapReadyCallback {

    TextView name, description,mpesa_txt, phone_number, country,
            bank_account_name, location, number_of_children, email;
    LinearLayout mpesa;
    String ptill_number;

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
    Dialog donateDialog;

    //album images variables
    List<AlbumModels> listdata;
    AlbumsAdapter adapter;
    SliderView recyclerAlbumView;



    public DetailedInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image);
        setSharedElementEnterTransition(transition);

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

        listdata = new ArrayList<>();

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
        mpesa_txt = view.findViewById(R.id.mpesa_txt);
        recyclerAlbumView = view.findViewById(R.id.slider_view);

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
                String orphanage_email = pref.getString("email",null);
                new showDonateDialog(getContext())
                        .showDonateDialog(pname,orphanage_email);
            }
        });

        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new showMpesaDialog(getContext()).mpesaDialog(ptill_number,false);
            }
        });

        showAlbumImages();
    }

    private void showAlbumImages() {


        int[] images = {R.drawable.imggg, R.drawable.imgg};

        for (int i = 0; i < images.length; i++) {
            listdata.add(new AlbumModels(images[i]));
        }

        recyclerAlbumView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        recyclerAlbumView.setSliderTransformAnimation(SliderAnimations.CUBEINSCALINGTRANSFORMATION);
        recyclerAlbumView.setScrollTimeInSec(4);
        recyclerAlbumView.startAutoCycle();

        recyclerAlbumView.setSliderAdapter(new AlbumsAdapter(getContext(),listdata));
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
        ptill_number = pref.getString("till_number",null);
        pemail = pref.getString("email",null);
        String pwhat_needed = pref.getString("what_needed",null);
        String pverified = pref.getString("verified",null);

        location.setText(plocation);
        country.setText(pcountry);
        description.setText(pdescription);
        number_of_children.setText("We have\t"+pnoc+"\tchildren's in need of\t"+pwhat_needed);
        phone_number.setText(pphone_number);
        bank_account_name.setText("Bank Account:\t"+pbank_account+"\tBank Name:\t"+pbank_account_name);
        mpesa_txt.setText("Donate na mpesa ("+ptill_number+")");
        email.setText(pemail);
    }


}