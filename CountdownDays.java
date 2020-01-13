//Fill in your name and student number
//Name: Sean Zhang
//Student Number: 260873386

// do NOT touch these import statements
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CountdownDays {

    // the method returns a String representing the current date in the following format: dd/mm/yyyy
    // you can use it, but do NOT modify it!
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //========================
    // Enter your code below

    public static void main(String[] args) {

        displayCountdown("18/09/2019");
    }
    //A method to get a substring from a speci?ed String
    public static String getSubstring(String s, int i, int j) {
        int m = s.length();
        String subString = "";
        if (i > j) {
            throw new IllegalArgumentException("The sequence of i and j is incorrect. Please retry");
        } else if (i < 0 || j > m) {
            throw new StringIndexOutOfBoundsException("The number you put is out of Bounds. Please retry");
        } else {
            for (int n = i; n <= j; n++) {
                subString += s.charAt(n);
            }
        }
        return subString;
    }
    //Three different methods to extract day, month, and year from a String
    public static int getDay(String s) {
        String s_Day = getSubstring(s, 0, 1);
        int day = Integer.parseInt(s_Day);
        return day;
    }

    public static int getMonth(String s) {
        String s_Month = getSubstring(s, 3, 4);
        int month = Integer.parseInt(s_Month);
        return month;
    }

    public static int getYear(String s) {
        String s_Year = getSubstring(s, 6, 9);
        int year = Integer.parseInt(s_Year);
        return year;
    }
    //A method to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        } else {
            return false;
        }
    }
    //A method that returns the number of days in a month
    public static int getDaysInAMonth(int month, int year) {
        int day = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        } else {
            if (isLeapYear(year)) {
                day = 29;
            } else {
                day = 28;
            }
        }
        return day;
    }
    //A method to check whether the due date has passed
    public static boolean dueDateHasPassed(String CurrentDate, String DueDate) {
        int CurrentDay = getDay(CurrentDate);
        int CurrentMonth = getMonth(CurrentDate);
        int CurrentYear = getYear(CurrentDate);
        int DueDay = getDay(DueDate);
        int DueMonth = getMonth(DueDate);
        int DueYear = getYear(DueDate);
        if (CurrentYear > DueYear) {
            return true;
        } else if ((CurrentYear == DueYear) && (CurrentMonth > DueMonth)) {
            return true;
        } else if ((CurrentYear == DueYear) && (CurrentMonth == DueMonth) && (CurrentDay > DueDay)) {
            return true;
        } else {
            return false;
        }
    }
    //A method to count the number of days left before the due date
    public static int countDaysLeft(String CurrentDate, String DueDate) {
        if (dueDateHasPassed(CurrentDate, DueDate)) {
            return 0;
        } else {
            int CurrentDay = getDay(CurrentDate);
            int CurrentMonth = getMonth(CurrentDate);
            int CurrentYear = getYear(CurrentDate);
            int DueDay = getDay(DueDate);
            int DueMonth = getMonth(DueDate);
            int DueYear = getYear(DueDate);
            int Days_Between_Years = 0;
            int Days_Passed_CurrentYear = 0;
            int Days_Passed_DueYear = 0;
            for (int b=CurrentYear;b<DueYear;b++){
                if (isLeapYear(b)) {
                    Days_Between_Years += 366;
                } else {
                    Days_Between_Years += 365;
                }
            }
            for (int j=1; j < CurrentMonth; j++) {
                Days_Passed_CurrentYear += getDaysInAMonth(j, CurrentYear);
            }
            Days_Passed_CurrentYear += CurrentDay;
            for (int k=1; k < DueMonth; k++) {
                Days_Passed_DueYear += getDaysInAMonth(k, DueYear);
            }
            Days_Passed_DueYear += DueDay;
            int DaysLeft = Days_Between_Years + Days_Passed_DueYear - Days_Passed_CurrentYear;
            return DaysLeft;
        }
    }
    //A method to diplay the countdown required
    public static void displayCountdown(String DueDate) {
        String CurrentDate = getCurrentDate();
        System.out.println("Today is:" + CurrentDate);
        System.out.println("Due date:" + DueDate);
        if (dueDateHasPassed(CurrentDate, DueDate)) {
            System.out.println("The due date has passed! :( Better luck next time!");
        } else {
            int DaysLeft = countDaysLeft(CurrentDate, DueDate);
            System.out.println("You have " + DaysLeft + " days left. You can do it!");

        }

    }
}