package com.example.exercise_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.exercise_calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private MyViewModel myViewModel;
    private ActivityMainBinding binding;//控件储存类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);//获取所有控件
        myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型对象

        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {//数值发生了改变
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让 myTextView 显示新的数值

                //让 textView 显示式子
                if(myViewModel.sign2.equals("")) {
                    if (myViewModel.sign1.equals("")) {
                        binding.textView3.setText(myViewModel.getMainNum().getValue());
                    } else {//如果是像 a+b 这种形式的式子
                        binding.textView3.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                }else{//如果是像 a+b*c 这种形式的式子
                    binding.textView3.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.num[1] + myViewModel.sign2 + myViewModel.getMainNum().getValue());
                }
            }
        });
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("9");
            }
        });
        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint=true;
                }
            }
        });
        binding.buttonJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="+";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();//将当前的主数值存放到num[0]当中
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNumWithnum_0_Tocal();
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else{//如果是像 a+b*c 这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithnum_1_tocal());//计算 d=b*c
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.num[0]=myViewModel.mainNumWithnum_0_Tocal();//计算a+d
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }
            }
        });
        binding.buttonJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();//将当前的主数值存放到num[0]当中
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNumWithnum_0_Tocal();
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else{//如果是像 a+b*c 这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithnum_1_tocal());//计算 d=b*c
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.num[0]=myViewModel.mainNumWithnum_0_Tocal();//计算a+d
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }
            }
        });
        binding.buttonChen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="*";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();//将当前的主数值存放到num[0]当中
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按照正常运算顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithnum_0_Tocal();
                        myViewModel.sign1="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;

                    }else{
                        myViewModel.sign2="*";
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();//将当前的主数值存放到num[1]当中
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{
                    myViewModel.num[1] = myViewModel.mainNumWithnum_1_tocal();
                    myViewModel.sign2="*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }
            }
        });
        binding.buttonChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="/";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();//将当前的主数值存放到num[0]当中
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按照正常运算顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithnum_0_Tocal();
                        myViewModel.sign1="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;

                    }else{
                        myViewModel.sign2="/";
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();//将当前的主数值存放到num[1]当中
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{
                    myViewModel.num[1] = myViewModel.mainNumWithnum_1_tocal();
                    myViewModel.sign2="/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }
            }
        });
        binding.buttonQingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.sign1="";
                myViewModel.sign2="";
                myViewModel.num[1]="";
                myViewModel.num[0]="";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint=false;
            }
        });
        binding.buttonJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithnum_0_Tocal());

                        //判断是否有小数点
                        if(myViewModel.getMainNum().getValue().contains("."))
                            myViewModel.havePoint=true;
                        else
                            myViewModel.havePoint=false;

                        myViewModel.num[0]="";
                        myViewModel.sign1="";
                    }
                }else{//如果是像 a+b*c 这种形式的式子时
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithnum_1_tocal());
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithnum_0_Tocal());

                    //判断是否有小数点
                    if(myViewModel.getMainNum().getValue().contains("."))
                        myViewModel.havePoint=true;
                    else
                        myViewModel.havePoint=false;

                    myViewModel.num[0]="";
                    myViewModel.sign1="";

                }
                //刷新
                myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue());
            }
        });
        binding.buttonZhuanhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0]= "0";//将0存放到num[0]当中
                    myViewModel.havePoint=false;

                }else {
                    return ;
                }
            }
        });
        binding.buttonKaifang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    if(Double.valueOf(myViewModel.num[0])<0){
                        String message = "开方不得小于0";

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        myViewModel.num[0]=String.valueOf( Math.sqrt(Double.valueOf(myViewModel.num[0])));
                        myViewModel.getMainNum().setValue(myViewModel.num[0]);
                        myViewModel.havePoint=false;
                    }

                }else {
                    return ;
                }
            }
        });
        binding.imageButtonTuige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals(""))
                        myViewModel.getMainNum().setValue("0");
                }
            }
        });
    }
}