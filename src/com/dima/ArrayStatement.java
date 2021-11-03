package com.dima;



public class ArrayStatement {

    private int stepsNum;

    private int [][] globalArray;

    private int[] result;

    public int[] getResult() {
        return result;
    }

    public ArrayStatement() {

    }

    public int[] getGlobalArray(int number) {
        return globalArray[number];
    }

    public int getStepsNum() {
        return stepsNum;
    }

    public void generateNewSort(int [] arrayToSort) { // sort and save statements

        int [][] allStatements = new int [arrayToSort.length][arrayToSort.length];

        allStatements[0] = arrayToSort;

        int borderOfSecondIterator = arrayToSort.length ;

        for (int i = 1; i < allStatements.length; i++) {
            borderOfSecondIterator--;
            for (int j = 0; j < allStatements.length; j++) {
                allStatements[i][j] = allStatements[i - 1][j];
            }
            for (int j = 0; j < borderOfSecondIterator; j++) {
                if(allStatements[i][j+1]<allStatements[i][j]) {
                    int current = allStatements[i][j];
                    allStatements[i][j] = allStatements[i][j + 1];
                    allStatements[i][j + 1] = current;
                }
            }
        }
        stepsNum = allStatements.length;
        globalArray = allStatements;

        result = allStatements[allStatements.length-1];

        }

    }

