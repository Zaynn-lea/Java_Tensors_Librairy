
package lib.tensor.tensors;


import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import lib.tensor.tensors.tensors.Tensor;


public class global {
    @Test
    public void setAndGetTesters() {
        int[] tempSizes = new int[] {5, 9, 7, 2};
        Tensor t1 = new Tensor(4, tempSizes);
        
        assertTrue( t1.getDimension() == 4 );
        assertTrue( t1.getSize()  == tempSizes[0] );
        for (int i = 0; i < tempSizes.length; i++) {
            assertTrue( t1.getSize_k(i) == tempSizes[i] );
        }
        assertTrue( Arrays.equals(tempSizes, t1.getSizes()) );

        tempSizes = new int[] {1, 1, 6, 0};
        t1.setElement(1, tempSizes);
        System.out.println(t1.getElement(tempSizes));
        assertTrue( t1.getElement(tempSizes) == 1 );

        tempSizes = new int[] {2, 7};
        Tensor t2 = t1.getSubTensor(tempSizes);
    }
}
