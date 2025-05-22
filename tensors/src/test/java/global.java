
import tensors.Tensor;


public class global {
    public static void main(String[] args) {
        Tensor t = new Tensor(4, {5, 9, 7, 2});

        t.setElement(1, {1, 1, 6, 0});
    }
}
