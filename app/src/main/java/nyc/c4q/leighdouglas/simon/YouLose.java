package nyc.c4q.leighdouglas.simon;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by leighdouglas on 9/28/16.
 */

public class YouLose extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_loses);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.player_loses);
    }

    public void onClick(View view){
        Intent j = new Intent(getApplicationContext(), SimonGame.class);
        startActivity(j);
    }
}
