package com.example.guruapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.muddzdev.styleabletoast.StyleableToast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import Database.DBHelper;
import Model.ExamMarkDTO;

public class Performance extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_performance);
//    }
    TableLayout tableLayout;
    View v;
    Button btnSearch;
    List examMarksList;
    DBHelper db;
    EditText examId;
    Spinner spinner;
    List<ExamMarkDTO> d1;
    String examCenterValue;
    int count =0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.activity_performance,container,false);
         btnSearch = v.findViewById(R.id.btnSearch);


        String [] values =
                {"Galle","Mathara","Hambantota"};
        Spinner spinner =(Spinner) v.findViewById(R.id.centSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        db = new DBHelper(getActivity());

        examId = v.findViewById(R.id.searchExamId);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                examCenterValue = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        addHeaders();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 d1 = db.performance(examId.getText().toString(),examCenterValue);


                 if(count!=0 && d1.size()==0){
                     removeRows();
                     count=0;
                 }

                 if(d1.size()==0){

                     StyleableToast.makeText(getActivity(), "Marks List is Not Released ",R.style.mytoast).show();

                 }else{

                     initViews();
                     List<ExamMarkDTO> e = new ArrayList<>();

                     if(count!=0 ){
                         removeRows();
                         count=0;
                     }

                    for (ExamMarkDTO f : d1){

                        ExamMarkDTO d1 = new ExamMarkDTO();

                        d1.setExam_ID(f.getExam_ID());
                        d1.setStudent_Center(f.getStudent_Center());
                        d1.setStudent_Id(f.getStudent_Id());
                        d1.setStudent_Marks(f.getStudent_Marks());
                        e.add(d1);
                        count+=1;

                        System.out.println(count);

                    }
                 }



            }
        });

        return v;
    }

    public void initViews(){
        tableLayout = (TableLayout)v.findViewById(R.id.tbl_layout);
        addRows();


    }

    public void addHeaders() {

        TableLayout tl = v.findViewById(R.id.tbl_layout);
        TableRow tr = new TableRow(getActivity());
        tr.setLayoutParams(getLayoutParams());

        //  tr.addView(getTextView(0, "Auditor id", Color.WHITE, Typeface.BOLD, R.color.colorAccent));
        tr.addView(getTextView(0, "ExamId", Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
        tr.addView(getTextView(0, "StudentId", Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
        tr.addView(getTextView(0, "Marks", Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
        tr.addView(getTextView(0, "Center", Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));

        tl.addView(tr, getTblLayoutParams());
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
       // tv.setOnClickListener(getActivity());
        return tv;
    }


    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }



    public void addRows(){
        System.out.println("Add Rows");
        // Collections.reverse(trainScheduleList);
        for (int i = 0; i < d1.size(); i++) {

            TableRow tr = new TableRow(getActivity());
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getRowsTextView(0, d1.get(i).getExam_ID(), Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, d1.get(i).getStudent_Id(), Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, Double.toString(d1.get(i).getStudent_Marks()), Color.BLACK, Typeface.BOLD ,R.drawable.cell_shape ));
            tr.addView(getRowsTextView(0, d1.get(i).getStudent_Center(), Color.BLACK, Typeface.BOLD ,R.drawable.cell_shape ));
            tableLayout.addView(tr, getTblLayoutParams());

        }

    }

    public void removeRows(){
        tableLayout.removeViews(1,count);
    }

    private TextView getRowsTextView(int id, String title, int color, int typeface,int bgColor) {
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        //tv.setOnClickListener(this);
        return tv;
    }


}
