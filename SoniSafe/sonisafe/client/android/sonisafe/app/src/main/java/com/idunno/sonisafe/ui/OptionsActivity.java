package com.idunno.sonisafe.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.idunno.sonisafe.R;
import com.idunno.sonisafe.model.Settings;

public class OptionsActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        m_settings = new Settings(this);
        m_timeBar = (SeekBar)findViewById(R.id.length_bar);
        m_timeLabel = (TextView)findViewById(R.id.time_label);
        m_timeBar.setOnSeekBarChangeListener(this);

        int captureTime = m_settings.getCaptureTime();
        m_timeBar.setProgress( captureTime );
        m_timeLabel.setText( getString(R.string.settings_length ) + " " + captureTime + "(msec)" );

        m_adapter = new ArrayAdapter<Settings.SamplingFrequency>(
                this,
                android.R.layout.simple_spinner_item,
                m_settings.getSupportedFrequencies() );
        m_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_frequencySpinner = (Spinner)findViewById(R.id.sampling_frequency);
        m_frequencySpinner.setAdapter(m_adapter);
        m_frequencySpinner.setSelection( m_adapter.getPosition( m_settings.getSampleFrequency() ) );

        m_frequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                m_settings.setSampleFrequency(m_adapter.getItem(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onProgressChanged(
            SeekBar seekBar, int progress, boolean fromUser) {
        if(progress < 100) {
            progress = 100;
        }
        m_timeBar.setProgress(progress);
        m_settings.setCaptureTime(progress);
        m_timeLabel.setText( getString(R.string.settings_length ) + " " + progress + "(msec)" );
    }
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    Settings m_settings;
    TextView m_timeLabel;
    SeekBar  m_timeBar;
    Spinner  m_frequencySpinner;
    ArrayAdapter<Settings.SamplingFrequency> m_adapter;

}
