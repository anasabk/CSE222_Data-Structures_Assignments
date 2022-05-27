package CPS.Exceptions;

public class CPSException {
    /**
     * Thrown when the number of rooms entered is invalid.
     */
    public static class InvalidNumOfRoomsException 
        extends Exception
    {
        public InvalidNumOfRoomsException() {
            super("Invalid number of rooms");
        }
    }

    /**
     * Thrown when the height of a building is invalid.
     */
    public static class InvalidHeightException 
        extends Exception
    {
        public InvalidHeightException(int height) {
            super("Height " + height + " is invalid");
        }
    }

    /**
     * Thrown when the length of a building is invalid.
     */
    public static class InvalidLengthException 
        extends Exception
    {
        public InvalidLengthException(int length) {
            super("Length " + length + " is invalid");
        }
    }

    /**
     * Thrown when an invalid position is entered.
     */
    public static class InvalidPositionException 
        extends Exception
    {
        public InvalidPositionException() {
            super("Invalid Position Detected");
        }

        public InvalidPositionException(int position) {
            super("The position" + position + "is invalid");
        }
    }

    /**
     * Thrown when invalid hours and minutes are entered.
     */
    public static class InvalidTimeException 
        extends Exception
    {
        public InvalidTimeException() {
            super("Invalid time");
        }
    }

    /**
     * Thrown when the building has a default non-editable height.
     */
    public static class SetHeightNotSupportedException 
        extends Exception
    {
        public SetHeightNotSupportedException() {
            super("Editting height is not supported");
        }
    }

    /**
     * Thrown when a modification is made in read only mode.
     */
    public static class ReadOnlyException
        extends Exception
    {
        public ReadOnlyException() {
            super("Read Only Mode is Selected");
        }
    }

    /**
     * Thrown when a building is being placed on 
     * top of another one in the same side.
     */
    public static class PlaceIsOccupiedException 
        extends Exception
    {
        public PlaceIsOccupiedException() {
            super("Place is already in use");
        }

        public PlaceIsOccupiedException(int index) {
            super("Place " + index +" is already in use");
        }
    }
}
