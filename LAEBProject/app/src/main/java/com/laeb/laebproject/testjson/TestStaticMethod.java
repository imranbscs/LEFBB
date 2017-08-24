package com.laeb.laebproject.testjson;

import com.laeb.laebproject.model_expendible.ExpendbleItems;
import com.laeb.laebproject.model_expendible.Friday;
import com.laeb.laebproject.model_expendible.Monday;
import com.laeb.laebproject.model_expendible.Saturday;
import com.laeb.laebproject.model_expendible.Slot;
import com.laeb.laebproject.model_expendible.Sunday;
import com.laeb.laebproject.model_expendible.Thursday;
import com.laeb.laebproject.model_expendible.Tuesday;
import com.laeb.laebproject.model_expendible.Wednesday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariq on 8/23/2017.
 */

public class TestStaticMethod {

    public static ExpendbleItems getAll(){

        ExpendbleItems expendbleItems = new ExpendbleItems();

        Saturday saturday = new Saturday();
        saturday.setAvailable(1);
        ArrayList<Slot> satSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getSaturday().size(); i++) {
            String s = TestStaticMethod.getSaturday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            satSlots.add(slot);
        }
        saturday.setSlots(satSlots);


        Sunday sunday = new Sunday();
        sunday.setAvailable(1);
        ArrayList<Slot> sunSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getSunday().size(); i++) {
            String s = TestStaticMethod.getSunday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            sunSlots.add(slot);
        }

        sunday.setSlots(sunSlots);

        Monday monday = new Monday();
        monday.setAvailable(1);
        ArrayList<Slot> monSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getMonday().size(); i++) {
            String s = TestStaticMethod.getMonday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            monSlots.add(slot);
        }

        monday.setSlots(monSlots);

        Tuesday tuesday = new Tuesday();
        tuesday.setAvailable(1);
        ArrayList<Slot> tusSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getTuesday().size(); i++) {
            String s = TestStaticMethod.getTuesday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            tusSlots.add(slot);
        }

        tuesday.setSlots(tusSlots);

        Wednesday wednesday = new Wednesday();
        wednesday.setAvailable(1);
        ArrayList<Slot> wedSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getWednesDay().size(); i++) {
            String s = TestStaticMethod.getWednesDay().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            wedSlots.add(slot);
        }

        wednesday.setSlots(wedSlots);

        Thursday thursday = new Thursday();
        thursday.setAvailable(1);
        ArrayList<Slot> thrSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getThursday().size(); i++) {
            String s = TestStaticMethod.getThursday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            thrSlots.add(slot);
        }

        thursday.setSlots(thrSlots);

        Friday friday = new Friday();
        friday.setAvailable(1);
        ArrayList<Slot> friSlots = new ArrayList<>();
        for(int i = 0; i < TestStaticMethod.getFriday().size(); i++) {
            String s = TestStaticMethod.getFriday().get(i);
            String[] values = TestStaticMethod.getArrayy(s);
            Slot slot = new Slot();
            slot.setFrom(values[0]);
            slot.setTo(values[1]);
            slot.setRate(values[2]);
            friSlots.add(slot);
        }

        friday.setSlots(friSlots);

        expendbleItems.setSunday(sunday);
        expendbleItems.setMonday(monday);
        expendbleItems.setTuesday(tuesday);
        expendbleItems.setWednesday(wednesday);
        expendbleItems.setThursday(thursday);
        expendbleItems.setFriday(friday);
        expendbleItems.setSaturday(saturday);

        return expendbleItems;
    }


    public static List<String> getSunday(){

        List<String> cricket = new ArrayList<String>();
//        cricket.add("1600-1800-20");

        return cricket;
    }

    public static List<String> getMonday(){

        List<String> cricket = new ArrayList<String>();
        //cricket.add("1600-1800-20");

        return cricket;
    }

    public static List<String> getTuesday(){

        List<String> cricket = new ArrayList<String>();
        cricket.add("1600-1800-20");
        cricket.add("1900-2100-30");
        cricket.add("1000-1100-80");

        return cricket;
    }

    public static List<String> getWednesDay(){

        List<String> cricket = new ArrayList<String>();
//        cricket.add("1600-1800-20");
//        cricket.add("1900-2100-30");

        return cricket;
    }

    public static List<String> getThursday(){

        List<String> cricket = new ArrayList<String>();
//        cricket.add("1600-1800-20");
//        cricket.add("1900-2100-30");
//        cricket.add("1000-1100-80");

        return cricket;
    }

    public static List<String> getFriday(){

        List<String> cricket = new ArrayList<String>();
        //cricket.add("1600-1800-20");

        return cricket;
    }

    public static List<String> getSaturday(){

        List<String> cricket = new ArrayList<String>();
        cricket.add("1600-1800-20");
        cricket.add("1900-2100-30");
        cricket.add("1000-1100-80");

        return cricket;
    }

    public static String[] getArrayy(String str){
        String[] parts = str.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        return parts;
    }

}
