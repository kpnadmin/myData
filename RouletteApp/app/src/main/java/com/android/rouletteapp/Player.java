package com.android.rouletteapp;

/**
 * Created by 김태훈 on 2017-06-29.
 */

public class Player {
    private int player_num;
    private String p_edit_text;
    private String g_Id; // 구글 id


    public Player() {
    }


    public Player(String p_edit_text, String g_Id) {
        this.p_edit_text = p_edit_text;
        this.g_Id = g_Id;
    }

    public int getPlayer_num() {
        return player_num;
    }

    public void setPlayer_num(int player_num) {
        this.player_num = player_num;
    }

    public String getP_edit_text() {
        return p_edit_text;
    }

    public void setP_edit_text(String p_edit_text) {
        this.p_edit_text = p_edit_text;
    }

    public String getG_Id() {
        return g_Id;
    }

    public void setG_Id(String g_Id) {
        this.g_Id = g_Id;
    }

}
