package Q2;

import java.util.Collection;

public class QuadTree {
    int _length, _width, _minX, _minY;
    private Node _root;

    private class Node {
        protected QuadTree _innerTree;
        protected Node _sideElement;
        protected int _pointX, _pointY, _quarterX, _quarterY;

        public Node(int quarterX, int quarterY) {
            if(quarterX != 1 && quarterX != 0
            || quarterY != 1 && quarterY != 0)
                throw new IllegalArgumentException();

            _innerTree = null;
            _sideElement = null;
            _pointX = -1;
            _pointY = -1;
            _quarterX = quarterX;
            _quarterY = quarterY;
        }

        public Node(int x, int y, int quarterX, int quarterY){
            if(x < _minX || x > _width
            || y < _minY || y > _length
            || quarterX != 1 && quarterX != 0
            || quarterY != 1 && quarterY != 0)
                throw new IllegalArgumentException();

            _innerTree = null;
            _sideElement = null;
            _pointX = x;
            _pointY = y;
            _quarterX = quarterX;
            _quarterY = quarterY;
        }

        public boolean isPoint() { return _pointX != -1 && _pointY != -1; }

        public boolean isTree() { return _innerTree != null; }

        public Node nextSide() { return _sideElement; }

        public boolean setTree() {
            if (!isTree() && _length > 1 && _width > 1) {
                _innerTree =
                        new QuadTree(_length / 2,
                                     _width / 2,
                                     _minX + _quarterX*_width / 2,
                                     _minY + _quarterY*_length / 2);

                int[] tempPoint = new int[] {_pointX, _pointY};
                _pointX = -1;
                _pointY = -1;
                _innerTree.insert(tempPoint[0], tempPoint[1]);
                return true;
            }

            return false;
        }

        public QuadTree setPoint(int x, int y) {
            if(x < _minX || x > _width
            || y < _minY || y > _length)
                throw new IllegalArgumentException();

            if(!isPoint()) {
                _pointX = x;
                _pointY = y;
                QuadTree tempTree = _innerTree;
                _innerTree = null;
                return tempTree;
            }

            return null;
        }

        public Node setSideElement(int quarterX, int quarterY) {
            Node temp = _sideElement;
            _sideElement = new Node(quarterX, quarterY);
            _sideElement._sideElement = temp;
            return  _sideElement;
        }

        public boolean insertPoint(int x, int y, int quarterX, int quarterY){
            if(x == _pointX || y == _pointY)
                return false;

            if(quarterX == _quarterX
            && quarterY == _quarterY){
                if(isPoint() && setTree() || isTree())
                    return _innerTree.insertPoint(x, y);

                else return false;
            }

            else if(_sideElement == null){
                _sideElement = new Node(x, y, quarterX, quarterY);
                return true;
            }

            else return _sideElement.insertPoint(x, y, quarterX, quarterY);
        }

        public QuadTree getTree() {
            if(isTree())
                return _innerTree;
            else return null;
        }

        public int[] getPoint() {
            if(isPoint())
                return new int[] {_pointX, _pointY};
            else return null;
        }

        @Override
        public String toString() {
            return toString(0, new StringBuilder()).toString();
        }

        private StringBuilder toString(int depth, StringBuilder output) {
            output.append(" ".repeat(Math.max(0, depth)));

            if(_quarterY == 1)
                output.append("Upper ");
            else
                output.append("Bottom ");

            if(_quarterX == 1)
                output.append("Right\n");
            else
                output.append("Left\n");

            if(isPoint()) {
                output.append(" ".repeat(Math.max(0, depth)))
                      .append(_pointX).append(", ")
                      .append(_pointY).append('\n');
            }

            else if(isTree())
                _innerTree.toString(depth + 1, output);

            if(_sideElement != null)
                _sideElement.toString(depth + 1, output);

            return output;
        }
    }

    public QuadTree(){
        _root = null;
        _width = 100;
        _length = 100;
    }

    public QuadTree(int length, int width){
        _root = null;
        _length = length;
        _width = width;
        _minY = 0;
        _minX = 0;
    }

    private QuadTree(int length, int width, int minX, int minY){
        _root = null;
        _length = length;
        _width = width;
        _minY = minY;
        _minX = minX;
    }

    public QuadTree(Collection<int[]> col) {
        this(100, 100);

        if(col != null){
            for(int[] temp : col)
                if(!insert(temp[0], temp[1]))
                    throw new IllegalStateException();
        }

        else throw new IllegalArgumentException();
    }

    public boolean insert(int x, int y){
        if(x >= _width || y >= _length
        || x < 0       || y < 0)
            throw new IllegalArgumentException();

        return insertPoint(x, y);
    }

    private boolean insertPoint(int x, int y) {
        int targetQuarterX = 0, targetQuarterY = 0;
        if(x >= _width/2)
            targetQuarterX = 1;
        if(y >= _length/2)
            targetQuarterY = 1;

        if(x < _minX || x >= _minX + _width
        || y < _minY || y >= _minY + _length)
            return false;

        if(_root == null){
            _root = new Node(x, y, targetQuarterX, targetQuarterY);
            return true;
        }

        else return _root.insertPoint(x, y,
                    targetQuarterX, targetQuarterY);
    }

    @Override
    public String toString() {
        return toString(0, new StringBuilder()).toString();
    }

    private StringBuilder toString(int depth, StringBuilder output) {
        if(_root != null)
            _root.toString(depth, output);

        return output;
    }
}
