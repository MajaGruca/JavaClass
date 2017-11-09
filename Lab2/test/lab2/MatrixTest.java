package lab2;
import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void Matrix() throws Exception {

    }

    public void get() throws Exception {
        double[][] tab = {{1,2,5,4,1,65463},{3,4,9}};
        Matrix actual = new Matrix(tab);
        for(int i=1;i<=tab.length;i++)
            for(int j=1;j<=tab[i].length;j++)
            {
                assertEquals(tab[i][j], actual.get(i, j), 1e-5);
            }
    }

    @org.junit.Test
    public void set() throws Exception {
        Matrix actual = new Matrix(4,5);
        for(int i=1;i<=4;i++)
            for(int j=1;j<=5;j++)
            {
                actual.set(i,j,i);
                assertEquals(i, actual.get(i,j), 1e-5);
            }
    }

    @org.junit.Test
    public void testToString() throws Exception {
        double[][] tab = {{1.3,2.56,5,4.3,1,65463},{3,4.1111,9}};
        Matrix actual = new Matrix(tab);
        String expected = "[[1.3,2.56,5.0,4.3,1.0,65463.0]\n[3.0,4.1111,9.0,0.0,0.0,0.0]]";
        String copy = actual.toString();
        assertEquals(expected, copy);
        int n=0;
        for(int i=0;i<copy.length();i++)
            if(copy.charAt(i)==',')
                n++;
        assertEquals(10, n);
        n=0;
        for(int i=0;i<copy.length();i++)
            if(copy.charAt(i)=='['||copy.charAt(i)==']')
                n++;
        assertEquals(6, n);
    }

    @org.junit.Test
    public void asArray() throws Exception {
        double[][] tab = {{1,2,3,3434,5466.474},{565,78,33}};
        Matrix actual = new Matrix(tab);
        double[][] act_tab = actual.asArray();
        double[][] exp_tab = {{1,2,3,3434,5466.474},{565,78,33,0,0}};
        for(int i=0;i<actual.rows;i++)
        {
            for(int j=0;j<actual.cols;j++)
            {
                assertEquals(exp_tab[i][j], act_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void reshape() throws Exception {
        Matrix actual = new Matrix(10,15);
        actual.reshape(3,50);
        assertEquals(3, actual.rows,1e-5);
        assertEquals(50, actual.cols,1e-5);
        try {
            actual.reshape(6,5);
            fail("Exception wasn't thrown!");
        }
        catch (RuntimeException exception) {
            assertEquals("3 x 50 matrix can't be reshaped to 6 x 5", exception.getMessage());
        }
    }

    @org.junit.Test
    public void shape() throws Exception {
        Matrix actual = new Matrix(5,8);
        int[] act = actual.shape();
        int[] exp = {5,8};
        assertArrayEquals(exp,act);
    }

    @org.junit.Test
    public void add() throws Exception {
        double[][] tab = {{88.5641556,95.2222,9999},{564.5616,666,32.32}};
        Matrix actual = new Matrix(tab);
        assertEquals(0,(actual.add(actual.mul(-1)).frobenius()),1e-5);
    }

    @org.junit.Test
    public void sub() throws Exception {
        double[][] tab = {{54,65.55,87},{123.445555,1348,92}};
        Matrix actual = new Matrix(tab);
        assertEquals(0,(actual.sub(actual).frobenius()),1e-5);
    }

    @org.junit.Test
    public void mul() throws Exception {
        double[][] tab = {{88.5641556,95.2222,9999},{564.5616,666,32.32}};
        Matrix actual = new Matrix(tab);
        assertEquals(0,(actual.add(actual.mul(-1)).frobenius()),1e-5);
    }

    @org.junit.Test
    public void div() throws Exception {
        double[][] tab = {{54,65.55,87},{123.445555,1348,92}};
        Matrix actual = new Matrix(tab);
        assertEquals(Math.sqrt(6),(actual.div(actual).frobenius()),1e-5);
    }

    @org.junit.Test
    public void add1() throws Exception {
        double[][] tab = {{54,65.55,87},{123.445555,1348,92}};
        Matrix actual = new Matrix(tab);
        double[][] act_tab = (actual.add(20)).asArray();
        double[][] exp_tab = {{74,85.55,107},{143.445555,1368,112}};
        for(int i=0;i<actual.rows;i++)
        {
            for(int j=0;j<actual.cols;j++)
            {
                assertEquals(exp_tab[i][j], act_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void sub1() throws Exception {
        double[][] tab = {{54,65.55,87},{123.445555,1348,92}};
        Matrix actual = new Matrix(tab);
        double[][] act_tab = (actual.sub(20)).asArray();
        double[][] exp_tab = {{34,45.55,67},{103.445555,1328,72}};
        for(int i=0;i<actual.rows;i++)
        {
            for(int j=0;j<actual.cols;j++)
            {
                assertEquals(exp_tab[i][j], act_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void mul1() throws Exception {
        double[][] tab = {{5,10,15},{6,4}};
        Matrix actual = new Matrix(tab);
        double[][] act_tab = (actual.mul(actual)).asArray();
        double[][] exp_tab = {{25,100,225},{36,16,0}};
        for(int i=0;i<actual.rows;i++)
        {
            for(int j=0;j<actual.cols;j++)
            {
                assertEquals(exp_tab[i][j], act_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void div1() throws Exception {
        double[][] tab = {{5,10,15},{20,25}};
        Matrix actual = new Matrix(tab);
        double[][] act_tab = (actual.div(5)).asArray();
        double[][] exp_tab = {{1,2,3},{4,5,0}};
        for(int i=0;i<actual.rows;i++)
        {
            for(int j=0;j<actual.cols;j++)
            {
                assertEquals(exp_tab[i][j], act_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void dot() throws Exception {
        double[][] tab1 = {{5,10}};
        double[][] tab2 = {{1},{2}};
        Matrix actual1 = new Matrix(tab1);
        Matrix actual2 = new Matrix(tab2);
        double[][] act1_tab = (actual1.dot(actual2)).asArray();
        double[][] exp1_tab = {{25}};
        for(int i=0;i<actual1.rows;i++)
        {
            for(int j=0;j<actual2.cols;j++)
            {
                assertEquals(exp1_tab[i][j], act1_tab[i][j],1e-5);
            }
        }
        double[][] act2_tab = (actual2.dot(actual1)).asArray();
        double[][] exp2_tab = {{5,10},{10,20}};
        for(int i=0;i<actual2.rows;i++)
        {
            for(int j=0;j<actual1.cols;j++)
            {
                assertEquals(exp2_tab[i][j], act2_tab[i][j],1e-5);
            }
        }
    }

    @org.junit.Test
    public void frobenius() throws Exception {
        double[][] tab1 = {{2,3,4},{5,8}};
        Matrix actual = new Matrix(tab1);
        double act = actual.frobenius();
        assertEquals(Math.sqrt(118), act,1e-5);
    }

}