package com.CNAM.GeoRouting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

/**
 * Created by fwoelffel on 27/04/14.
 */
public class SettingsActivity extends Activity implements Preferences{

    private SharedPreferences m_sharedPrefs;
    private Switch m_switch_AutoMode, m_switch_Notifications, m_switch_GPS;
    private Button m_button_ChooseCalendar, m_button_LogIn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        m_sharedPrefs = getSharedPreferences(Preferences.APPNAME, 0);
        m_switch_AutoMode = ((Switch)findViewById(R.id.switch_AutoMode));
        m_switch_Notifications= ((Switch)findViewById(R.id.switch_Notifications));;
        m_switch_GPS = ((Switch)findViewById(R.id.switch_GPS));;
        m_button_ChooseCalendar = ((Button)findViewById(R.id.button_ChooseCalendar));
        m_button_LogIn = ((Button)findViewById(R.id.button_LogIn));

        // TODO Dispatch Listener's source
        m_button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogInActivity();
            }
        });
        m_button_ChooseCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Launch Calendar Chooser
            }
        });
    }

    private void startLogInActivity() {
        startActivity(new Intent(SettingsActivity.this, LogInActivity.class));
    }

    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    public void onPause() {
        savePreferences();
        super.onPause();
    }

    @Override
    public void loadPreferences() {
        m_switch_AutoMode.setChecked(m_sharedPrefs.getBoolean(Preferences.AUTO, false));
        m_switch_Notifications.setChecked(m_sharedPrefs.getBoolean(Preferences.NOTIFICATIONS, false));
        m_switch_GPS.setChecked(m_sharedPrefs.getBoolean(Preferences.GPS, false));
    }

    @Override
    public void savePreferences() {
        SharedPreferences.Editor editor = m_sharedPrefs.edit();
        editor.putBoolean(Preferences.AUTO, m_switch_AutoMode.isChecked());
        editor.putBoolean(Preferences.GPS, m_switch_GPS.isChecked());
        editor.putBoolean(Preferences.NOTIFICATIONS, m_switch_Notifications.isChecked());
        editor.commit();
    }
}