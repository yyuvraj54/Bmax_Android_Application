package com.example.bmax;



public class CredentialsCheck {
    String u;
    String p;
    String h;
    String w;
    String g;
    String ival;
    String ageval;

    CredentialsCheck(String user,String password,String h,String w,String gender,String ival,String ageval) {
        this.u = user;
        this.p = password;
        this.h = h;
        this.w = w;
        this.g = gender;
        this.ival = ival;
        this.ageval = ageval;
    }
    //CHECKING IF ALL CHARATER OF NAME IS CHAR ELSE RETURNING FALSE >> USED FOR DETECTING NAME IN FORMAT OR NOT
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public String errorcheck(){
        //ERROR MEASSAGE IF ANY
        String meassage="Something got wrong!";
        int state=0;

        //CHECKING GENDER IF NOT GIVEN :ERROR MEASSAGE FOR GENDER
        if (g=="MALE" ||g=="FEMALE"){state +=1;}
        else{meassage="Gender not selected";}




        //INTENSITY
        if(ival.equals("Select your workout intensity")){meassage="Please select intensity default you can give it 2";}
        else{state +=1;}
        //AGE
        if(ageval.length()==0){meassage="Please enter your age";}
        else if(Integer.parseInt(ageval)<=5){meassage="Please enter a valid age (more than 5)";}
        else if(Integer.parseInt(ageval)>=80){meassage="Please enter a valid age (less than 80)";}
        else{state +=1;}



        //CHECKING HEIGHT AND WEIGHT VALUES
        if (h.length()==0 && w.length()==0){meassage="Height/Weight values can't be null!";}
        else if(h.length()==0){meassage="Height can't be null!";}
        else if(w.length()==0){meassage="Weight can't be null!";}
        else{state+=1;}


        //CHECKING USERNAME AND PASSWORD
        if (u.length()==0 && p.length()==0){meassage="Name/Password missing!";}
        else if(u.length()==0) {meassage="Name missing!";}
        else if(p.length()==0) {meassage="Password missing!";}
        else{state+=1;}

        if (isAlpha(u)){state +=1;}
        else{meassage="Invalid name: please use letters only with no space";}


        //IF ALL CONDITION ARE SATISFIED THEN RETURNING THE MEASSAGE ELSE ERROR MEASSAGE
        if (state==6){meassage="Data check almost done";}
        return meassage;


    }

}
