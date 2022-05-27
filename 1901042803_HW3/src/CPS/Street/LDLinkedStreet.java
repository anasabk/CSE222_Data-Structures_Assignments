package CPS.Street;

import java.util.ListIterator;

import CPS.Exceptions.CPSException.*;
import CPS.LDLinkedList.LDLinkedList;

public class LDLinkedStreet implements Street{
    private Mode _mode;
    private final int _length;
    private LDLinkedList<AbstractBuilding> _frontSide, _backSide;

    public LDLinkedStreet(){
        _frontSide = new LDLinkedList<AbstractBuilding>();
        _backSide = new LDLinkedList<AbstractBuilding>();
        _mode = Mode.Read_Only;
        _length = 20;
    }

    public LDLinkedStreet(int length){
        _frontSide = new LDLinkedList<AbstractBuilding>();
        _backSide = new LDLinkedList<AbstractBuilding>();
        _mode = Mode.Read_Only;
        _length = length;
    }

    public void setMode(Mode mode){
        _mode = mode;
    }

    public int getLength(){
        return _length;
    }

    public Mode getMode(){
        return _mode;
    }

    public boolean add(AbstractBuilding building, int position, Side side)
        throws InvalidPositionException,
               ReadOnlyException
    {
        if (position > getLength() || position < 0)
            throw new InvalidPositionException();
        
        else if(_mode == Mode.Read_Only)
            throw new ReadOnlyException();

        if (position + building.getLength() > getLength())
            return false;

        LDLinkedList<AbstractBuilding> targetside;
        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        ListIterator<AbstractBuilding> iterator = targetside.listIterator();
        AbstractBuilding tempBuilding;

        while(iterator.hasNext()){
            tempBuilding = iterator.next();
            if (tempBuilding.getPosition() >=
                position + building.getLength())
            {
                iterator.add(tempBuilding);
                building.setPosition(position);
                return true;
            }

            else if ((tempBuilding.getPosition() + tempBuilding.getLength() > position) ||
                     (tempBuilding.getPosition() <= position + building.getLength() &&
                      tempBuilding.getPosition() + tempBuilding.getLength() > position + building.getLength()))
                return false;
        }

        targetside.add(building);
        building.setPosition(position);
        return true;
    }

    public AbstractBuilding remove(int index, Side side)
        throws ReadOnlyException
    {
        if(index < 0)
            throw new IndexOutOfBoundsException();

        else if(_mode == Mode.Read_Only)
            throw new ReadOnlyException();

        LDLinkedList<AbstractBuilding> targetside;
        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        if(index >= targetside.size())
            throw new IndexOutOfBoundsException();

        AbstractBuilding removed = targetside.remove(index);
        try {
            removed.setPosition(-1);
        } catch (Exception e) {}

        return removed;
    }

    public AbstractBuilding remove_Position(int position, Side side)
        throws InvalidPositionException,
               ReadOnlyException
    {
        if(_mode == Mode.Read_Only)
            throw new ReadOnlyException();

        else if(position >= getLength() || position < 0)
            throw new InvalidPositionException();

        LDLinkedList<AbstractBuilding> targetside;
        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        ListIterator<AbstractBuilding> 
            iterator = targetside.listIterator();
        
        AbstractBuilding tempBuilding;
        
        while(iterator.hasNext()){
            tempBuilding = iterator.next();
            if (tempBuilding.getPosition() <= position &&
                tempBuilding.getPosition() + tempBuilding.getLength() > position)
            {
                iterator.remove();
                tempBuilding.setPosition(-1);
                return tempBuilding;
            }
        }

        return null;
    }

    public AbstractBuilding get(int index, Side side){
        if(index < 0)
            return null;

        LDLinkedList<AbstractBuilding> targetside;
        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        if(index >= targetside.size())
            throw new IndexOutOfBoundsException();

        return targetside.get(index);
    }

    public AbstractBuilding get_Position(int position, Side side){
        if(position < 0 || position >= getLength())
            throw new IndexOutOfBoundsException();
        
        LDLinkedList<AbstractBuilding> targetside;
        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        for(AbstractBuilding temp : targetside)
            if (temp.getPosition() <= position &&
                temp.getPosition() + temp.getLength() > position)
                return temp;

        return Empty;
    }

