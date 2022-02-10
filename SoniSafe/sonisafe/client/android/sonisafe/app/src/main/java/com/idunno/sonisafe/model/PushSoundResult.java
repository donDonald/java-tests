package com.idunno.sonisafe.model;

public class PushSoundResult {

    public PushSoundResult(String text) {
        m_text = text;
    }

    public PushSoundResult(String text, float v, float t) {
        m_text = text;
        m_v = v;
        m_t = t;
    }

    public String getText() {
        return m_text;
    }

    public float getV() {
        return m_v;
    }

    public float getT() {
        return m_t;
    }

    public boolean isLeveled() {
        return m_v > m_t;
    }

    private String m_text;
    private float m_v = 0;
    private float m_t = 0;

}
