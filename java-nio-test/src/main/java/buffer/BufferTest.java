package buffer;

import java.nio.*;

/**
 * <pre>
 *
 buffer(abstract) --> byteBuffer(abstract) --> HeapByteBuffer(impl)

 byteBuffer: java.nio.HeapByteBuffer
 shortBuffer: java.nio.HeapShortBuffer
 intBuffer: java.nio.HeapIntBuffer
 longBuffer: java.nio.HeapLongBuffer
 doubleBuffer: java.nio.HeapDoubleBuffer
 charBuffer: java.nio.HeapCharBuffer



 *     <pre/>
 */

public class BufferTest {
    public static void main(String[] args) {
        byte[] byteArray = new byte[] { 1, 2, 3 };
        short[] shortArray = new short[] { 1, 2, 3, 4 };
        int[] intArray = new int[] { 1, 2, 3, 4, 5 };
        long[] longArray = new long[] { 1, 2, 3, 4, 5, 6 };
        float[] floatArray = new float[] { 1, 2, 3, 4, 5, 6, 7 };
        double[] doubleArray = new double[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        char[] charArray = new char[] { 'a', 'b', 'c', 'd' };

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println();

        System.out.println("byteBuffer: " + byteBuffer.getClass().getName());
        System.out.println("shortBuffer: " + shortBuffer.getClass().getName());
        System.out.println("intBuffer: " + intBuffer.getClass().getName());
        System.out.println("longBuffer: " + longBuffer.getClass().getName());
        System.out.println("doubleBuffer: " + doubleBuffer.getClass().getName());
        System.out.println("charBuffer: " + charBuffer.getClass().getName());
    }
}
