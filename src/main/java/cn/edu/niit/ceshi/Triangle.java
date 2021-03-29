package cn.edu.niit.ceshi;

public class Triangle {
    public String IsTri(int a,int b,int c){
        String str=" ";
        if ((a+b<=c)||(a+c<=b)||(b+c<=a)){
            str="不构成三角形";
            System.out.println(str);
        }else{
            if((a==b) ||(b==c)||(a==c)){
                if((a==b)&&(b==c)){
                    str="等边三角形";
                    System.out.println(str);
                }else{
                    str="等腰三角形";
                    System.out.println(str);
                }
            }else{
                str="一般三角形";
                //System.out.println(str);
            }
        }
        return str;
    }
}
