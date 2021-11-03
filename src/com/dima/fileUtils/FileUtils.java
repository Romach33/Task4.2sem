package com.dima.fileUtils;

import com.dima.Imported_Utils.ArrayUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;


public class FileUtils {

    public static void save(int[] arrayToSave) {
        String way = saveWindow();
        int [] toSave = arrayToSave;
        savingRebArray(toSave,way);
    }

    public static int[] load() {
        String way = loadWindow();
        int [] preLoad = readingArray(way);
        int [] loaded = preLoad;
        return loaded;
    }

    private static String loadWindow() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        chooser.showDialog(null, "Load");

        File file = chooser.getSelectedFile();
        String path = file.getAbsolutePath();
        return path;
    }

    private static String saveWindow() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        chooser.showDialog(null, "Save");

        File file = chooser.getSelectedFile();
        String path = file.getAbsolutePath();
        return path;
    }

    private static int [] readingArray(String way) {
        return ArrayUtils.readIntArrayFromFile(way);
    }
    private static void savingRebArray(int[] array, String way) {
        try {
            ArrayUtils.writeArrayToFile(way, array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}