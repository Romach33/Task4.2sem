package com.dima;

public class Main {

    private static GuiForm gui = null;

    public static GuiForm getGui() {
        return gui;
    }

    public static void main(String[] args) {
        GuiForm guiForm = new GuiForm();
        gui = guiForm;
    }
}
