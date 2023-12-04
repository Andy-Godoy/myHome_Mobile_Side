package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassword extends AppCompatActivity  {

    private EditText editTextEmail;
    private Button enviarCodigoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }


        editTextEmail = findViewById(R.id.editTextEmail);
        enviarCodigoButton = findViewById(R.id.validarButton);

        enviarCodigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmailInBackground();
            }
        });
    }

    private void sendEmailInBackground() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //acá configuramos los datos del SMTP para el envío de correo para enviar el código de verificación
                final String username = "germanhectorfalasco@gmail.com";
                final String password = "LE6tD8MYyAWbvmOa";

                String recipientEmail = editTextEmail.getText().toString().trim();

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "false");
                props.put("mail.smtp.host", "smtp-relay.sendinblue.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                    message.setSubject("Código de verificación");
                    String verificationCode = generarCodigoVerificacion();
                    message.setText("Tu código de verificación para restablecer la contraseña en MyHome es: " + verificationCode);

                    Transport.send(message);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForgotPassword.this, "Correo de verificación enviado con éxito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassword.this, ForgotPasswordCode.class);
                            intent.putExtra("codigo", verificationCode); // verificationCode es el código generado
                            intent.putExtra("email", editTextEmail.getText().toString());
                            startActivity(intent);

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForgotPassword.this, "Error al enviar el correo de verificación", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        thread.start();
    }

    private String generarCodigoVerificacion() {
        // Genera un código de verificación aleatorio de 6 dígitos que enviamos por mail
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }

}
