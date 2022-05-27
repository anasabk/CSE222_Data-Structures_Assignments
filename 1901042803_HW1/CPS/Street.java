package CPS;

import java.util.Scanner;

import CPS.CPSException.*;

/**
 * This class represents a city with a two sided street.
 * Has Read/Write and a View mode.
 * Stores buildings as an AbstractBuilding object.
 */
public class Street{
    private int _length;
    private Mode _mode; /**The mode for modification*/
    private AbstractBuilding[] _front, _back;   /**street sides */

    /**
     * An anonymous class used to represent an empty area of the street
     */
    private static final AbstractBuilding Empty = new AbstractBuilding() {
        public String focus() { return null; }
    };

    /**
     * Enum to reoresent the sides of the streets
     */
    public enum Side {
        Front, Back;
    }

    /**
     * Enum to represent the modes of the program
     */
    public enum Mode {
        Read, Write
    }

    /**
     * Abstract class to be the super class of 
     * every building object in the street. 
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
         * A constructor taking the dimensions. 
         * Dimensions more than 0 are accepted.
         * @param length: bu
         * @param height
         * @throws InvalidDimensionsException
         */
        public AbstractBuilding(int length, int height) 
            throws InvalidDimensionsException
        {
            if(height < 0 || length < 0) 
                throw new InvalidDimensionsException();
            
            else{
                _length = length;
                _height = height;
                _position = 0;
            }
        }

        /**
         * Set the height of the building. 
         * A height more than 0 is accepted.
         * @param height
         * @throws InvalidDimensionsException
         * @throws SetHeightNotSupportedException
         */
        public void setHeight(int height) 
            throws InvalidDimensionsException,
                   SetHeightNotSupportedException
        {
            if(height < 0) 
                throw new InvalidDimensionsException();
            
            else _height = height;
        }
    
        /**
         * Set the length of the building.
         * A length more than 0 is accepted
         * @param length
         * @throws InvalidDimensionsException
         */
        public void setLength(int length) 
            throws InvalidDimensionsException
        {
            if(length < 0) 
                throw new InvalidDimensionsException();
            
            else _length = length;
        }
    
        /**
         * Set the position of the building.
         * this function is only supposed to be used 
         * Street class methods. 
         * A position more than 0 is accepted.
         * @param position
         * @throws InvalidPositionException
         */
        private void setPosition(int position) 
            throws InvalidPositionException
        {
            if(position < 0) 
                throw new InvalidPositionException();
    
            else _position = position;
        }
    
        /**
         * Return the height
         * @return
         */
        public int getHeight()
        {
            return _height;
        }
    
        /**
         * Return the length
         */
        public int getLength()
        {
            return _length;
        }
    