    public String getsilhouette(){
    /**Find the highest building*/
        int mainMaxHeight = 0;
        for(int i = 0; i < getLength(); ++i){
            AbstractBuilding 
                tempBuilding = get_Position(i, Side.Front);
            if(tempBuilding.getHeight() > mainMaxHeight)
                mainMaxHeight = tempBuilding.getHeight();

            tempBuilding = get_Position(i, Side.Back);
            if(tempBuilding.getHeight() > mainMaxHeight)
                mainMaxHeight = tempBuilding.getHeight();
        }
        
    /**Create the string array */
        StringBuilder[] silhouette = new StringBuilder[mainMaxHeight + 2];

        for(int i = 0; i < mainMaxHeight + 2; ++i)
            silhouette[i] = new StringBuilder();

    /**Print the numbers for scaling*/
        silhouette[mainMaxHeight + 1].append("    0  ");
        silhouette[mainMaxHeight]
            .append("  0 ")
            .append((char)0x2500);

        /**Print the column numbers */
        for(int i = 5; i < getLength() + 5; i += 5){
            for(int j = 0; j < 5; ++j)
                silhouette[mainMaxHeight].append((char)0x2500);

            silhouette[mainMaxHeight + 1].append(String.format("  %-3d", i));
        }

        /**Print the row numbers */
        for(int i = mainMaxHeight - 1; i > 0; --i){
            if( (mainMaxHeight - i) % 5 == 0)
                silhouette[i].append(String.format("%3d ", mainMaxHeight - i));

            else
                silhouette[i].append("    ");
        }

        silhouette[0].append(String.format("%3d ", mainMaxHeight));

        AbstractBuilding highestBuilding, lastHighestBuilding = Empty;

    /**Print the silhouette itself */
        /**Go through building column by column from left to right*/
        for(int i = 0; i < getLength(); ++i){

            /**Find the highest building in the street */
            highestBuilding = Empty;
            if(get_Position(i, Side.Front).getHeight() > get_Position(i, Side.Back).getHeight())
                highestBuilding = get_Position(i, Side.Front);

            else 
                highestBuilding = get_Position(i, Side.Back);

            /**Remove the walls between close and overlapping buildings */
            if (lastHighestBuilding.getHeight() != highestBuilding.getHeight() &&
                lastHighestBuilding.getHeight() != 0 &&
                highestBuilding.getHeight() != 0)
            {
                int start, finish = mainMaxHeight;

                /**The building we are in is higher than the 
                 * previous building..
                 */
                if (lastHighestBuilding.getHeight() > highestBuilding.getHeight()){
                    start = mainMaxHeight - highestBuilding.getHeight(); 
                    silhouette[start++]
                        .append("\b")
                        .append((char)0x2514);

                    for(; start < finish; ++start)
                        silhouette[start].append("\b ");
                }
                
                /**The building we are in is lower than or equal
                 * to the previous building.
                 */
                else {
                    start = mainMaxHeight - lastHighestBuilding.getHeight() + 1;
                    silhouette[start - 1]
                        .append("\b")
                        .append((char)0x2500)
                        .append((char)0x2518);

                    for(; start < finish; ++start)
                        silhouette[start].append("\b  ");
                }
            }

            /**Fill the inside of the column with spaces */
            for(int j = 0; j < mainMaxHeight - highestBuilding.getHeight(); ++j)
                silhouette[j].append(" ");

            /**Print spaces or walls according to situation */
            if(highestBuilding != Empty){
                int start, finish;

                silhouette[mainMaxHeight - highestBuilding.getHeight()].append((char)0x2500);

                /**We are at the beginning of a building 
                 * and it's higher than the one before */
                if (highestBuilding.getPosition() == i &&
                    highestBuilding.getHeight() > lastHighestBuilding.getHeight())
                {
                    silhouette[mainMaxHeight - highestBuilding.getHeight()]
                        .append("\b")
                        .append((char)0x250C);
                    
                    start = mainMaxHeight - highestBuilding.getHeight() + 1;
                    finish = mainMaxHeight - lastHighestBuilding.getHeight();
                    for(; start < finish; ++start)
                        silhouette[start].append("|");
                }

                /**we are at the end of a building */
                else if(highestBuilding.getLength() + highestBuilding.getPosition() - 1 == i){
                    silhouette[mainMaxHeight - highestBuilding.getHeight()]
                        .append("\b")
                        .append((char)0x2510);
                    
                    start = mainMaxHeight - highestBuilding.getHeight() + 1;
                    finish = mainMaxHeight;
                    for(; start < finish; ++start)
                        silhouette[start].append("|");
                }
                    
                /**We are in the middle of a building */
                else {
                    start = mainMaxHeight - highestBuilding.getHeight() + 1;
                    finish = mainMaxHeight;
                    for(; start < finish; ++start)
                        silhouette[start].append(" ");
                }
            }

            /**Record the last highest building examined */
            lastHighestBuilding = highestBuilding;
        }

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < silhouette.length; ++i)
            result.append("\n")
                  .append(silhouette[i].toString());

        return result.append("\n").toString();
    }

    public int countType(String typeName, Side side){
        int counter = 0;
        LDLinkedList<AbstractBuilding> targetside;

        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        for(AbstractBuilding temp : targetside)
            if(temp.getClass().getSimpleName().equals(typeName))
                ++counter;

        return counter;
    }

    public int countTypeArea(String typeName, Side side){
        int counter = 0;
        LDLinkedList<AbstractBuilding> targetside;

        if(side == Side.Front)
            targetside = _frontSide;

        else
            targetside = _backSide;

        for(AbstractBuilding temp : targetside)
            if(temp.getClass().getSimpleName().equals(typeName))
                counter += temp.getLength();

        return counter;
    }

    public float playGroundRatio(){
        float total = countTypeArea("PlayGround", Side.Front);
        total += countTypeArea("PlayGround", Side.Back);

        return total / ((float)getLength()*2);
    }

    public int getEmptyArea(Side side){
        int counter = 0;
        for(int i = 0; i < getLength(); ++i)
            if(get_Position(i, side) == Empty)
                ++counter;

        return counter;
    }

    public int numOfBuildings(Side side){
        if(side == Side.Front)
            return _frontSide.size();

        else 
            return _backSide.size();
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("Length: " + _length + "\n");

        list.append("\nFront side:\nNumber of buildings: ")
            .append(numOfBuildings(Side.Front) + "\n");

        for(AbstractBuilding tempBuilding : _frontSide)
            list.append(tempBuilding.toString());

            list.append("\nBack side:\nNumber of buildings: ")
            .append(numOfBuildings(Side.Back) + "\n");

        for(AbstractBuilding tempBuilding : _backSide)
            list.append(tempBuilding.toString());

        return list.toString();
    }
}
