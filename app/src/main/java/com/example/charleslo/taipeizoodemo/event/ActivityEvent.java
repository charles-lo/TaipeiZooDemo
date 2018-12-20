package com.example.charleslo.taipeizoodemo.event;

public class ActivityEvent {

    private int m_Msg;
    private int m_Idx;

    public ActivityEvent(int msg) {
        m_Msg = msg;
    }

    public ActivityEvent(int msg, int idx) {
        m_Msg = msg;
        m_Idx = idx;
    }

    public int getMsg() {
        return m_Msg;
    }

    public int getIdx() {
        return m_Idx;
    }
}
