package com.example.loginapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class ActivityStudent extends AppCompatActivity {
    String stuName,stuNum,stuSex,stuInst,stuSpec;//学生的各个信息
    String[] stuHobby;//学生的兴趣数组
    Spinner spInst,spSpec;//下拉框列表
    ArrayAdapter<String> instAdapter;
    ArrayAdapter<String> specAdapter;//下拉框对应的适配器
    String institute="请选择学院";//学院默认值
    String speciality="请选择专业";//专业默认值
    final String[]instList=new String[]{"请选择学院","计算机学院","电气学院"};//学院列表
    final String[]computerList=new String[]{"请选择专业","软件工程","信息安全","物联网"};//计算机学院专业列表
    final String[]electricalList=new String[]{"请选择专业","电气工程","电机工程"};//电气学院专业列表
    CheckBox checkBox0,checkBox1,checkBox2,checkBox3;//复选框
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //获取MainActivity页面的学生数组
        Intent stuIntent=getIntent();
        Bundle stuBundle=stuIntent.getExtras();//获取MainActivity传输的bundle
        Student[] students=(Student[]) stuBundle.getSerializable("students");//需要添加的学生数组
        Student[] editStudents=(Student[]) stuBundle.getSerializable("editStudents");//需要编辑的学生数组

        RadioButton radioA=findViewById(R.id.radioA);//实例化性别选项男
        RadioButton radioB=findViewById(R.id.radioB);//实例化性别选项女
        if(radioA.isChecked()){//获取选中的性别
            stuSex=radioA.getText().toString();
        }else if (radioB.isChecked()){
            stuSex=radioB.getText().toString();
        }

        spInst= findViewById(R.id.spInst);//实例化学院下拉框
        spSpec= findViewById(R.id.spSpec);//实例化专业下拉框
        instAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, instList);//创建适配器
        spInst.setAdapter(instAdapter);//学院下拉框和适配器关联
        spInst.setSelection(0,true);//学院默认选择
        specAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, computerList);//创建适配器
        spSpec.setAdapter(specAdapter);//专业下拉框和适配器关联
        spSpec.setSelection(0,true);//专业默认选择
        //实例化兴趣复选框
        checkBox0=findViewById(R.id.lit);//文学
        checkBox1=findViewById(R.id.pe);//体育
        checkBox2=findViewById(R.id.music);//音乐
        checkBox3=findViewById(R.id.art);//美术

        if(stuBundle.getSerializable("editStudents")!=null){
            //获取需要被编辑的学生信息并显示
            int position=stuBundle.getInt("position");
            EditText editName=findViewById(R.id.stuName);//实例化姓名输入框
            EditText editNum=findViewById(R.id.stuNum);//实例化学号输入框

            editName.setText(editStudents[position].getStuName());//显示选中的学生姓名
            editNum.setText(editStudents[position].getStuNum());//显示选中的学生学号
            if(editStudents[position].getStuSex().equals("男")){//勾选选中学生的性别
                radioA.setChecked(true);
                radioB.setChecked(false);
            }else{
                radioA.setChecked(false);
                radioB.setChecked(true);
            }
            //显示选中学生的学院和专业
            if (editStudents[position].getStuInst().equals("计算机学院")){
                spInst.setSelection(1,true);
                specAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, computerList);
                spSpec.setAdapter(specAdapter);
                switch (editStudents[position].getStuSpec()){
                    case "软件工程":
                        spSpec.setSelection(1,true);
                        break;
                    case "信息安全":
                        spSpec.setSelection(2,true);
                        break;
                    case "物联网":
                        spSpec.setSelection(3,true);
                        break;
                }
            }else{
                spInst.setSelection(2,true);
                specAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, electricalList);
                spSpec.setAdapter(specAdapter);
                switch (editStudents[position].getStuSpec()){
                    case "电气工程":
                        spSpec.setSelection(1,true);
                        break;
                    case "电机工程":
                        spSpec.setSelection(2,true);
                }
            }
            //获取并显示选中学生的兴趣数组
            stuHobby=editStudents[position].getStuHobby();
            if(!stuHobby[0].equals(""))
                checkBox0.setChecked(true);
            if(!stuHobby[1].equals(""))
                checkBox1.setChecked(true);
            if(!stuHobby[2].equals(""))
                checkBox2.setChecked(true);
            if(!stuHobby[3].equals(""))
                checkBox3.setChecked(true);
        }

        //下拉框列表的监听器
        spInst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                institute=adapterView.getItemAtPosition(i).toString();//获取选中的学院
                if(institute.equals("计算机学院")){//下拉框的二级联动
                    specAdapter= new ArrayAdapter<>(ActivityStudent.this, android.R.layout.simple_spinner_item, computerList);
                    spSpec.setAdapter(specAdapter);
                }else if(institute.equals("电气学院")){
                    specAdapter= new ArrayAdapter<>(ActivityStudent.this, android.R.layout.simple_spinner_item, electricalList);
                    spSpec.setAdapter(specAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });
        spSpec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                speciality=adapterView.getItemAtPosition(i).toString();//获取选中的专业
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button submit= findViewById(R.id.submit);//实例化提交按钮
        submit.setOnClickListener(view -> {//提交按钮监听器，获取填写内容并利用bundle提交给MainActivity

            if(radioA.isChecked()){
                stuSex=radioA.getText().toString();
            }else if(radioB.isChecked()){
                stuSex=radioB.getText().toString();
            }

            stuHobby=new String[4];//实例化学生兴趣数组
            Arrays.fill(stuHobby, "");//空字符串初始化数组
            if(checkBox0.isChecked())
                stuHobby[0]=checkBox0.getText().toString();
            if(checkBox1.isChecked())
                stuHobby[1]=checkBox1.getText().toString();
            if(checkBox2.isChecked())
                stuHobby[2]=checkBox2.getText().toString();
            if(checkBox3.isChecked())
                stuHobby[3]=checkBox3.getText().toString();//获取页面中选中的学生爱好

            //获取输入的姓名、学号，以及选中的学院、专业
            stuName=((EditText)findViewById(R.id.stuName)).getText().toString().trim();
            stuNum=((EditText)findViewById(R.id.stuNum)).getText().toString().trim();
            stuInst=((Spinner)findViewById(R.id.spInst)).getSelectedItem().toString().trim();
            stuSpec=((Spinner)findViewById(R.id.spSpec)).getSelectedItem().toString().trim();

            if(!stuName.equals("")||!stuNum.equals("")||!stuInst.equals("")||!stuSpec.equals("")){
                Intent intent=new Intent(ActivityStudent.this,MainActivity.class);
                Bundle bundle=new Bundle();
                //创建一个Student对象用于存储所有获得的学生信息
                Student student=new Student();
                student.setStuName(stuName);
                student.setStuNum(stuNum);
                student.setStuSex(stuSex);
                student.setStuInst(stuInst);
                student.setStuSpec(stuSpec);
                student.setStuHobby(stuHobby);
                Student[] newStudents;
                //判断该页面需要添加学生还是编辑学生信息
                if(stuBundle.getString("mode").equals("addStu")) {
                    newStudents = student.addStudent(students, student);
                    bundle.putSerializable("newStudents",newStudents);
                    Toast.makeText(ActivityStudent.this,"添加信息成功!",Toast.LENGTH_SHORT).show();
                }else if(stuBundle.getString("mode").equals("editStu")){
                    int position=stuBundle.getInt("position");
                    editStudents[position]=student;
                    newStudents = editStudents;
                    bundle.putSerializable("newStudents",newStudents);
                    Toast.makeText(ActivityStudent.this,"修改信息成功!",Toast.LENGTH_SHORT).show();
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }else {
                Toast.makeText(ActivityStudent.this,"请将信息填写完整!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}