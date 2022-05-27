package CPS;

import CPS.CPSException.*;

/**
 * A class to represent a market with closing 
 * and Opening hours information in 24 hour format, 
 * as well as other properties from super classes. 
 * Derived from Building abstract class.
 */
public class Market extends Building {
    private int _closeHour, 
                _closeMin, 
                _openHour, 
                _openMin;

    /**
     * Default constructor.
     */
    public Market(){
        super();
        _closeHour = 0;
        _closeMin = 0;
        _openHour = 0;
        _openMin = 0;
    }

    /**
     * A constructor that takes the dimensions.
     * @param length
     * @param height
     * @throws InvalidDimensionsException
     */
    public Market(int length, int height)
        throws InvalidDimensionsException
    {
        super(length, height);
        _closeHour = 0;
        _closeMin = 0;
        _openHour = 0;
        _openMin = 0;
    }

    /**
     * A constructor that takes the dimensions 
     * and the owner info.
     * @param length
     * @param height
     * @param owner
     * @throws InvalidDimensionsException
     */
    public Market(int length, int height, String owner)
        throws InvalidDimensionsException
    {
        super(length, height, owner);
        _closeHour = 0;
        _closeMin = 0;
        _openHour = 0;
        _openMin = 0;
    }

    /**
     * A constructor that takes all the required 
     * information for the class.
     * @param length
     * @param height
     * @param owner
     * @param closeHour
     * @param closeMin
     * @param openHour
     * @param openMin
     * @throws InvalidDimensionsException
     * @throws InvalidTimeException
     */
    public Market(int length, 
                  int height, 
                  String owner, 
                  int closeHour, 
                  int closeMin, 
                  int openHour, 
                  int openMin)
        throws InvalidDimensionsException,
               InvalidTimeException
    {
        super(length, height, owner);

        if (closeHour < 0 || closeHour > 23 ||
            closeMin < 0 || closeMin > 59 ||
            openHour < 0 || openHour > 23 ||
            openMin < 0 || openMin > 59)
            throw new InvalidTimeException();

        else{
            _closeHour = closeHour;
            _closeMin = closeMin;
            _openHour = openHour;
            _openMin = openMin;
        }
    }

    /**
     * Set closing hour
     * @param hour
     * @param minute
     * @throws InvalidTimeException
     */
    public void setCloseTime(int hour, int minute) 
        throws InvalidTimeException
    {
        if (hour < 0 || hour > 23 ||
            minute < 0 || minute > 59)
            throw new InvalidTimeException();

        else{
            _closeHour = hour;
            _closeMin = minute;
        }
    }

    /**
     * Set Operning hour
     * @param hour
     * @param minute
     * @throws InvalidTimeException
     */
    public void setOpenTime(int hour, int minute) 
        throws InvalidTimeException
    {
        if (hour < 0 || hour > 23 ||
            minute < 0 || minute > 59)
            throw new InvalidTimeException();

        else{
            _openHour = hour;
            _openMin = minute;
        }
    }

    /**
     * Return closing hour as integer array, 
     * index 0 for hours, index 1 for minutes
     * @return array containing hours and minutes
     */
    public int[] getCloseTime(){
        return new int[] {_closeHour, _closeMin};
    }

    /**
     * Return opening hour as integer array, 
     * index 0 for hours, index 1 for minutes
     * @return array containing hours and minutes
     */
    public int[] getOpenTime(){
        return new int[] {_openHour, _openMin};
    }

    @Override
    protected Object clone() 
        throws CloneNotSupportedException
    {
        Market temp = null;
        try {
            temp = new Market(getLength(), 
                              getHeight(), 
                              _owner, 
                              _closeHour,
                              _closeMin,
                              _openHour,
                              _openMin);

        } catch (Exception e) { throw new CloneNotSupportedException(); }

        return temp;
    }

    @Override
    public String focus() {
        return String.format("%02d:%02d", _closeHour, _closeMin);
    }

    @Override
    public String toString() {
        return "\nType: Market\nOper Hour: " + getOpenTime()[0] + ":" + getOpenTime()[1] + 
               "Close Hour: " +  getCloseTime()[0] + ":" + getCloseTime()[1] + 
               super.toString();
    }
}