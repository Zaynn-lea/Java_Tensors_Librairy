
package lib.tensor.tensors;


import lib.tensor.tensors.tensors.Tensor;


public class Main {
    public static void main(String[] args) {
        int[] tempSizes = new int[] {5, 9, 7, 2};
        Tensor t = new Tensor(4, tempSizes);

        tempSizes = new int[] {1, 1, 6, 0};
        t.setElement(1, tempSizes);
    }
}
