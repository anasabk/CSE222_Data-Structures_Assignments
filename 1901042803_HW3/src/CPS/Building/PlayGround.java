package CPS.Building;

import CPS.Exceptions.CPSException.*;
import CPS.Street.Street.*;

/**
 * This is an abstract class used t represent a playground, 
 * which is building 2 meters high by default, 
 * and is derived from AbstractBuilding class.
 */
public class PlayGround extends AbstractBuilding{
    /**
     * Default constructor
     * @throws InvalidDimensionsException
     */
    public PlayGround()
        throws InvalidLengthException,
               InvalidHeightException
    {
        super(0, 3);
    }

    /**
     * A constructor that takes the length
     * @param length
     * @throws InvalidDimensionsException
     */
    public PlayGround(int length)
        throws InvalidLengthException,
               InvalidHeightException
    {
        super(length, 2);
    }

    /**
     * This method is made useless because playgrounds 
     * have default height.
     */
    public final void setHeight(int height)
        throws SetHeightNotSupportedException
    {
        throw new SetHeightNotSupportedException();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return (super.equals(obj) && getClass() == obj.getClass());
    }

    @Override
    public String focus() {
        return String.format("%d", getLength());
    }

    @Override
    public String toString() {
        return "\nType: Playgorund" + super.toString();
    }
}
