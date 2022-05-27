package CPS.Building;

import CPS.Exceptions.CPSException.*;

/**
 * A class to store a house with color and number 
 * of rooms information, as well as other properties 
 * from super classes. 
 * Derived from Building abstract class.
 */
public class House extends Building {
    private int _numOfRooms;
    private String _color;

    /**
     * Default constructor
     */
    public House(){
        super();
        _numOfRooms = 0;
        _color = null;
    }

    /**
     * A constructor that takes the dimensions.
     * @param length
     * @param height
     * @throws InvalidDimensionsException
     */
    public House(int length, int height) 
        throws InvalidLengthException,
               InvalidHeightException
    {
        super(length, height);
        _numOfRooms = 0;
        _color = null;
    }

    /**
     * A constructor that takes the dimensions 
     * and the owner info.
     * @param length
     * @param height
     * @param owner
     * @throws InvalidDimensionsException
     */
    public House(int length, int height, String owner) 
        throws InvalidLengthException,
               InvalidHeightException
    {
        super(length, height, owner);
        _numOfRooms = 0;
        _color = null;
    }

    /**
     * A constructor that takes all the required 
     * information for the class.
     * @param length
     * @param height
     * @param owner
     * @param numOfRooms
     * @param color
     * @throws InvalidDimensionsException
     * @throws InvalidNumOfRoomsException
     */
    public House(int length, 
                 int height, 
                 String owner, 
                 int numOfRooms, 
                 String color)
        throws InvalidLengthException,
               InvalidHeightException,
               InvalidNumOfRoomsException
    {
        super(length, height, owner);
        
        if(numOfRooms < 0)  
            throw new InvalidNumOfRoomsException();
        else _numOfRooms = numOfRooms;
        _color = color;
    }

    /**
     * Set the number of rooms. 
     * Only numbers bigger than 0 are excepted.
     * @param numOfRooms
     * @throws InvalidNumOfRoomsException
     */
    public void setNumOfRooms(int numOfRooms) 
        throws InvalidNumOfRoomsException 
    {
        if (numOfRooms < 1) 
            throw new InvalidNumOfRoomsException();

        else _numOfRooms = numOfRooms;
    }

    /**
     * Set the color of the house
     * @param color
     */
    public void setColor(String color){
        _color = color;
    }

    /**
     * Return the number of rooms.
     * @return
     */
    public int getNumOfRooms()
    {
        return _numOfRooms;
    }

    /**
     * Return the number of rooms.
     * @return
     */
    public String getColor()
    {
        return _color;
    }

    @Override
    protected Object clone(){
        House temp = null;
        try {
            temp = new House(getLength(), 
                             getHeight(), 
                             getOwner(), 
                             _numOfRooms, 
                             _color);

        } catch (Exception e) {}

        return temp;
    }

    @Override
    public String focus() {
        return getOwner();
    }

    @Override
    public String toString() {
        return "\nType: House\nNumber of rooms: " + _numOfRooms + 
                "\nColor: " + _color +
                super.toString();
    }
}
