package cz.educanet.tranformations;

import kotlin.NotImplementedError;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private final double[][] rawArray;

    public Matrix(double[][] rawArray) {
        this.rawArray = rawArray;
    }

    @Override
    public int getRows() {
        return rawArray.length;
    }

    @Override
    public int getColumns() {
        if (getRows() > 0)
            return rawArray[0].length;

        return 0;
    }

    @Override
    public IMatrix times(IMatrix matrix) {
        double[][] result = new double[getRows()][matrix.getColumns()];
        if(getColumns() == matrix.getRows()) {
            for (int rowA = 0; rowA < getRows(); rowA++) {
                for (int colB = 0; colB < matrix.getColumns(); colB++) {
                    for (int colA = 0; colA < getColumns(); colA++) {
                        result[rowA][colB] += get(rowA, colA) * matrix.get(colA, colB);
                    }
                }
            }
        }
        return MatrixFactory.create(result);
    }

    @Override
    public IMatrix times(Number scalar) {
        double[][] result = new double[getRows()][getColumns()];
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result[row][col] = get(row,col) * (double) scalar;
            }
        }
        return MatrixFactory.create(result);
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        double[][] result = new double[getRows()][getColumns()];

        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result[row][col] = get(row,col) + matrix.get(row,col);
            }
        }
        return MatrixFactory.create(result);
    }

    @Override
    public double get(int n, int m) {
        return rawArray[n][m];
    }

    //region Optional
    @Override
    public IMatrix transpose() {
        return null;
    }

    @Override
    public double determinant() {
        return 0;
    }
    //endregion
    //region Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        //return Arrays.equals(rawArray, matrix.rawArray);

        for (int row = 0; row < rawArray.length; row++) {
            if (!Arrays.equals(rawArray[row], matrix.rawArray[row]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rawArray);
    }
    //endregion
}
