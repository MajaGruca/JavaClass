package Lab2;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows*cols];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                data[i*cols +j]=0;
            }
        }
    }

    Matrix(double[][] d){
        rows = d.length;
        int i=0;
        int max_col=0;
        while(i<d.length)
        {
            if(max_col<d[i].length)
                max_col=d[i].length;
            i++;
        }
        cols = max_col;
        int n = rows*cols;
        data = new double[n];
        for(i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(d[i].length<=j)
                    data[i*cols +j]=0;
                else
                    data[i*cols +j]=d[i][j];
            }
        }

    }

    double get(int r,int c)
    {
        return data[(r-1)*cols + (c-1)];
    }

    void set(int r,int c, double value)
    {
        data[(r-1)*cols + (c-1)]=value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++)
            {
                buf.append(data[i*cols +j]);
                if(j!=cols-1)
                    buf.append(",");
            }
            buf.append("]");
            if(i!=rows-1)
                buf.append("\n");
        }
        buf.append("]");
        return buf.toString();
    }

    double[][] asArray()
    {
        double[][] tab = new double[rows][cols];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                tab[i][j]=data[i*cols +j];
            }
        }
        return tab;
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        else
        {
            rows=newRows;
            cols=newCols;
        }
    }

    int[] shape()
    {
        int[] tab={rows,cols};
        return tab;
    }

    Matrix add(Matrix m)
    {
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]+m.data[i*cols +j];
            }
        }
        return ret;
    }
    Matrix sub(Matrix m){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]-m.data[i*cols +j];
            }
        }
        return ret;
    }
    Matrix mul(Matrix m){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]*m.data[i*cols +j];
            }
        }
        return ret;
    }
    Matrix div(Matrix m){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]/m.data[i*cols +j];
            }
        }
        return ret;
    }

    Matrix add(double w){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]+w;
            }
        }
        return ret;
    }
    Matrix sub(double w){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]-w;
            }
        }
        return ret;
    }
    Matrix mul(double w){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]*w;
            }
        }
        return ret;
    }
    Matrix div(double w){
        Matrix ret = new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                ret.data[i*cols +j] = this.data[i*cols +j]/w;
            }
        }
        return ret;
    }

    Matrix dot(Matrix m)
    {
        Matrix ret = new Matrix(this.rows,m.cols);
        int i=0,j=0,licz = 0, suma=0;
        while(j<rows)
        {
            while (i < cols)
            {
                suma += data[i + j*cols]*m.data[i*m.cols + licz%m.cols];
                i++;
            }
            ret.data[licz] = suma;
            suma=0;
            i=0;
            licz++;
            if(licz%m.cols==0)
                j++;
        }
        return ret;
    }

    double frobenius()
    {
        double norma = 0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                norma += this.data[i*cols +j]*this.data[i*cols +j];
            }
        }
        norma = Math.sqrt(norma);
        return norma;
    }

    public static void main(String[] args)
    {

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
        double[][] tabd = wyk.asArray();
        //wyk.set(1,2,25);
        System.out.print((wyk.dot(wyk1)).toString());
    }

}