package com.example.training_hagita;

public class KukuMultiplication {

    // 定数の宣言
    private static final int TATE = 9;
    private static final int YOKO = 9;

    public static void main(String[] args) {

        // 二次元配列の宣言
        int[][] kuku = new int[TATE][YOKO];

        // TATEのループ処理
        for (int i = 0; i < TATE; i++) {

            // YOKOのループ処理
            for (int j = 0; j < YOKO; j++) {

                // 九九の結果を変数に保存
                // 配列のズレを修正するために+1する
                kuku[i][j] = (i + 1) * (j + 1);
            }
        }

        // TATEのループ処理
        for (int i = 0; i < TATE; i++) {

            // YOKOのループ処理
            for (int j = 0; j < YOKO; j++) {

                // 表の見た目を整理
                if (kuku[i][j] < 10 ) {

                    // 九九の結果を表示する
                    System.out.print(" " + kuku[i][j] + " ");
                } else {
                    System.out.print("" + kuku[i][j] + " ");
                }
            }

            // 結果を出力
            System.out.println();
        }
    }
}