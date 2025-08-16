package com.lab5.lab7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] cells = new Button[3][3];
    // 0 = puste, 1 = X, 2 = O
    private int[][] board = new int[3][3];

    private char current = 'X';
    private double scoreX = 0.0;
    private double scoreO = 0.0;

    private TextView tvTurn, tvScoreX, tvScoreO;
    private Button btnNewGame, btnResetScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTurn = findViewById(R.id.tvTurn);
        tvScoreX = findViewById(R.id.tvScoreX);
        tvScoreO = findViewById(R.id.tvScoreO);
        btnNewGame = findViewById(R.id.btnNewGame);
        btnResetScores = findViewById(R.id.btnResetScores);

        initBoardButtons();

        btnNewGame.setOnClickListener(v -> startNewGame(false));
        btnResetScores.setOnClickListener(v -> {
            scoreX = 0.0;
            scoreO = 0.0;
            updateScores();
            startNewGame(true);
        });

        // Pierwsza gra
        startNewGame(true);
    }

    private void initBoardButtons() {
        cells[0][0] = findViewById(R.id.b00);
        cells[0][1] = findViewById(R.id.b01);
        cells[0][2] = findViewById(R.id.b02);
        cells[1][0] = findViewById(R.id.b10);
        cells[1][1] = findViewById(R.id.b11);
        cells[1][2] = findViewById(R.id.b12);
        cells[2][0] = findViewById(R.id.b20);
        cells[2][1] = findViewById(R.id.b21);
        cells[2][2] = findViewById(R.id.b22);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int rr = r, cc = c;
                cells[r][c].setOnClickListener(v -> onCellClicked(rr, cc));
            }
        }
    }

    private void onCellClicked(int r, int c) {
        if (board[r][c] != 0) return; // zajęte
        board[r][c] = (current == 'X') ? 1 : 2;
        cells[r][c].setText(String.valueOf(current));

        int winner = checkWinner();
        if (winner != 0) {
            // 1 = X, 2 = O
            if (winner == 1) {
                scoreX += 1.0;
                Toast.makeText(this, "Wygrywa X! +1 pkt", Toast.LENGTH_SHORT).show();
            } else {
                scoreO += 1.0;
                Toast.makeText(this, "Wygrywa O! +1 pkt", Toast.LENGTH_SHORT).show();
            }
            updateScores();
            lockBoard(true);
            return;
        }

        if (isBoardFull()) {
            // remis
            scoreX += 0.5;
            scoreO += 0.5;
            Toast.makeText(this, "Remis! X +0.5, O +0.5", Toast.LENGTH_SHORT).show();
            updateScores();
            lockBoard(true);
            return;
        }

        // zmiana tury
        current = (current == 'X') ? 'O' : 'X';
        tvTurn.setText("Tura: " + current);
    }

    private void startNewGame(boolean resetTurnToX) {
        // wyczyść planszę
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = 0;
                cells[r][c].setText("");
                cells[r][c].setEnabled(true);
            }
        }
        if (resetTurnToX) current = 'X';
        tvTurn.setText("Tura: " + current);
    }

    private void lockBoard(boolean lock) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c].setEnabled(!lock && board[r][c] == 0);
            }
        }
    }

    private void updateScores() {
        tvScoreX.setText("X: " + scoreX);
        tvScoreO.setText("O: " + scoreO);
    }

    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int v : row) if (v == 0) return false;
        }
        return true;
    }

    private int checkWinner() {
        // wiersze i kolumny
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        // przekątne
        if (board[1][1] != 0) {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return board[1][1];
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[1][1];
        }
        return 0; // brak zwycięzcy
    }

    // (opcjonalnie) zachowaj stan przy rotacji
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putChar("current", current);
        outState.putDouble("scoreX", scoreX);
        outState.putDouble("scoreO", scoreO);
        int[] flat = new int[9];
        int k = 0;
        for (int r=0;r<3;r++) for (int c=0;c<3;c++) flat[k++] = board[r][c];
        outState.putIntArray("board", flat);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        current = state.getChar("current", 'X');
        scoreX = state.getDouble("scoreX", 0.0);
        scoreO = state.getDouble("scoreO", 0.0);
        int[] flat = state.getIntArray("board");
        if (flat != null && flat.length == 9) {
            int k = 0;
            boolean hasWinner = false;
            for (int r=0;r<3;r++) {
                for (int c=0;c<3;c++) {
                    board[r][c] = flat[k++];
                    String t = (board[r][c]==1) ? "X" : (board[r][c]==2) ? "O" : "";
                    cells[r][c].setText(t);
                }
            }
            // jeśli gra była skończona, zablokuj planszę
            if (checkWinner()!=0 || isBoardFull()) hasWinner = true;
            lockBoard(hasWinner);
        }
        tvTurn.setText("Tura: " + current);
        updateScores();
    }
}