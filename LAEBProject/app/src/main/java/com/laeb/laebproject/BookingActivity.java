package com.laeb.laebproject;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.laeb.laebproject.fragments_booking.FragmentSearchFacilities;
import com.laeb.laebproject.fragments_booking.FragmentsBooking;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by Kanwal on 8/16/2017.
 */

public class BookingActivity extends AppCompatActivity implements View.OnClickListener{


    String Name_Of_Day;
    Integer Number_Of_Day;
    Integer Day_OF_Week;
    TextView lbl_viwDate4_1;
    TextView lbl_viwDate4_2;

    TextView lbl_viwDate5_1;
    TextView lbl_viwDate5_2;

    TextView lbl_viwDate6_1;
    TextView lbl_viwDate6_2;

    TextView lbl_viwDate7_1;
    TextView lbl_viwDate7_2;

    TextView lbl_viwDate2;
    TextView lbl_viwDate1;
    TextView lbl_viwDate2_1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking);

       // lbl_viwDate4_1 = (TextView) findViewById(R.id.lbl_viwDate4_1);
       // lbl_viwDate4_2 = (TextView) findViewById(R.id.lbl_viwDate4_2);

      //  lbl_viwDate5_1 = (TextView) findViewById(R.id.lbl_viwDate5_1);
      //  lbl_viwDate5_2 = (TextView) findViewById(R.id.lbl_viwDate5_2);

      //  lbl_viwDate6_1 = (TextView) findViewById(R.id.lbl_viwDate6_1);
     //   lbl_viwDate6_2 = (TextView) findViewById(R.id.lbl_viwDate6_2);

       // lbl_viwDate7_1 = (TextView) findViewById(R.id.lbl_viwDate7_1);
      //  lbl_viwDate7_2 = (TextView) findViewById(R.id.lbl_viwDate7_2);

      //  lbl_viwDate2 = (TextView) findViewById(R.id.lbl_viwDate2);
       // lbl_viwDate1 = (TextView) findViewById(R.id.lbl_viwDate1);
      //  lbl_viwDate2_1 = (TextView) findViewById(R.id.lbl_viwDate2_1);


        Calendar cal = Calendar.getInstance();
        Integer Day_OF_Week = cal.DAY_OF_MONTH;

        String[] weekdayArray = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun", "Sun"};
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        if (Calendar.MONDAY == dayOfWeek) {
            cal.set(Calendar.DATE, 1);
            weekDay = "Mon";
            lbl_viwDate5_1.setText("" + Day_OF_Week);
            lbl_viwDate5_2.setText(weekdayArray[Day_OF_Week]);

            lbl_viwDate6_1.setText("" + Day_OF_Week);
            lbl_viwDate6_2.setText(weekdayArray[Day_OF_Week]);
        } else if (Calendar.TUESDAY == dayOfWeek) weekDay = "Tue";
        else if (Calendar.WEDNESDAY == dayOfWeek) weekDay = "Wen";
        else if (Calendar.THURSDAY == dayOfWeek) weekDay = "Thu";
        else if (Calendar.FRIDAY == dayOfWeek) weekDay = "Fri";
        else if (Calendar.SATURDAY == dayOfWeek) {
            Calendar calendar = Calendar.getInstance();

                /*int thisYear = calendar.get(Calendar.YEAR);
                Log.i("","thisYear" +thisYear);

                int thisMonth = calendar.get(Calendar.MONTH);
                Log.i("", "@ thisMonth : " + thisMonth);*/

            int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
            Log.i("", "$ thisDay : " + thisDay);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int thisDay1 = calendar.get(Calendar.DAY_OF_MONTH);

//            lbl_viwDate4_1.setText("" + thisDay);
//            lbl_viwDate4_2.setText(dayOfWeek);

        //    lbl_viwDate5_1.setText("" + thisDay1);
         //   lbl_viwDate5_2.setText("Sun");
        } else if (
                Calendar.SUNDAY == dayOfWeek) {

            Calendar calendar = Calendar.getInstance();
            int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
            Log.i("", "$ thisDay : " + thisDay);

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int thisDay1 = calendar.get(Calendar.DAY_OF_MONTH);

         //   lbl_viwDate4_1.setText("" + thisDay);
          //  lbl_viwDate4_2.setText(dayOfWeek);

        //    lbl_viwDate5_1.setText("j");
        //    lbl_viwDate5_2.setText("Mon");
        }
        System.out.println(weekDay);




        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
         FragmentsBooking fragmentsBooking= new FragmentsBooking();
        addFragment(fragmentsBooking);
    }

    public void addFragment(Fragment f){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, f);
        fragmentTransaction.commit();
    }

    public void saveClicked(View view) {
        //startActivity(new Intent(this, FullProfileActivity.class));
    }

    public String getShortDayName(int day) {
        Calendar c = Calendar.getInstance();
        c.set(2011, 7, 1, 0, 0, 0);
        c.add(Calendar.DAY_OF_MONTH, day);
        return String.format("%ta", c);
    }

    @Override
    public void onClick(View v) {
        ImageView oImage = (ImageView) v;
        int imgResource = R.drawable.tickselected;
        oImage.setImageResource(imgResource);


    }
}
