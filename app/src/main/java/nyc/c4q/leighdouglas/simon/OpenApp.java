package nyc.c4q.leighdouglas.simon;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by leighdouglas on 10/1/16.
 */

public class OpenApp extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_app);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.open_app);
    }
    public void playClick(View view){
        Intent j = new Intent(getApplicationContext(), SimonGame.class);
        startActivity(j);
    }

    public void directionsClick(View view){
        Toast.makeText(getApplicationContext(), "Follow the button pattern, and then repeat!", Toast.LENGTH_SHORT).show();
    }
}

