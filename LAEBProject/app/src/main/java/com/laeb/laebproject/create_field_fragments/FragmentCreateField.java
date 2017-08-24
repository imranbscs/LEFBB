package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tariq on 8/19/2017.
 */

public class FragmentCreateField extends Fragment implements View.OnClickListener {

    String Type;
    EditText ed_name;
    EditText ed_city;
    EditText ed_size;
    TextView grass_pitch;
    ImageView addImage;
    TextView clay_pitch;

    private static final int RESULT_OK = -1;
    Bitmap image;

    private static int RESULT_LOAD_IMAGE = 1;


    View myView;
    private ImageView profImg;

    final FieldInfo fieldInfo = new FieldInfo();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_field, container, false);
        View b = (View) view.findViewById(R.id.nextBtn);

        ed_name = (EditText) view.findViewById(R.id.ed_name);
        ed_city = (EditText) view.findViewById(R.id.ed__district);
        ed_size = (EditText) view.findViewById(R.id.field_size);
        grass_pitch = (TextView) view.findViewById(R.id.grass_pitch);
        clay_pitch = (TextView) view.findViewById(R.id.clay_pitch);
        profImg = (ImageView) view.findViewById(R.id.picture);
        addImage = (ImageView) view.findViewById(R.id.addImage);
        grass_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = (ImageView) getView().findViewById(R.id.imgGrass);
                imageView.setImageResource(R.drawable.tickselected);
                ImageView imageView2 = (ImageView) getView().findViewById(R.id.imgClay);
                imageView2.setImageResource(R.drawable.tick);
                fieldInfo.type  = "Grass";
            }
        });
        clay_pitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) getView().findViewById(R.id.imgClay);
                imageView.setImageResource(R.drawable.tickselected);
                ImageView imageView2 = (ImageView) getView().findViewById(R.id.imgGrass);
                imageView2.setImageResource(R.drawable.tick);
                fieldInfo.type  = "Clay";
            }
        });

        b.setOnClickListener(this);
        addImage.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:

                fieldInfo.name = ed_name.getText().toString();
               // fieldInfo.city = ed_city.getText().toString();
                fieldInfo.size = ed_size.getText().toString();


                FieldFacilities fragment = new FieldFacilities();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity) getActivity()).addFragment(fragment);
                break;

            case R.id.addImage:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = this.getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            profImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));

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

}
