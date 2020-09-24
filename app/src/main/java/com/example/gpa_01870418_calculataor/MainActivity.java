package com.example.gpa_01870418_calculataor;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator progressAnimator;

    private EditText course1_grade;
    private EditText course2_grade;
    private EditText course3_grade;
    private EditText course4_grade;
    private EditText course5_grade;

    private int grade1 , grade2 , grade3 , grade4 , grade5;

    private ProgressBar course1_prog;
    private ProgressBar course2_prog;
    private ProgressBar course3_prog;
    private ProgressBar course4_prog;
    private ProgressBar course5_prog;

    private ProgressBar mainGPA_bar;

    private Button calculate;

    private TextView GPA_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course1_grade = (EditText) findViewById(R.id.course1_grade);
        course2_grade = (EditText) findViewById(R.id.course2_grade);
        course3_grade = (EditText) findViewById(R.id.course3_grade);
        course4_grade = (EditText) findViewById(R.id.course4_grade);
        course5_grade = (EditText) findViewById(R.id.course5_grade);

        course1_prog = (ProgressBar) findViewById(R.id.course1_prog);
        course2_prog = (ProgressBar) findViewById(R.id.course2_prog);
        course3_prog = (ProgressBar) findViewById(R.id.course3_prog);
        course4_prog = (ProgressBar) findViewById(R.id.course4_prog);
        course5_prog = (ProgressBar) findViewById(R.id.course5_prog);

        mainGPA_bar = (ProgressBar) findViewById(R.id.mainGPA_Bar);

        GPA_display = (TextView) findViewById(R.id.GPA_display);

        getCourseGrades(course1_grade , 1 , course1_prog);
        getCourseGrades(course2_grade , 2 , course2_prog);
        getCourseGrades(course3_grade , 3 , course3_prog);
        getCourseGrades(course4_grade , 4 , course4_prog);
        getCourseGrades(course5_grade , 5 , course5_prog);

        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                getGPA(grade1 , grade2 , grade3 , grade4 , grade5);
            }
        });

    }

    public void animateProgress(int grade , ProgressBar p){
        progressAnimator = ObjectAnimator.ofInt(p, "progress", grade);
        progressAnimator.setDuration(400);
        progressAnimator.start();
    }

    public void getCourseGrades(final EditText courseGradeText , final int id , final ProgressBar p){

        courseGradeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(courseGradeText.getText().toString().isEmpty() == true){
                    switch (id){
                        case 1:
                            grade1 = 0;
                            break;
                        case 2:
                            grade2 = 0;
                            break;
                        case 3:
                            grade3 = 0;
                            break;
                        case 4:
                            grade4 = 0;
                            break;
                        case 5:
                            grade5 = 0;
                            break;
                        default:
                            break;
                    }
                }else{
                    switch (id){
                        case 1:
                            grade1 = Integer.parseInt(courseGradeText.getText().toString());
                            break;
                        case 2:
                            grade2 = Integer.parseInt(courseGradeText.getText().toString());
                            break;
                        case 3:
                            grade3 = Integer.parseInt(courseGradeText.getText().toString());
                            break;
                        case 4:
                            grade4 = Integer.parseInt(courseGradeText.getText().toString());
                            break;
                        case 5:
                            grade5 = Integer.parseInt(courseGradeText.getText().toString());
                            break;
                        default:
                            break;
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(courseGradeText.getText().toString().isEmpty() == true){
                    animateProgress(0 , p);
                }else{
                    int grade = Integer.parseInt(courseGradeText.getText().toString());
                    if(grade >= 0 || grade <= 100){
                        animateProgress(grade , p);
                    }
                }
            }
        });

    }

    public void getGPA(int a , int b , int c , int d , int e){
        int GPA = (a + b + c + d + e) / 5;

        if(GPA <= 60){
            GPA_display.setTextColor(getResources().getColor(R.color.danger));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.progress_danger);
            mainGPA_bar.setProgressDrawable(progressDrawable);
        }else if(GPA >= 61 && GPA <= 79){
            GPA_display.setTextColor(getResources().getColor(R.color.medium));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.progress_medium);
            mainGPA_bar.setProgressDrawable(progressDrawable);
        }else if(GPA > 80){
            GPA_display.setTextColor(getResources().getColor(R.color.good));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.progress_good);
            mainGPA_bar.setProgressDrawable(progressDrawable);
        }

        animateProgress(GPA , mainGPA_bar);
        GPA_display.setText(String.valueOf(GPA));

    }
}
