package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.general.GlobelList;
import com.laeb.laebproject.general.Globels;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/19/2017.
 */

public class FragmentCreateField extends Fragment implements View.OnClickListener {

    String Type;
    EditText ed_name;
    EditText ed_city;
    EditText ed_size;
    TextView grass_pitch, artificialPitch;
    ImageView addImage, deleteImg;
    TextView clay_pitch;
    int mCity_Id = 0;
    public final int IMG_REQUEST = 1;
    Spinner spn_city;
    Spinner spn_acc;List<com.laeb.laebproject.model_create_team.list_city_and_fields.City> citis;
    List<String> cityStr;
    private static final int RESULT_OK = -1;
    Bitmap image;
    public Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;
    View myView;
    private ImageView profImg;
    String image64String = "";
    final FieldInfo fieldInfo = new FieldInfo();
    ImageView imageViewArtifitial, imageViewClay, imageViewGrass;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_field, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);

        ed_name = (EditText) view.findViewById(R.id.ed_name);
        ed_city = (EditText) view.findViewById(R.id.ed__district);
        ed_size = (EditText) view.findViewById(R.id.field_size);
        grass_pitch = (TextView) view.findViewById(R.id.grass_pitch);
        clay_pitch = (TextView) view.findViewById(R.id.clay_pitch);
        artificialPitch = (TextView) view.findViewById(R.id.artificial_pitch);
        profImg = (ImageView) view.findViewById(R.id.picture);
        addImage = (ImageView) view.findViewById(R.id.addImage);
        spn_acc = (Spinner) view.findViewById(R.id.accamodations);
        spn_city = (Spinner) view.findViewById(R.id.ed_city);
        deleteImg = (ImageView) view.findViewById(R.id.delete);

        imageViewArtifitial = (ImageView) view.findViewById(R.id.at_img);
        imageViewClay = (ImageView) view.findViewById(R.id.imgClay);
        imageViewGrass = (ImageView) view.findViewById(R.id.imgGrass);


        SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, groundSize());
        spn_acc.setAdapter(adap);

        getCityStr();           //get the city name and map them in the city spinner.

        artificialPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPitchType(imageViewArtifitial, "Artificial");
            }
        });
        grass_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPitchType(imageViewGrass, "Grass");
            }
        });
        clay_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPitchType(imageViewClay, "Clay");
            }
        });

        b.setOnClickListener(this);
        deleteImg.setOnClickListener(this);
        addImage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if(!(validation())) {
                    return;
                }
                    fieldInfo.name = ed_name.getText().toString();
                    fieldInfo.size = ed_size.getText().toString();
                    fieldInfo.city = mCity_Id + "";
                    fieldInfo.capacity = spn_acc.getSelectedItem().toString().substring(0, 1);

                    FieldFacilities fragment = new FieldFacilities();
                    Bundle args = new Bundle();
                    CustomBinder oCustom = new CustomBinder();
                    oCustom.setList(fieldInfo);
                    args.putSerializable("complexObject", oCustom);
                    fragment.setArguments(args);
                    ((CreateFieldActivity) getActivity()).addFragment(fragment);
                    break;

            case R.id.addImage:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);
                break;

            case R.id.delete:
                Globels.CREATE_FIELD_IMAGE = "[{\"image\":\"base64string1\"}]";
                profImg.setImageResource(R.drawable.pic_ground);
                Toast.makeText(getActivity(), "No Image selected..", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean validation() {
        boolean b = true;
        if (ed_name.getText().toString().trim().equals("") || ed_name.getText().toString().trim().length()<1) {
            ed_name.setError(getString(R.string.invalidName));
            return false;
        } else {
            ed_name.setError(null);
        }

        if (ed_city.getText().toString().trim().equals("") || ed_city.getText().toString().trim().length()<1) {
            ed_city.setError(getString(R.string.invalidDistrictName));
            return false;
        } else {
            ed_city.setError(null);
        }

        if (ed_size.getText().toString().trim().equals("") || ed_size.getText().toString().trim().length()<1) {
            ed_size.setError(getString(R.string.invalidGroundSize));
            return false;
        } else {
            ed_size.setError(null);
        }
        return true;
    }

    public ArrayList<String> groundSize(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("6 X 6");
        strings.add("7 X 7");
        strings.add("8 X 8");
        strings.add("9 X 9");
        strings.add("10 X 10");
        strings.add("11 X 11");
        return strings;
    }

    public void getPitchType(ImageView img, String pitchType){
        imageViewArtifitial.setImageResource(R.drawable.tick);
        imageViewClay.setImageResource(R.drawable.tick);
        imageViewGrass.setImageResource(R.drawable.tick);
        img.setImageResource(R.drawable.tickselected);
        fieldInfo.type  = pitchType;
    }

    void getCityStr(){
        citis = GlobelList.cities;
        cityStr = new ArrayList<>();
        final List<com.laeb.laebproject.model_create_team.list_city_and_fields.City> cc = GlobelList.cities;
        Toast.makeText(getActivity(), GlobelList.cities.size()+"  ", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < GlobelList.cities.size(); i++){
            cityStr.add(GlobelList.cities.get(i).getCityName());
        }
        //spn_city = (Spinner) getView().findViewById(R.id.ed_city);
        SpinnerAdapter adap = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, cityStr);
        spn_city.setAdapter(adap);
        spn_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                com.laeb.laebproject.model_create_team.list_city_and_fields.City areaName = (com.laeb.laebproject.model_create_team.list_city_and_fields.City) cc.get(position);
                mCity_Id = areaName.getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                profImg.setImageBitmap(bitmap);
                image64String = imageToString(bitmap);
                Globels.CREATE_FIELD_IMAGE = image64String;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
