package org.example.sudokuproject;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1. ", "Mateusz Rybicki",},
                {"2. ", "Wojciech Swiderski"},
        };
    }
}