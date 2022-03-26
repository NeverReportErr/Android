package com.example.loginapplication;

public class CreateStu {
    Student student;
    public Student[] setStudent(){
        Student[] students=new Student[20];
        String lit="文学";
        String pe="体育";
        String music="音乐";
        String art="美术";

        String[] stuHobby0=new String[4];
        stuHobby0[0]=lit;
        stuHobby0[1]=pe;
        stuHobby0[2]="";
        stuHobby0[3]="";
        student=new Student("张三","10010","男","计算机学院","软件工程",stuHobby0);
        students[0]=student;

        String[] stuHobby1=new String[4];
        stuHobby1[0]=lit;
        stuHobby1[1]="";
        stuHobby1[2]=music;
        stuHobby1[3]="";
        student=new Student("李四","10011","女","计算机学院","信息安全",stuHobby1);
        students[1]=student;

        String[] stuHobby2=new String[4];
        stuHobby2[0]="";
        stuHobby2[1]=pe;
        stuHobby2[2]=music;
        stuHobby2[3]="";
        student=new Student("王五","10012","男","计算机学院","物联网",stuHobby2);
        students[2]=student;

        String[] stuHobby3=new String[4];
        stuHobby3[0]="";
        stuHobby3[1]=music;
        stuHobby3[2]="";
        stuHobby3[3]=art;
        student=new Student("赵六","10013","女","计算机学院","物联网",stuHobby3);
        students[3]=student;

        String[] stuHobby4=new String[4];
        stuHobby4[0]=lit;
        stuHobby4[1]="";
        stuHobby4[2]="";
        stuHobby4[3]="";
        student=new Student("孙七","10014","男","计算机学院","软件工程",stuHobby4);
        students[4]=student;

        String[] stuHobby5=new String[4];
        stuHobby5[0]="";
        stuHobby5[1]=pe;
        stuHobby5[2]=music;
        stuHobby5[3]=art;
        student=new Student("周八","10015","男","计算机学院","信息安全",stuHobby5);
        students[5]=student;

        String[] stuHobby6=new String[4];
        stuHobby6[0]="";
        stuHobby6[1]=pe;
        stuHobby6[2]="";
        stuHobby6[3]=art;
        student=new Student("吴九","10016","男","电气学院","电气工程",stuHobby6);
        students[6]=student;

        String[] stuHobby7=new String[4];
        stuHobby7[0]="";
        stuHobby7[1]="";
        stuHobby7[2]=music;
        stuHobby7[3]="";
        student=new Student("郑十","10017","男","电气学院","电机工程",stuHobby7);
        students[7]=student;

        return students;
    }
}
