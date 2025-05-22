
/*
 Class defining a tensor of arbitrary dimension

 TODO : proper documentation

 TODO : find and use a proper assertion librairy

 TODO : test
*/


package lib.tensor.tensors.tensors;


public class Tensor {

    // Fields

    private int      _dimension;
    private int[]    _sizes;
    private int      _element;
    private Tensor[] _elements;


    // Constructors and Destructors

    public Tensor() {}
    public Tensor(int dimension, int[] sizes) {
        assert dimension >= 0            : "negative dimension";
        assert sizes.length == dimension : "dimension and number of sizes doesn't match";

        for (int s: sizes) {
            assert s > 0 : "non positive size";
        }

        _dimension  = dimension;
        _sizes      = sizes;

        _CreateElements();
    }
    private Tensor(int dimension, int[] sizes, boolean initElt) {
        assert dimension >= 0            : "negative dimension";
        assert sizes.length == dimension : "dimension and number of sizes doesn't match";

        for (int s: sizes) {
            assert s > 0 : "non positive size";
        }

        _dimension  = dimension;
        _sizes      = sizes;

        if (initElt)    _CreateElements();
    }


    // Getters

    public int   getDimension()   { return _dimension; }
    public int[] getSizes()       { return _sizes; }
    public int   getSize()        { return _sizes[0]; }
    public int   getSize_k(int k) { assert (0 <= k) && (k < _dimension) : "0 <= k < dimension not satisfied" ; return _sizes[k]; }

    public int getElement()      { assert _dimension == 0 : "non 0 dimension" ; return _element; }
    public int getElement(int k) {
        assert _dimension == 0 || (0 <= k) && (k < _dimension) : "0 <= k < dimension not satisfied";

        return _elements[k].getElement();
    }
    public int getElement(int [] ks) {
        assert ks.length <= _dimension             : "0 <= ks.length < dimension not satisfied" ;
        assert (0 <= ks[0]) && (ks[0] < _sizes[0]) : "0 <= ks[0] < size not satisfied" ;

        if (ks.length == 1) return getElement(ks[0]);


        int[] tempSizes = new int[ks.length - 1];
        System.arraycopy(ks, 1, tempSizes, 0, ks.length - 1);

        return _elements[ks[0]].getElement(tempSizes);
    }

    public Tensor getSubTensor(int k) {
        assert _dimension == 0 || (0 <= k) && (k < _dimension) : "0 <= k < dimension not satisfied";

        return _elements[k];
    }
    public Tensor getSubTensor(int [] ks) {
        assert ks.length <= _dimension             : "0 <= ks.length < dimension not satisfied" ;
        assert (0 <= ks[0]) && (ks[0] < _sizes[0]) : "0 <= ks[0] < size not satisfied" ;

        if (ks.length == 1)     return getSubTensor(ks[0]);


        int[] tempSizes = new int[ks.length - 1];
        System.arraycopy(ks, 1, tempSizes, 0, ks.length - 1);

        return _elements[ks[0]].getSubTensor(tempSizes);
    }

    // Setters

    public void setElement(int new_element) { assert _dimension == 0 : "non 0 dimension" ; _element = new_element; }
    public void setElement(Tensor new_element, int index) {
        assert new_element.getDimension() == _dimension - 1 : "dimensions of source and destination doesn't match";
        assert (0 <= index) && (index < _sizes[0])           : "0 <= index < size not satisfied";

        _elements[index] = new_element;
    }
    public void setElement(int new_element, int index) {
        assert (0 <= index) && (index < _sizes[0])           : "0 <= index < size not satisfied";

        _elements[index].setElement(new_element);
    }
    public void setElement(int new_element, int[] ks) {
        assert ks.length == _dimension                               : "0 <= ks.length < dimension not satisfied";
        assert (0 <= ks[0]) && (ks[0] < _sizes[0])                   : "0 <= index < size not satisfied";

        if (ks.length != 1) {
            int[] tempSizes = new int[ks.length - 1];
            System.arraycopy(ks, 1, tempSizes, 0, ks.length - 1);

            _elements[ks[0]].setElement(new_element, tempSizes);
        } else {
            setElement(new_element, ks[0]);
        }
    }
    public void setElement(Tensor new_element, int[] ks) {
        assert ks.length < _dimension                                : "0 <= ks.length < dimension not satisfied";
        assert new_element.getDimension() == _dimension - ks.length : "dimensions of source and destination doesn't match";
        assert (0 <= ks[0]) && (ks[0] < _sizes[0])                   : "0 <= index < size not satisfied";

        if (ks.length != 1) {
            int[] tempSizes = new int[ks.length - 1];
            System.arraycopy(ks, 1, tempSizes, 0, ks.length - 1);

            _elements[ks[0]].setElement(new_element, tempSizes);
        } else {
            setElement(new_element, ks[0]);
        }
    }


    // Operations

    public Tensor add(Tensor other) {
        assert _dimension == other.getDimension() : "both tensors must be of the same dimension";
        assert _sizes == other.getSizes()         : "both tensors must have the same sizes";

        Tensor answer = new Tensor(_dimension, _sizes, false);


        if (_dimension != 0) {
            for (int i = 0; i < _dimension; i++) {
                answer.setElement(_elements[i].add(other.getSubTensor(i)), i);
            }
        } else {
            _element += other.getElement();
        }


        return answer;
    }

    public Tensor product(int scalar) {
        Tensor answer;

        if (scalar != 0) {
            answer = new Tensor(_dimension, _sizes, false);

            if (_dimension != 0) {
                for (int i = 0; i < _dimension; i++) {
                    answer.setElement(_elements[i].product(scalar), i);
                }
            } else {
                _element *= scalar;
            }
        } else {
            answer = new Tensor(_dimension, _sizes);
        }

        return answer;
    }


    // Algorithms

    // TODO


    // Private inner working

    /**
     * The tensor is defined recursively
     * */
    private void _CreateElements() {
        if (_dimension == 0)
            _element = 0;

        else {
            int[] tempSizes = new int[_dimension - 1];
            System.arraycopy(_sizes, 1, tempSizes, 0, _dimension - 1);

            _elements = new Tensor[_sizes[0]];

            for (int i = 0; i < _sizes[0]; i++) {
                _elements[i] = new Tensor(_dimension - 1, tempSizes);
            }
        }
    }

}