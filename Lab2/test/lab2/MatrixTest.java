package lab2;
import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void Matrix() throws Exception {

    }

    public void get() throws Exception {
        double[][] tab = {{1,2,5,4,1,65463},{3,4,9}};
        Matrix expected = new Matrix(tab);
        for(int i=1;i<=tab.length;i++)
            for(int j=1;j<=tab[i].length;j++)
            {
                assertEquals(expected.get(i, j), tab[i][j], 1e-5);
            }
    }

    @org.junit.Test
    public void set() throws Exception {
        Matrix expected = new Matrix(4,5);
        for(int i=1;i<=4;i++)
            for(int j=1;j<=5;j++)
            {
                expected.set(i,j,i);
                assertEquals(expected.get(i,j), i, 1e-5);
            }
    }

    @org.junit.Test
    public void testToString() throws Exception {
        double[][] tab = {{1.3,2.56,5,4.3,1,65463},{3,4.1111,9}};
        Matrix expected = new Matrix(tab);
        String actual = "[[1.3,2.56,5.0,4.3,1.0,65463.0]\n[3.0,4.1111,9.0,0.0,0.0,0.0]]";
        String copy = expected.toString();
        assertEquals(copy, actual);
        int n=0;
        for(int i=0;i<copy.length();i++)
            if(copy.charAt(i)==',')
                n++;
        assertEquals(n, (tab[0].length-1)*tab.length);
        n=0;
        for(int i=0;i<copy.length();i++)
            if(copy.charAt(i)=='['||copy.charAt(i)==']')
                n++;
        assertEquals(n, 2+2*tab.length);
    }

    @org.junit.Test
    public void asArray() throws Exception {
    }

    @org.junit.Test
    public void reshape() throws Exception {
    }

    @org.junit.Test
    public void shape() throws Exception {
    }

    @org.junit.Test
    public void add() throws Exception {
    }

    @org.junit.Test
    public void sub() throws Exception {
    }

    @org.junit.Test
    public void mul() throws Exception {
    }

    @org.junit.Test
    public void div() throws Exception {
    }

    @org.junit.Test
    public void add1() throws Exception {
    }

    @org.junit.Test
    public void sub1() throws Exception {
    }

    @org.junit.Test
    public void mul1() throws Exception {
    }

    @org.junit.Test
    public void div1() throws Exception {
    }

    @org.junit.Test
    public void dot() throws Exception {
    }

    @org.junit.Test
    public void frobenius() throws Exception {
    }

    @org.junit.Test
    public void main() throws Exception {
    }

}