package org.example.sudokuproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.sudokuproject.src.SudokuBoard;

public enum Difficulty {

    Easy(1), Medium(10), Hard(30);

    private int liczbaPol;
    private Random random = new Random();
    private List<FieldCoordinates> list = new ArrayList<>();

    Difficulty(int liczbaPol) {
        this.liczbaPol = liczbaPol;
    }

    private void removeNumbers() {
        for (int i = 0; i < liczbaPol; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            FieldCoordinates fieldCoordinates = new FieldCoordinates(x, y);
            list.add(fieldCoordinates);
        }
    }

    public SudokuBoard selectDifficulty(SudokuBoard sudokuBoard, String level) {
        //Difficulty difficulty = Difficulty.valueOf(level);
        removeNumbers();
        for (int i = 0; i < list.size(); i++) {
            sudokuBoard.set(list.get(i).getX(), list.get(i).getY(), 0);
        }
        return sudokuBoard;

    }
}
