package q2;

/**
 * Represents a TimesheetRow containing a long that represents a project's
 * hours for each day of the week.
 *
 * @author Anhad Sodhi (Set 1B)
 * @version 1.0
 */
public class TimesheetRow {

    /** The number of days in a week. */
    private static final int MAX_DAYS = 7;
    
    /** The number of hours in a day in a week. */
    private static final float MAX_HOURS = 24.0f;
    
    /** The amount to shift the number in the getHour and setHour methods. */
    private static final int SHIFT_AMOUNT = 8;
    
    /** Used to turn the hour float into an int and the hour int into a float
     *  in the getHour and setHour methods. */
    private static final int FLOAT_TO_INT_RATIO = 10;
    
    /** The project number. */
    private int project;
    
    /** The work package. */
    private String workPackage;
    
    /** The representation of each day's hours for a week. */
    private long hours;
    
    /** A helpful array for getting and setting the hours for a day. */
    private final long[] mask = 
        {0xFFL, 
         0xFF00L, 
         0xFF0000L, 
         0xFF000000L, 
         0xFF00000000L, 
         0xFF0000000000L,
         0xFF000000000000L };

    /**
     * Constructs a TimesheetRow object and sets the instance variables to
     * example values.
     */
    public TimesheetRow() {
        project = 1;
        workPackage = "pkg";
        hours = 0;
    }
    
    /**
     * Constructs a TimesheetRow object and sets the instance variables to
     * the parameters.
     * @param proj as an int, the project
     * @param workPkg as a String, the workPackage
     * @param hrs as a variable length float array, the hours to set
     *     (Saturday to Friday)
     * @throws IllegalArgumentException if there are too many given hrs to set
     */
    public TimesheetRow(int proj, String workPkg, float... hrs)
                        throws IllegalArgumentException {
        project = proj;
        workPackage = workPkg;
        
        hours = 0;
        if (hrs.length <= MAX_DAYS) {
            for (int i = 0; i < hrs.length; i++) {
                if (hrs[i] >= 0.0f && hrs[i] <= MAX_HOURS) {
                    setHour(i, hrs[i]);
                } else {
                    throw new IllegalArgumentException(
                            "Error! hours must be under 24.0 must be 7 or"
                            + " below.");
                }
                
            }
        } else {
            throw new IllegalArgumentException(
                      "Error! Number of hours must be 7 or below.");
        }
        
    }
    
    /**
     * An accessor (getter) method for the project.
     * @return project as an int
     */
    public int getProject() {
        return project;
    }

    /**
     * An accessor (getter) method for the workPackage.
     * @return workPackage as a String
     */
    public String getWorkPackage() {
        return workPackage;
    }

    /**
     * An accessor (getter) method for the hours.
     * @return hours as a long
     */
    public long getHours() {
        return hours;
    }

    /**
     * A mutator (setter) method for the project.
     * @param num as an int
     */
    public void setProject(int num) {
        project = num;
    }

    /**
     * A mutator (setter) method for the workPackage.
     * @param newPkg as a String
     */
    public void setWorkPackage(String newPkg) {
        workPackage = newPkg;
    }

    /**
     * A mutator (setter) method for the hours.
     * @param newHours as a long
     */
    public void setHours(long newHours) {
        hours = newHours;
    }

    /**
     * An accessor (getter) method to get a given day of the week.
     * @param dayOfWeek as an int
     * @return hours as a float
     */
    public float getHour(int dayOfWeek) {
        long maskUsed = mask[dayOfWeek];
        
        long onlyHour = (maskUsed & hours) >> (SHIFT_AMOUNT * dayOfWeek);
        
        return onlyHour / (float) FLOAT_TO_INT_RATIO;
    }

    /**
     * A mutator (setter) method to set a given day of the week to a given
     * value.
     * @param dayOfWeek as an int
     * @param newHours as a float
     */
    public void setHour(int dayOfWeek, float newHours) {
        long intHours = (long) (newHours * FLOAT_TO_INT_RATIO);
        long maskUsed = mask[dayOfWeek];
        
        long hour = maskUsed & (intHours << (SHIFT_AMOUNT * dayOfWeek));
        
        hours = hours | hour;
    }

    /**
     * Returns a String description of this TimesheetRow object.
     * @return formatted representation as a String
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < MAX_DAYS; i++) {
            str += getHour(i) + " ";
        }
        return str;
    }
}