        /**
         * return the position
         * @return
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
         * Used to show specific information about 
         * an object derived from this class.
         * @return a string
         */
        public abstract String focus();
    }

    /**
     * default constructor
     */
    public Street(){
        _mode = Mode.Write;
        _length = 0;
        _front = null;
        _back = null;
    }

    /**
     * a constructor that takes the length. 
     * Initializes the street with Empty areas
     * @param length
     * @throws InvalidDimensionsException
     */
    public Street(int length)
        throws InvalidDimensionsException
    {
        if(length < 0) 
            throw new InvalidDimensionsException();

        _mode = Mode.Write;
        _length = length;
        _front = new AbstractBuilding[length];
        _back = new AbstractBuilding[length];

        for(int i = 0; i < _length; ++i){
            _front[i] = Empty;
            _back[i] = Empty;
        }
    }

    /**
     * Set the length of the street. 
     * Reinitializes the street so all the data is lost.
     * @param length
     * @throws InvalidDimensionsException
     * @throws ReadOnlyException
     */
    public void setLength(int length)
        throws InvalidDimensionsException,
               ReadOnlyException    
    {
        if(_mode == Mode.Read)  
            throw new ReadOnlyException();
        
        else if(length < 0) 
            throw new InvalidDimensionsException();

        else{
            _length = length;
            _front = new AbstractBuilding[length];
            _back = new AbstractBuilding[length];

            for(int i = 0; i < _length; ++i){
                _front[i] = Empty;
                _back[i] = Empty;
            }
        }
    }

    /**
     * Set the mode.
     * @param mode
     * @see
     * Mode
     */
    public void setMode(Mode mode){
        _mode = mode;
    }

    /**
     * return the length
     * @return
     */
    public int getLength(){
        return _length;
    }

    /**
     * Return the mode
     * @return
     */
    public Mode getMode(){
        return _mode;
    }

    /**
     * Add a building to the specified position 
     * in the specified side. 
     * Return an exception in case the position 
     * is inapropriate.
     * @param building
     * @param position
     * @param side
     * @throws PlaceIsOccupiedException
     * @throws InvalidPositionException
     * @throws ReadOnlyException
     */
    public void add(AbstractBuilding building, int position, Side side)
        throws PlaceIsOccupiedException,
               InvalidPositionException,
               ReadOnlyException
    {
        if(getMode() == Mode.Read)
            throw new ReadOnlyException();

        else if(position < 0 || position > getLength() || position + building.getLength() > _length)
            throw new InvalidPositionException();

        AbstractBuilding[] targetSide = null;

        switch(side){
            case Front:
                targetSide = _front;
                break;

            case Back:
                targetSide = _back;
                break;
        }

        for(int i = position; i < getLength(); ++i)
            if(targetSide[i] != Empty)   
                throw new PlaceIsOccupiedException(position);

        for(int i = position; i < position + building.getLength(); ++i)
            targetSide[i] = building;
        
        building.setPosition(position);
    }

    /**
     * Remove the building that occupies the position 
     * specified in the specified side. 
     * returns if the position is empty.
     * @param position
     * @param side
     * @throws InvalidPositionException
     * @throws ReadOnlyException
     */
    public void remove(int position, Side side)
        throws InvalidPositionException,
               ReadOnlyException
    {
        if(getMode() == Mode.Read)
            throw new ReadOnlyException();

        else if(position < 0 || position > getLength())
            throw new InvalidPositionException();

        else if(_length == 0)
            throw new NullPointerException();

        AbstractBuilding[] targetSide = null;

        switch(side){
            case Front:
                targetSide = _front;
                break;

            case Back:
                targetSide = _back;
                break;
        }

        if(targetSide[position] == Empty)   
            return;

        int targetArea = targetSide[position].getPosition();
        targetArea += targetSide[position].getLength();
        for(int i = targetSide[position].getPosition(); i < targetArea; ++i)
            targetSide[i] = Empty;

        targetSide[position].setPosition(0);
    }

    /**
     * Return the building that occupies the specified 
     * position in the specified side.
     * @param position
     * @param side
     * @return The target building reference
     * @throws InvalidPositionException
     */
    public AbstractBuilding getBuilding(int position, Side side)
        throws InvalidPositionException
    {
        if(position < 0 || position > getLength())
            throw new InvalidPositionException();

        else if(_length == 0)
            throw new NullPointerException();

        AbstractBuilding[] targetSide = null;

        switch(side){
            case Front:
                targetSide = _front;
                break;

            case Back:
                targetSide = _back;
                break;
        }

        return targetSide[position];
    }

    /**
     * Return an array of strings representing the rows of 
     * the graph of the skyline silhouette of the buildings.
     * @return
     */
    public String[] getsilhouette(){
        int mainMaxHeight = 0;

        /**Find the highest building*/
        for(int i = 0; i < getLength(); ++i){
            if(_front[i].getHeight() > mainMaxHeight)
                mainMaxHeight = _front[i].getHeight();

            if(_back[i].getHeight() > mainMaxHeight)
                mainMaxHeight = _back[i].getHeight();
        }
        
        /**Create the string array */
        String[] silhouette = new String[mainMaxHeight + 2];

        /**Write the numbers for scaling*/
        silhouette[mainMaxHeight + 1] = "    0  ";
        silhouette[mainMaxHeight] = "  0 " + (char)0x2500;

        for(int i = 5; i < _length + 5; i += 5){
            for(int j = 0; j < 5; ++j)
                silhouette[mainMaxHeight] += (char)0x2500;
            silhouette[mainMaxHeight + 1] += String.format("  %-3d", i);
        }

        for(int i = mainMaxHeight - 1; i > 0; --i)
                silhouette[i] = "    ";

        for(int i = mainMaxHeight - 1; i > 0  && i >= 4; i -= 5){
            silhouette[i - 4] = String.format("%3d ", i-i/5-1);
        }

        silhouette[0] = String.format("%3d ", mainMaxHeight);

        AbstractBuilding highestBuilding, lastHighestBuilding = Empty;

        /**Print the silhouette itself */
        for(int i = 0; i < _length; ++i){   /**Go through building columns */
            highestBuilding = Empty;
            
            if(_front[i].getHeight() > _back[i].getHeight())
                highestBuilding = _front[i];

            else highestBuilding = _back[i];

            /**Remove the walls between close and overlapping buildings */
            if (lastHighestBuilding.getHeight() != highestBuilding.getHeight() &&
                lastHighestBuilding.getHeight() != 0 &&
                highestBuilding.getHeight() != 0)
            {
                int start, finish = mainMaxHeight;

                if (lastHighestBuilding.getHeight() > highestBuilding.getHeight()){
                    start = mainMaxHeight - highestBuilding.getHeight();
                    
                    silhouette[start++] += "\b" + (char)0x2514;

                    for(; start < finish; ++start)
                        silhouette[start] += "\b ";
                }
                
                else {
                    start = mainMaxHeight - lastHighestBuilding.getHeight() + 1;
                    silhouette[start - 1] += "\b" + (char)0x2500 + (char)0x2518;

                    for(; start < finish; ++start)
                        silhouette[start] += "\b  ";
                }
            }

            for(int j = 0; j < mainMaxHeight - highestBuilding.getHeight(); ++j)
                silhouette[j] += " ";

            /**Print spaces or walls acoording to situation */
            if(highestBuilding != Empty){
                silhouette[mainMaxHeight - highestBuilding.getHeight()] += (char)0x2500;

                if (highestBuilding.getPosition() == i &&
                    highestBuilding.getHeight() > lastHighestBuilding.getHeight())
                {
                    silhouette[mainMaxHeight - highestBuilding.getHeight()] += "\b" + (char)0x250C;
                    int start = mainMaxHeight - highestBuilding.getHeight() + 1;
                    int finish = mainMaxHeight - lastHighestBuilding.getHeight();

                    for(; start < finish; ++start)
                        silhouette[start] += "|";
                }

                else if(highestBuilding.getLength() + highestBuilding.getPosition() - 1 == i){
                    silhouette[mainMaxHeight - highestBuilding.getHeight()] += "\b" + (char)0x2510      ;

                    for(int j = mainMaxHeight - highestBuilding.getHeight() + 1; j < mainMaxHeight; ++j)
                        silhouette[j] += "|";
                }
                    
                
                else {
                    for(int j = mainMaxHeight - highestBuilding.getHeight() + 1; j < mainMaxHeight; ++j)
                        silhouette[j] += " ";
                }
            }

            /**Record the last highest building examined */
            lastHighestBuilding = highestBuilding;
        }

        return silhouette;
    }

    /**
     * Count the number of buildings of the 
     * given type in the given side.
     * @param type
     * @param side
     * @return number of buildings
     */
    public int countType(Class<AbstractBuilding> type, Side side) {
        int i = 0, counter = 0;
        AbstractBuilding tempBuilding;
        try {
            while(i < getLength()){
                tempBuilding = getBuilding(i, side);
                if(tempBuilding.getClass() == type){
                    ++counter;
                    i += tempBuilding.getLength();
                }
                
                else ++i;
            }
        } catch (Exception e) {}

        return counter;
    }

    /**
     * Return the total area occupied by the specified 
     * building type in the specified sidee.
     * @param type
     * @param side
     * @return total area occupied
     */
    public int countTypeArea(Class type, Side side) {
        int i = 0, counter = 0;
        AbstractBuilding tempBuilding;
        try {
            while(i < getLength()){
                tempBuilding = getBuilding(i, side);
                if(tempBuilding.getClass() == type){
                    counter += tempBuilding.getLength();
                    i += tempBuilding.getLength();
                }
                
                else ++i;
            }
        } catch (Exception e) {}

        return counter;
    }

    /**
     * return the ratio of the total area of playgrounds 
     * to the total length of the street sides.
     * @return ratio
     */
    public float playGroundRatio(){
        float totalArea = 0;

        totalArea += countTypeArea(PlayGround.class, Side.Front);
        totalArea += countTypeArea(PlayGround.class, Side.Back);

        return totalArea/((float)getLength()*2);
    }

    /**
     * Return the size of empty area in the 
     * specified side of the street.
     * @param side
     * @return
     */
    public int getEmptyArea(Side side){
        return countType(Empty.getClass(), side);
    }

    @Override
    public String toString() {
        String list = 
            String.format("\nFront side:\n");

        AbstractBuilding tempBuilding = null;
        int i = 0;
        try {
            while(i < _length){
                tempBuilding = getBuilding(i, Side.Front);
                if(tempBuilding != Empty)
                    list += tempBuilding.toString();
                
                i += tempBuilding.getLength();
            }
        } catch (Exception e) {}

        list += String.format("\nBack side:\n");

        tempBuilding = null;
        i = 0;
        try {
            while(i < _length){
                tempBuilding = getBuilding(i, Side.Back);
                if(tempBuilding != Empty)
                    list += tempBuilding.toString();
                
                i += tempBuilding.getLength();
            }
        } catch (Exception e) {}

        return list;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Street tempClone = null;
        AbstractBuilding tempBuilding = null;

        try {
            tempClone = new Street(_length);

            int i = 0;
            while(i < _length){
                tempBuilding = getBuilding(i, Side.Front);
                tempClone.add(tempBuilding, i, Side.Front);
                i += tempBuilding.getLength();
            }

            i = 0;
            while(i < _length){
                tempBuilding = getBuilding(i, Side.Back);
                tempClone.add(tempBuilding, i, Side.Back);
                i += tempBuilding.getLength();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }

        return tempClone;
    }
}