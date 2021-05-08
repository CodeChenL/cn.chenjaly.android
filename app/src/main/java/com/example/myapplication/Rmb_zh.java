package com.example.myapplication;

public class Rmb_zh {

    private String [] a = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private String [] b = {"","角","毫","厘",""};
    private String [] c = {"","亿","千","百","拾","万","千","百","拾","亿","百","拾","万","千","百","拾","元"};

    private String rmb,rmbStrTemp;
    private String rmbInt,rmbDec;

    public void setRmb(String rmbStr){
        this.rmbStrTemp = rmbStr;
    }

    public String getRmb(){
        StringBuffer rmbStrResult = new StringBuffer();
        StringBuffer rmbStrall = new StringBuffer();

        //整理输入的数据，通过判断输入的字符串是否有小数位
        //并且小数位大于3位，以确保小数位可以保留两位
        if ((rmbStrTemp.indexOf(".")>0)&&(rmbStrTemp.length()-rmbStrTemp.indexOf(".")>3)){
            //自取小数点后两位
            rmbStrTemp = rmbStrTemp.substring(0,rmbStrTemp.length()-((rmbStrTemp.length()-rmbStrTemp.indexOf("."))-3));
        }
        //确定要进行转换的字符串
        rmbStrall.append(rmbStrTemp);

        //判断输入的字符串有没有小数点如果是则添加“.00”
        if (rmbStrTemp.indexOf(".")<0){
            rmbStrall.append(".00");
        }

        //判断输入的字符串是不是只带有小数点不带小数位如果是在后面添加00；
        if (rmbStrTemp.length()-rmbStrTemp.indexOf(".")==1){
            rmbStrall.append("00");
        }

        rmb=rmbStrall.toString();

        //取出整数部分
        rmbInt = rmb.substring(0,rmb.indexOf("."));
        //取出小数部分
        rmbDec = rmb.substring(rmb.indexOf(".")+1,rmb.length());

        String rmbChar;
        int rmbBDic,rmbCom;

        //判断整数的单位开始的位置 个十百千万那个
        rmbCom = c.length-(rmbInt.length());

        for (int i = 0;i<rmbInt.length();i++){
            //取出整数的每一位字符从左往右
            rmbChar = rmbInt.substring(i,i+1);
            //将每一个位的字符转换成整数
            rmbBDic = Integer.parseInt(rmbChar);
            //将数字转换成对应的中文汉字
            rmbStrResult.append(a[rmbBDic]);
            //将单位跟在对应的汉字后面
            rmbStrResult.append(c[rmbCom]);
            rmbCom++;
        }

        //处理小数部分
        for (int i=0;i<rmbDec.length();i++){
            rmbChar = rmbDec.substring(i,i+1);
            rmbBDic = Integer.parseInt(rmbChar);
            rmbStrResult.append(a[rmbBDic]);
            rmbStrResult.append(b[i+1]);
        }
        return  rmbStrResult.toString();
    }



}
