
package lib.tensor.tensors;


import static org.junit.Assert.assertTrue;
import org.junit.Test;

import lib.tensor.tensors.tensors.Tensor;


public class global {
    @Test
    public void setAndGetTesters() {
        int[] tempSizes = new int[] {5, 9, 7, 2};
        Tensor t1 = new Tensor(4, tempSizes);

        tempSizes = new int[] {7, 2};
        Tensor t2 = new Tensor(2, tempSizes);

        tempSizes = new int[] {1, 1, 6, 0};
        t1.setElement(1, tempSizes);
        System.out.println(t1.getElement(tempSizes));
        assertTrue( t1.getElement(tempSizes) == 1 );
    }
}
