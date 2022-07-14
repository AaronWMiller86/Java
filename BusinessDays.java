import java.time.*;
import java.time.DayOfWeek;

public class BusinessDays {

    int year;
    int month;
    int numDays;
    int start;
    int totalCount;
    int yearCount;

    public void date() {
        // Method uses java.time to establish the start day of the week as an Integer. E.g. '1' (Monday) or '5' (Friday).
        LocalDate localDate = LocalDate.of(year, month, 1);
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        start = dayOfWeek.getValue();
    }

    public void numberOfDays() {
        // Method determines the number of days in the given month.
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            numDays = 30;
            // Logic below determines if the year is a Leap-year and adds a day to February if so.
        } else if (month == 2 && (year % 4) == 0) {
            numDays = 29;
        } else if (month == 2) {
            numDays = 28;
        } else {
            numDays = 31;
        }
    }

    public String days(int inputYear, int inputMonth) {
        // Method takes a year and month as input and iterates through skipping Saturdays and Sundays from the count.
        year = inputYear;
        month = inputMonth;
        date();
        numberOfDays();
        int countSat = 1;
        int countSun = 1;

        // Logic below eliminates rollover issue with the first day of the month being Sunday.
        if (start == 7) {
            countSat = 2;
        }

        /*
        Loop below starts on the given weekday and skips Saturdays (6, 13, 20, etc.) and Sundays (7, 14, 21, etc.)
        The start value is added to the while portion of the loop (numDays + start) which allows the count to
        cycle back through the days skipped by initializing the loop at the start variable from the date() method.
         */
        for (int i = start; i < (numDays + start); i++) {
            if (i == ((countSat * 6) + (countSat - 1))) {
                countSat += 1;
                continue;
            }
            if (i == (7 * countSun)) {
                countSun += 1;
                continue;
            }
            totalCount += 1;
        }
        return "There are " + totalCount + " business days in " + Month.of(month).name() + " of " + year + ".";
    }

    public String daysInYear(int fullYear) {
        // Method below takes a year input and calls the day() method for all 12 months of that year then adds the total.
        year = fullYear;
        month = 1;
        while (month < 13){
            totalCount = 0;
            date();
            numberOfDays();
            days(year, month);
            yearCount = yearCount + totalCount;
            month +=1;
        }
        return "There are " + yearCount + " business days in " + year + ".";
    }

    public static void main(String[] args) {
        BusinessDays test = new BusinessDays();
        System.out.println(test.days(2021, 8));
        System.out.println(test.daysInYear(2022));

    }
}