package lab2;

import java.util.Random;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i * cols + j] = 0;
            }
        }
    }

    Matrix(double[][] d) {
        rows = d.length;
        int i = 0;
        int max_col = 0;
        while (i < d.length) {
            if (max_col < d[i].length)
                max_col = d[i].length;
            i++;
        }
        cols = max_col;
        int n = rows * cols;
        data = new double[n];
        for (i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (d[i].length <= j)
                    data[i * cols + j] = 0;
                else
                    data[i * cols + j] = d[i][j];
            }
        }

    }

    double get(int r, int c) {
        return data[(r) * cols + (c)];
    }

    void set(int r, int c, double value) {
        data[(r) * cols + (c)] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[i * cols + j]);
                if (j != cols - 1)
                    buf.append(",");
            }
            buf.append("]");
            if (i != rows - 1)
                buf.append("\n");
        }
        buf.append("]");
        return buf.toString();
    }

    double[][] asArray() {
        double[][] tab = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tab[i][j] = data[i * cols + j];
            }
        }
        return tab;
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        else {
            rows = newRows;
            cols = newCols;
        }
    }

    int[] shape() {
        int[] tab = {rows, cols};
        return tab;
    }

    Matrix add(Matrix m) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] + m.data[i * cols + j];
            }
        }
        return ret;
    }

    Matrix sub(Matrix m) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] - m.data[i * cols + j];
            }
        }
        return ret;
    }

    Matrix mul(Matrix m) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] * m.data[i * cols + j];
            }
        }
        return ret;
    }

    Matrix div(Matrix m) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] / m.data[i * cols + j];
            }
        }
        return ret;
    }

    Matrix add(double w) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] + w;
            }
        }
        return ret;
    }

    Matrix sub(double w) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] - w;
            }
        }
        return ret;
    }

    Matrix mul(double w) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] * w;
            }
        }
        return ret;
    }

    Matrix div(double w) {
        Matrix ret = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ret.data[i * cols + j] = this.data[i * cols + j] / w;
            }
        }
        return ret;
    }

    Matrix dot(Matrix m) {
        Matrix ret = new Matrix(this.rows, m.cols);
        int i = 0, j = 0, licz = 0, suma = 0;
        while (j < rows) {
            while (i < cols) {
                suma += data[i + j * cols] * m.data[i * m.cols + licz % m.cols];
                i++;
            }
            ret.data[licz] = suma;
            suma = 0;
            i = 0;
            licz++;
            if (licz % m.cols == 0)
                j++;
        }
        return ret;
    }

    double frobenius() {
        double norma = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                norma += this.data[i * cols + j] * this.data[i * cols + j];
            }
        }
        norma = Math.sqrt(norma);
        return norma;
    }

    //////// GRUPA D /////////

    Matrix max(int axis){
        if(axis==1)
        {
            Matrix ret = new Matrix(this.rows,1);
            for(int i=0;i<this.rows;i++)
            {
                double maximum;
                maximum=this.data[i*this.cols];
                for(int j=0;j<this.cols;j++)
                {
                    if(this.data[i*this.cols+j]>maximum)
                        maximum=this.data[i*this.cols+j];
                }
                ret.data[i]=maximum;
            }
            return ret;
        }
        if(axis==0)
        {
            Matrix ret = new Matrix(1,this.cols);
            double[][] tab = this.asArray();
            for(int i=0;i<this.cols;i++)
            {
                double maximum;
                maximum=tab[0][i];
                for(int j=0;j<this.rows;j++)
                {
                    if(tab[j][i]>maximum)
                        maximum=tab[j][i];
                }
                ret.data[i]=maximum;
            }
            return ret;
        }
        if(axis==-1)
        {
            Matrix ret = new Matrix(1,1);
            double[][] tab = this.asArray();
            double maximum=tab[0][0];
            for(int i=0;i<this.rows;i++)
            {
                for(int j=0;j<this.cols;j++)
                {
                    if(tab[i][j]>maximum)
                        maximum=tab[i][j];
                }
            }
            ret.data[0]=maximum;
            return ret;
        }
        Matrix ret = new Matrix(0,0);
        return ret;
    }

    /////////////////////////


    public static void main(String[] args) {

        double[][] tab1 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        double[][] tab2 = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        Matrix wyk = new Matrix(tab1);
        Matrix wyk1 = new Matrix(tab2);

        System.out.print((wyk.max(-1)).toString());
    }
}