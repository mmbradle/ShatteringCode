package shatteringstone.sandbox.date;

import java.util.Calendar;

public class Age {

    public static int calcAge(Calendar birthDay){
        //Calendar birthDay = new GregorianCalendar(1980, 4, 22);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR))
            age--;
        return(age);
    }

//    public static void main(String[] args) {
}
