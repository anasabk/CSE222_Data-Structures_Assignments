package CPS;

import CPS.CPSException.*;
import CPS.Street.*;

/**
 * This is an abstract class used for buildings that 
 * have an owner, and is derived from AbstractBuilding class. 
 */
public abstract class Building extends AbstractBuilding{
    public String _owner;

    /**
     * Default constructor.(Uses super default constructor)
     */
    public Building() {
        super();
    }

    /**
     * A constructor that takes the dimensions of the building. 
     * @param length
     * @param height
     * @throws InvalidDimensionsException
     */
    public Building(int length, int height) 
        throws InvalidDimensionsException
    {
        super(length, height);
    }

    /**
     * A constructor that takes the dimensions and the owner name
     * @param length
     * @param height
     * @param owner
     * @throws InvalidDimensionsException
     */
    public Building(int length, int height, String owner)
        throws InvalidDimensionsException
    {
        super(length, height);
        _owner = owner;
    }

    /**
     * Set the owner name.
     * @param owner
     */
    public void setOwner(String owner){
        _owner = owner;
    }

    /**
     * Return the owner name.
     * @return
     */
    public String getOwner(){
        return _owner;
    }

    @Override
    public String toString() {
        return "\nOwner: " + _owner + super.toString();
    }
}
