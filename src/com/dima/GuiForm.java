package com.dima;

import com.dima.Imported_Utils.ArrayUtils;
import com.dima.Imported_Utils.JTableUtils;
import com.dima.fileUtils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiForm extends JFrame {
    private JButton insertNewArrayButton;
    private JButton forwardButton;
    private JButton backButton;
    private JTable table1;
    private JPanel mainP;
    private JLabel curr;

    private int currentStep;

    private int maxEk;

    private JLabel maxVIs;
    private JLabel status;
    private JLabel sortedElemsNum;
    private JTextField textField1;
    private JButton manualARRAYButton;
    private JTable table2;
    private JButton saveSORTEDButton;
    private JLabel statusOfEquality;

    private ArrayStatement localSolution = null;

    //default bounds
        private final int x0 = 0;
        private final int y0 = 0;
        private final int xLen = 500;
        private final int yLen = 375;

    public int getCurrentStep() {
        return currentStep;
    }

    public int getMax() {
        return maxEk;
    }

    public GuiForm() {

        prepareFrame();

        insertNewArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] newArray = FileUtils.load();



                localSolution.generateNewSort(newArray);

                //1-st step show

                currentStep = 1;

                forwardButton.setEnabled(true);
                backButton.setEnabled(true);

                maxEk = localSolution.getStepsNum();

                maxVIs.setText("" + maxEk);

                curr.setText("" + currentStep);

                status.setText("LOADED");
                status.setForeground(Color.green);

                sortedElemsNum.setText("" + (currentStep-1));

                updateStatement(currentStep-1);

                checkStatuses();

                JTableUtils.writeArrayToJTable(table2, localSolution.getResult());
                saveSORTEDButton.setEnabled(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStep--;
                checkStatuses();
                updateStatement(currentStep-1);
                sortedElemsNum.setText("" + (currentStep-1));
                curr.setText("" + currentStep);
            }
        });
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStep++;
                checkStatuses();
                updateStatement(currentStep-1);
                sortedElemsNum.setText("" + (currentStep-1));
                curr.setText("" + currentStep);
            }
        });
        manualARRAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] newArray = ArrayUtils.toIntArray(textField1.getText());
                localSolution.generateNewSort(newArray);
                //1-st step show
                currentStep = 1;
                forwardButton.setEnabled(true);
                backButton.setEnabled(true);
                maxEk = localSolution.getStepsNum();
                maxVIs.setText("" + maxEk);
                curr.setText("" + currentStep);
                status.setText("LOADED");
                status.setForeground(Color.green);
                sortedElemsNum.setText("" + (currentStep-1));
                updateStatement(currentStep-1);
                checkStatuses();
                JTableUtils.writeArrayToJTable(table2, localSolution.getResult());

                saveSORTEDButton.setEnabled(true);


            }
        });
        saveSORTEDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileUtils.save(localSolution.getResult());
            }
        });
    }

    private void prepareFrame() {

        setTitle("BUBBLE SORT UTIL");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int[] nullArray = { 0 , 0 , 0 , 0, 0 , 0 , 0 , 0 }; // preparing jtable
        JTableUtils.writeArrayToJTable(table1, nullArray);
        JTableUtils.writeArrayToJTable(table2, nullArray);

        setBounds(x0, y0, xLen, yLen);

        forwardButton.setEnabled(false); // preparing buttons
        backButton.setEnabled(false);

        setContentPane(mainP);
        saveSORTEDButton.setEnabled(false);
        setVisible(true);

        ArrayStatement local = new ArrayStatement();

        localSolution = local;

    }
    
    public void updateStatement(int stepNumber) {
        int[] current = localSolution.getGlobalArray(stepNumber);
        JTableUtils.writeArrayToJTable(table1, current);

        for (int i = 0; i < stepNumber; i++) {
                table1.setDefaultRenderer(Object.class, new TableInfoRenderer());
        }
    }
    private void checkStatuses() {
        if (currentStep == 1) {
            backButton.setEnabled(false);
            statusOfEquality.setText("Nothing Done ");
            statusOfEquality.setForeground(Color.red);
        }
        if (currentStep == maxEk) {
            forwardButton.setEnabled(false);
            statusOfEquality.setText("Process DONE");
            statusOfEquality.setForeground(Color.green);
        }
        if(currentStep < maxEk) {
            forwardButton.setEnabled(true);
            statusOfEquality.setText(" ");

        }
        if (currentStep > 1) {
            backButton.setEnabled(true);
            statusOfEquality.setText(" ");
        }
    }
}
