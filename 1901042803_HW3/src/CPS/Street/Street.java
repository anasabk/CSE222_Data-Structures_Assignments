package CPS.Street;

import CPS.Exceptions.CPSException.*;

/**
 * This class represents a city with a two sided street.
 * Has Read/Write and a View mode.
 * Stores buildings as an AbstractBuilding object.
 */
public interface Street{
    /**
     * An anonymous class used to represent an empty area of the street
     */
    public static final AbstractBuilding Empty = new AbstractBuilding() {
        public String focus() { return null; }
        @Override
        public String toString(){ return "Type: Empty Area\n" + super.toString(); }
    };

    /**
     * Enum to represent the sides of the streets
     */
    public enum Side {
        Front, Back;
    }

    /**
     * Enum to represent the editting modes of the program
     */
    public enum Mode {
        Read_Only, Read_Write
    }

    /**
     * Abstract class to be the super class of 
     * every building object in the street. <P>
     * Handles the management of the length, 
     * height and position of the building.
     */
    public static abstract class AbstractBuilding {
        private int _length, _height, _position;
    
        /**
         * Default constructor
         */
        public AbstractBuilding() {
            _length = 1;
            _height = 0;
            _position = 0;
        }

        /**
         * A constructor that takes the 
         * dimensions of the building
         * @throws InvalidHeightException 
         *  Thrown when the height is less than 0.
         * @throws InvalidLengthException 
         *  Thrown when the length is less than 1.
         */
        public AbstractBuilding(int length, int height) 
            throws InvalidLengthException,
                   InvalidHeightException
        {
            if(height < 0)
                throw new InvalidHeightException(height);

            else if(length < 1) 
                throw new InvalidLengthException(length);
            
            _length = length;
            _height = height;
            _position = 0;
        }

        /**
         * Set the height of the building.
         * @param height
         * @throws InvalidHeightException 
         *  Thrown when the height is less than 0.
         * @throws SetHeightNotSupportedException 
         *  Thrown when the type of building has a fixed height.
         */
        public void setHeight(int height) 
            throws InvalidHeightException,
                   SetHeightNotSupportedException
        {
            if(height < 0) 
                throw new InvalidHeightException(height);
            
            _height = height;
        }
    
        /**
         * Set the length of the building.
         * A length more than 0 is accepted
         * @param length
         * @throws InvalidLengthException
         *  Thrown when the length is less than 1.
         */
        public void setLength(int length) 
            throws InvalidLengthException
        {
            if(length < 1) 
                throw new InvalidLengthException(length);
            
            _length = length;
        }
    
        /**
         * Set the position of the building.
         * @param position
         * @throws InvalidPositionException
         *  Thrown when a position less than -1 is entered.
         */
        protected void setPosition(int position) 
            throws InvalidPositionException
        {
            if(position < -1) 
                throw new InvalidPositionException();
    
            _position = position;
        }
    
        /**
         * Return the height of the building.
         */
        public int getHeight()
        {
            return _height;
        }
    
        /**
         * Return the length of the building.
         */
        public int getLength()
        {
            return _length;
        }
    
        /**
         * Return the position of the building in the street.
         */
        public int getPosition()
        {
            return _position;
        }
    
        @Override
        public String toString() {
            return String.format ("\nLength (meters): %d\nHeight (meters): %d" + 
                                  "\nPosition (meters away from left): %d\n",
                                  getLength(), getHeight(), getPosition());
        }

        /**
         * Return specific information about 
         * this building.
         * @return Information about the building 
         * specific to it's type as a string.
         */
        public abstract String focus();
    
        /** */
        @Override
        public boolean equals(Object obj) {
            AbstractBuilding target = (AbstractBuilding)obj;
            if (_length == target.getLength() &&
                _height == target.getHeight() &&
                _position == target.getPosition())
                return true;

            else return false;
        }
    }

    /**
     * Set the editting mode of the street.
     * @param mode : Can be either Read_Only or Read_Write
     * @see Mode
     */
    public void setMode(Mode mode);

    /**
     * Return the length of the street.
     * @return
     */
    public int getLength();

    /**
     * Return the current editting mode of the street.
     * @return Current mode.
     * @see Mode
     */
    public Mode getMode();

    /**
     * Add a building to the specified position 
     * in the specified side. <P>
     * @param building
     * @param position
     * @param side
     * @return true on succes, false if the position is inapropriate.
     * @throws InvalidPositionException
     *  When the position is out of bounds.
     * @throws ReadOnlyException
     *  When called in Read_Only mode.
     */
    public boolean add(AbstractBuilding building, int position, Side side)
        throws InvalidPositionException, ReadOnlyException;

    /**
     * Remove the building with the given index in the specified side. <P>
     * Indexes start from 0.
     * @param index
     * @param side
     * @return The removed building.
     * @throws IndexOutOfBoundsException
     *  When the given index is out of bounds.
     * @throws ReadOnlyException
     *  When called in Read_Only mode.
     */
    public AbstractBuilding remove(int index, Side side)
        throws ReadOnlyException;

    /**
     * Remove the building in the given position on the street 
     * in the specified side. <P> 
     * positions can start from 0, and end in the the length of the street.
     * @param position
     * @param side
     * @return The removed building.
     * @throws InvalidPositionException
     *  When the given position is out of bounds of the street.
     * @throws ReadOnlyException
     *  When called in Read_Only mode.
     */
    public AbstractBuilding remove_Position(int position, Side side)
        throws InvalidPositionException, ReadOnlyException;

    /**
     * Return the building with the given index in the specified side. <P>
     * Indexes start from 0.
     * @param index
     * @param side
     * @return The target building.
     * @throws IndexOutOfBoundsException
     *  When the given index is out of bounds.
     */
    public AbstractBuilding get(int index, Side side);

    /**
     * Return the building in the given position on the street 
     * in the specified side. <P> 
     * positions can start from 0, and end in the the length of the street.
     * @param position
     * @param side
     * @return The target building.
     * @throws InvalidPositionException
     *  When the given position is out of bounds of the street
     */
    public AbstractBuilding get_Position(int position, Side side)
        throws InvalidPositionException;

    /**
     * Return an array of strings representing the rows of 
     * the graph of the skyline silhouette of the street.
     * @return Array of strings
     */
    public String getsilhouette();

    /**
     * Count the number of buildings of the 
     * given type in the given side.
     * @param type
     * @param side
     * @return number of buildings
     */
    public int countType(String typeName, Side side);

    /**
     * Return the total area occupied by the specified 
     * building type in the specified sidee.
     * @param type
     * @param side
     * @return total area occupied
     */
    public int countTypeArea(String typeName, Side side);

    /**
     * return the ratio of the total area of playgrounds 
     * to the total length of the street sides.
     * @return ratio
     */
    public float playGroundRatio();

    /**
     * Return the size of empty area in the 
     * specified side of the street.
     * @param side
     * @return
     */
    public int getEmptyArea(Side side);
}