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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    private static final String TAG = ActivityLogin.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txtServer = findViewById(R.id.txtServer);
        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            boolean ok = true;
            if (txtServer.getText().toString().trim().equals("")) {
                txtServer.setError(getString(R.string.error_required));
                ok = false;
            } else {
                txtServer.setError(null);
            }
            if (txtUsername.getText().toString().trim().equals("")) {
                txtUsername.setError(getString(R.string.error_required));
                ok = false;
            } else {
                txtUsername.setError(null);
            }
            if (txtPassword.getText().toString().trim().equals("")) {
                txtPassword.setError(getString(R.string.error_required));
                ok = false;
            } else {
                txtPassword.setError(null);
            }
            if (ok) {
                Intent intent = new Intent(this, ActivityMain.class);
                intent.putExtra("server", txtServer.getText().toString().trim());
                intent.putExtra("username", txtUsername.getText().toString().trim());
                intent.putExtra("password", txtPassword.getText().toString());
                startActivity(intent);
            }
        });
    }
}
