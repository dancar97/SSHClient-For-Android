/*
 * Copyright (c) 2017 Benjamin Raison
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package net.baumink.android.sshclient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class ActivityMain extends AppCompatActivity {

    private static final String TAG = ActivityMain.class.getSimpleName();
    Session session;
    Output output;
    Channel channel;
    InputStream in = new PipedInputStream();
    PipedOutputStream pin;
    Button gcalc;
    Button buttonkod;
    Button buttonRetro;
    Button kodiUp;
    Button kodiDown;
    Button kodiLeft;
    Button kodiRight;
    Button kodiOk;
    Button kodiBack;
    Button shutDown;
    Button killAll;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.txtOutput);
        EditText txtInput = findViewById(R.id.txtInput);
        Intent intent = getIntent();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    getString(R.string.format_user, intent.getStringExtra("username"), intent.getStringExtra("server")));
        } else {
            Log.w(TAG, "SupportActionBar is null!");
        }
        ImageButton btnSend = findViewById(R.id.btnSend);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        gcalc = findViewById(R.id.buttongalculator);

         buttonkod = findViewById(R.id.buttonKodi);
         buttonRetro = findViewById(R.id.buttonRetropi);
         kodiUp = findViewById(R.id.kodiup);
         kodiDown = findViewById(R.id.kodiDown);
         kodiLeft = findViewById(R.id.kodiLeft);
         kodiRight = findViewById(R.id.kodiRight);
         kodiOk = findViewById(R.id.kodiOk);
         kodiBack = findViewById(R.id.kodiBack);
        shutDown = findViewById(R.id.shutdownButton);
        killAll = findViewById(R.id.KillAll);



        killAll.setOnClickListener(v -> {
            try {
                pin.write("\nkillall screen".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        shutDown.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nshutdown".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        kodiUp.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Up\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        kodiDown.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Down\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        kodiLeft.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Left\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        kodiRight.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Right\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        kodiOk.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Select\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        kodiBack.setOnClickListener(v -> {
            try {
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkodi-send --action=\"Back\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });






        buttonRetro.setOnClickListener(v -> {
            try {

                pin.write("\nkodi-send --action=\"quit\"".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nkillall screen".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nnohup screen -d -m emulationstation".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonkod.setOnClickListener(v -> {
            try {
                pin.write("\nkillall screen".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nexport DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nnohup screen -d -m kodi".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gcalc.setOnClickListener(v -> {
            try {
                pin.write("export DISPLAY=:0\n".trim().getBytes());
                pin.write("\n".getBytes());
                pin.write("\nnohup galculator".trim().getBytes());
                pin.write("\n".getBytes());
                pin.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        btnSend.setOnClickListener(v -> {
            Log.d(TAG, "Clicked!: " + txtInput.getText().toString());
            if (!txtInput.getText().toString().trim().equals("")) {
                try {
                    pin.write(txtInput.getText().toString().trim().getBytes());
                    pin.write("\n".getBytes());
                    pin.flush();
                    txtInput.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        startConnection(intent, textView);
    }

    private void startConnection(Intent intent, TextView textView) {
        new Thread(() -> {
            JSch jsch = new JSch();
            output = new Output(textView, this);
            try {
                pin = new PipedOutputStream((PipedInputStream) in);
                session = jsch.getSession(intent.getStringExtra("username"), intent.getStringExtra("server"), 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword(intent.getStringExtra("password"));
                session.setOutputStream(output);
                session.connect();
                ChannelShell channel = (ChannelShell) session.openChannel("shell");
                channel.setInputStream(in);
                channel.setOutputStream(output);
                channel.connect();
                channel.start();
                if (session.isConnected()) {
                    Log.d(TAG, "Connected");
                }
            } catch (JSchException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        channel.disconnect();
        session.disconnect();
    }
}

class Output extends OutputStream {
    private TextView textView;
    private AppCompatActivity activity;
    private StringBuffer buffer = new StringBuffer(100);

    Output(TextView textView, AppCompatActivity activity) {
        this.textView = textView;
        this.activity = activity;
    }

    @Override
    public void write(int i) throws IOException {
        buffer.append((char) i);
        if (buffer.length() > 95 || (char) i == '\n') {
            String s = buffer.toString();
            buffer = new StringBuffer(100);
            activity.runOnUiThread(() -> {
                textView.append(s);
            });
        }
    }
}