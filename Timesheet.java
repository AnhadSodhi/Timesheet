package q2;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an employee Timesheet containing TimesheetRow objects.
 *
 * @author Anhad Sodhi (Set 1B)
 * @version 1.0
 */
public class Timesheet {
    
    /** The employee number. */
    private String empNum;
    
    /** The Friday on the specified week. */
    private LocalDate endWeek;
    
    /** The list of TimesheetRows. */
    private List<TimesheetRow> details = new ArrayList<TimesheetRow>();
    
    /**
     * Constructs a Timesheet object that sets the empNum to "undefined" and
     * the endWeek to the Friday on the current week.
     */
    public Timesheet() {
        empNum = "undefined";
        
        endWeek = LocalDate.now();
        while (endWeek.getDayOfWeek() != DayOfWeek.FRIDAY) {
            endWeek = endWeek.plusDays(1);
        }
    }
    
    /**
     * Constructs a Timesheet object that sets the empNum and endWeek to the
     * parameters.
     * @param num as a String, the empNum
     * @param week as a LocalDate, the endWeek
     * @throws IllegalArgumentException if the given week is not a Friday
     */
    public Timesheet(String num, LocalDate week)
                     throws IllegalArgumentException {
        empNum = num;
        
        endWeek = week;
        while (endWeek.getDayOfWeek() != DayOfWeek.FRIDAY) {
            endWeek = endWeek.plusDays(1);
        }
    }
    
    /**
     * An accessor (getter) method for the empNum.
     * @return empNum as a String
     */
    public String getEmpNum() {
        return empNum;
    }
    
    /**
     * An accessor (getter) method for the endWeek.
     * @return endWeek as a LocalDate
     */
    public LocalDate getEndWeek() {
        return endWeek;
    }
    
    /**
     * An accessor (getter) method for the details.
     * @return details as a List of TimesheetRows
     */
    public List<TimesheetRow> getDetails() {
        return details;
    }
    
    /**
     * A mutator (setter) method for the empNum.
     * @param newNum as a String
     */
    public void setEmpNum(String newNum) {
        empNum = newNum;
    }
    
    /**
     * A mutator (setter) method for the endWeek.
     * @param newWeek as a LocalDate
     * @throws IllegalArgumentException if the newWeek is not a Friday
     */
    public void setEndWeek(LocalDate newWeek) {
        if (newWeek.getDayOfWeek() == DayOfWeek.FRIDAY) {
            endWeek = newWeek;
        } else {
            throw new IllegalArgumentException(
                      "Error:the new end week must be a Friday");
        }
    }
    
    /**
     * A mutator (setter) method for the details.
     * @param newDetails as a List of TimesheetRows
     */
    public void setDetails(List<TimesheetRow> newDetails) {
        details = newDetails;
    }
    
    /**
     * Returns a String representation of the Timesheet object.
     * @return formatted representation as a String
     */
    public String toString() {
        String str = "Employee number: " + empNum + "\n";
        str += "End week: " + endWeek + "\n";
        str += "SAT SUN MON TUE WED THU FRI \n";
        
        for (int i = 0; i < details.size(); i++) {
            str += details.get(i);
            str += "\n";
        }
        return str;
    }
    
    /**
     * A method to add a row to the details List.
     * @param row as a TimesheetRow
     */
    public void addRow(TimesheetRow row) {
        details.add(row);
    }
    
    /**
     * This is the main method (entry point) that gets called by the JVM.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Timesheet sheet = new Timesheet();
        
        ArrayList<TimesheetRow> list = new ArrayList<>();
        TimesheetRow r1 = new TimesheetRow();
        TimesheetRow r2 = new TimesheetRow();
        list.add(r1);
        list.add(r2);
        
        sheet.setEmpNum("one");
        sheet.setDetails(list);
        sheet.addRow(new TimesheetRow());
        System.out.println(sheet);
    }

}
