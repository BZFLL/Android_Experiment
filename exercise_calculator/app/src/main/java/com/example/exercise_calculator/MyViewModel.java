package com.example.exercise_calculator;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public String num[]={"",""};//用于储存参与计算的数值
    public Boolean havePoint=false;//主数值当中是否包含小数点
    public String sign1="",sign2="";//运算符号

    private MutableLiveData<String> mainNum;//主数值(用户正在操作的数)

    public MutableLiveData<String> getMainNum(){
        if(mainNum == null){
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");
        }
        return mainNum;
    }

    public void setMainNum(String n){
        if(mainNum.getValue().equals("0"))
            mainNum.setValue(n);//覆盖掉"0"
        else{
            mainNum.setValue(mainNum.getValue() + n);//在末尾增添数值
        }
    }

    public String mainNumWithnum_0_Tocal(){
        String value="0";
        if(mainNum.getValue().contains(".") || num[0].contains(".")){//如果两个数中其中一个有小数点
            switch (sign1){
                case "+":
                    value=String.valueOf( Double.valueOf(num[0]) + Double.valueOf(mainNum.getValue()) );
                    break;
                case "-":
                    value=String.valueOf( Double.valueOf(num[0]) - Double.valueOf(mainNum.getValue()) );
                    break;
                case "*":
                    value=String.valueOf( Double.valueOf(num[0]) * Double.valueOf(mainNum.getValue()) );
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value=String.valueOf( Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()) );

            }
        }else{//如果两个数都是整数
            switch (sign1){
                case "+":
                    value=String.valueOf( Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()) );
                    break;
                case "-":
                    value=String.valueOf( Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()) );
                    break;
                case "*":
                    value=String.valueOf( Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()) );
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value=String.valueOf( Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()) );
            }
        }
        return value;
    }
    public String mainNumWithnum_1_tocal(){
        String value="0";
        if(mainNum.getValue().contains(".") || num[1].contains(".")){//如果两个数中其中一个有小数点
            switch (sign2){
                case "*":
                    value=String.valueOf( Double.valueOf(num[1]) * Double.valueOf(mainNum.getValue()) );
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value=String.valueOf( Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()) );
            }
        }else{//如果两个数都是整数
            switch (sign2){
                case "*":
                    value=String.valueOf( Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()) );
                    break;
                case "/":
                    if(mainNum.getValue().equals("0"))
                        mainNum.setValue("1");
                    value=String.valueOf( Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()) );
            }
        }
        return value;
    }

}