package com.example.loginapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    Integer[] icons={R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,
            R.mipmap.img05, R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,R.mipmap.img09,
            R.mipmap.img10,R.mipmap.img11};//图标数组
    Student[] students=new CreateStu().setStudent();//学生数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView= findViewById(R.id.listview);//获取列表视图
        List<Map<String,Object>> listItems = new ArrayList<>();//创建一个list集合
        Intent intent=getIntent();//返回事件
        Bundle returnBundle=intent.getExtras();//获取返回的bundle
        if (returnBundle!=null){//判断返回的bundle是不是空值
            //bundle不为空则添加bundle传入的学生信息
            students=(Student[]) returnBundle.getSerializable("newStudents");
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("图标", icons[i]);
                map.put("姓名", students[i].getStuName());
                map.put("信息", "学院:" + students[i].getStuInst()
                        + "     专业:" + students[i].getStuSpec());
                listItems.add(map);
            } else break;
        }

        SimpleAdapter adapter = new SimpleAdapter(this,listItems,
                R.layout.item,new String[]{"姓名","图标","信息"}, new int[]{R.id.name,R.id.img,R.id.info});
        //创建SimpleAdapter
        listView.setAdapter(adapter);//将适配器与ListView关联

        //listview长按事件
        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            final String[] items={"编辑","删除"};//设置菜单对话框子项目
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("菜单");//设置菜单对话框的标题
            builder.setItems(items, (dialogInterface, which) -> {
                if(items[which].equals("删除")){//菜单中选择删除
                    //生成提示框确认操作
                    AlertDialog confirmDialog=new AlertDialog.Builder(MainActivity.this).create();
                    confirmDialog.setTitle("提示");
                    confirmDialog.setMessage("您确定要删除该项吗？");
                    //添加取消按钮
                    confirmDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialogInterface1, i) ->
                            Toast.makeText(MainActivity.this, "操作已取消", Toast.LENGTH_SHORT).show());
                    //添加确定按钮
                    confirmDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialogInterface12, i) -> {
                        for(int n=position;n<students.length;n++){//更新删除后的students数组
                            if(students[n]!=null)
                                students[n]=students[n+1];
                            else break;
                        }
                        int pos=(int)listView.getAdapter().getItemId(position);
                        if(listItems.remove(pos)!=null) {//删除选择项并弹出提示
                            adapter.notifyDataSetChanged();//更新adapter
                            Toast.makeText(MainActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    confirmDialog.show();
                }else if(items[which].equals("编辑")){//菜单中选择编辑
                    Intent editIntent=new Intent(MainActivity.this,ActivityStudent.class);
                    Bundle editBundle=new Bundle();

                    editBundle.putInt("position",position);//传输被编辑项的位置
                    editBundle.putSerializable("editStudents",students);
                    editBundle.putString("mode","editStu");//提示ActivityStudent页面为编辑模式
                    editIntent.putExtras(editBundle);
                    startActivity(editIntent);
                }
            });
            builder.create().show();
            return false;
        });

        Button button= findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent buttonIntent=new Intent(MainActivity.this,ActivityStudent.class);
            Bundle stuBundle=new Bundle();
            stuBundle.putSerializable("students",students);
            stuBundle.putString("mode","addStu");//提示ActivityStudent页面为添加模式
            buttonIntent.putExtras(stuBundle);
            startActivity(buttonIntent);
        });//主页中的button监听器，单击后可以跳转至添加学生信息页面
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=new MenuInflater(this);;
        menuInflater.inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addStu:
                Intent intent1=new Intent(MainActivity.this,ActivityStudent.class);
                startActivity(intent1);
                break;
            case R.id.refurbish:

        }
        return  super.onOptionsItemSelected(item);
    }
}