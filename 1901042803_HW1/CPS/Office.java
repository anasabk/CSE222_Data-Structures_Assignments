package CPS;

import CPS.CPSException.*;

/**
 * A class to represent an office with job type 
 * information, as well as other properties 
 * from super classes. 
 * Derived from Building abstract class.
 */
public class Office extends Building {
    private String _jobType;

    /**
     * Default constructor.
     */
    public Office(){
        super();
        _jobType = null;
    }

    /**
     * A constructor that takes the dimensions.
     * @param length
     * @param height
     * @throws InvalidDimensionsException
     */
    public Office(int length, int height)
        throws InvalidDimensionsException
    {
        super(length, height);
        _jobType = null;
    }

    /**
     * A constructor that takes the dimensions 
     * and the owner info.
     * @param length
     * @param height
     * @param owner
     * @throws InvalidDimensionsException
     */
    public Office(int length, int height, String owner)
        throws  InvalidDimensionsException
    {
        super(length, height, owner);
        _jobType = null;
    }

    /**
     * A constructor that takes all the required 
     * information for the class.
     * @param length
     * @param height
     * @param owner
     * @param jobType
     * @throws InvalidDimensionsException
     */
    public Office  (int length, 
                    int height, 
                    String owner, 
                    String jobType)
        throws InvalidDimensionsException
    {
        super(length, height, owner);
        _jobType = jobType;
    }

    /**
     * Set the job type.
     * @param jobType
     */
    public void setJobType(String jobType){
        _jobType = jobType;
    }

    /**
     * Return the job type.
     * @return
     */
    public String getJobType(){
        return _jobType;
    }

    @Override
    protected Object clone(){
        Office temp = null;
        try {
            temp = new Office(getLength(), 
                              getHeight(), 
                              _jobType, 
                              _owner);

        } catch (Exception e) {}

        return temp;
    }

    @Override
    public String focus() {
        return _jobType;
    }

    @Override
    public String toString() {
        return "\nType: Office" + super.toString();
    }
}
