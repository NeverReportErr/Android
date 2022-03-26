package com.example.loginapplication;

import java.io.Serializable;

public class Student implements Serializable {
    String stuName;//学生姓名
    String StuNum;//学号
    String stuSex;//性别
    String stuInst;//学院
    String stuSpec;//专业
    String[] stuHobby;//兴趣
    public Student(){}
    public Student(String stuName, String stuNum, String stuSex, String stuInst, String stuSpec, String[] stuHobby) {
        this.stuName = stuName;
        this.StuNum = stuNum;
        this.stuSex = stuSex;
        this.stuInst = stuInst;
        this.stuSpec = stuSpec;
        this.stuHobby = stuHobby;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNum() {
        return StuNum;
    }

    public void setStuNum(String stuNum) {
        StuNum = stuNum;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuInst() {
        return stuInst;
    }

    public void setStuInst(String stuIns) {
        this.stuInst = stuIns;
    }

    public String getStuSpec() {
        return stuSpec;
    }

    public void setStuSpec(String stuSpec) {
        this.stuSpec = stuSpec;
    }

    public String[] getStuHobby() {
        return stuHobby;
    }

    public void setStuHobby(String[] stuHobby) {
        this.stuHobby = stuHobby;
    }

    public Student[] addStudent(Student[] students,Student student){//学生数组中添加一名学生
        for (int i=0;i<students.length;i++){
            if (students[i]==null){
                students[i]=student;
                break;
            }
        }
        return students;
    }
}
