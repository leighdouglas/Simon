package nyc.c4q.leighdouglas.simon;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.os.Handler;


public class SimonGame extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    private Button btnTopRight;
    private Button btnTopLeft;
    private Button btnBottomRight;
    private Button btnBottomLeft;
    List<Integer> userInput = new ArrayList<>();
    List<Integer> cpuInput = new ArrayList<>();
    int numHighScore;
    private TextView roundCounter;
    String TAG = "TAG";
    public int i;
    Random random = new Random();
    public int randomNumber = random.nextInt(4)+1;
    public int tempInt;
    private TextView highScore;

    private Shape roundCounterShape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("my_HS", Activity.MODE_PRIVATE);
        editor = sp.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simon_game);
        cpuInput.add(randomNumber);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.simon_game);
        Handler handler = new Handler();
        btnTopRight = (Button) findViewById(R.id.topRight);
        btnTopLeft = (Button) findViewById(R.id.topLeft);
        btnBottomRight = (Button) findViewById(R.id.bottomRight);
        btnBottomLeft = (Button) findViewById(R.id.bottomLeft);
        roundCounter = (TextView) findViewById(R.id.roundCounter);
        highScore = (TextView) findViewById(R.id.highScore);
        Log.d("cpu input", cpuInput.toString());
        roundCounter.setText(Integer.toString(cpuInput.size()));
        delay();
    }

    public void buttonOnClick(View view){


        Button button = (Button) view;
        ObjectAnimator anim = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f);
        anim.setDuration(200);
        anim.start();

        String numberPressed = getResources().getResourceEntryName(view.getId());
        tempInt = idToButtonNum(numberPressed);

        userInput.add(tempInt);
        Log.d("user", userInput.toString());

        if(userInput.get(i) != cpuInput.get(i)) {
            Intent j = new Intent(getApplicationContext(), YouLose.class);
            startActivity(j);
            Log.d(TAG, "You Lose!");
        } else {
                i++;
            if (userInput.size() == cpuInput.size()) {
                    roundWon();
                Log.d("cpu", cpuInput.toString());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pref_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Light_theme:
                btnTopLeft.setBackgroundColor(getResources().getColor(R.color.lightthemeone));
                btnTopRight.setBackgroundColor(getResources().getColor(R.color.lightthemetwo));
                btnBottomLeft.setBackgroundColor(getResources().getColor(R.color.lightthemethree));
                btnBottomRight.setBackgroundColor(getResources().getColor(R.color.lightthemefour));

                break;
            case R.id.Rave_theme:
                btnTopLeft.setBackgroundColor(getResources().getColor(R.color.darkthemeone));
                btnTopRight.setBackgroundColor(getResources().getColor(R.color.darkthemetwo));
                btnBottomLeft.setBackgroundColor(getResources().getColor(R.color.darkthemethree));
                btnBottomRight.setBackgroundColor(getResources().getColor(R.color.darkthemefour));

                break;
            case R.id.original:
                btnTopLeft.setBackgroundColor(getResources().getColor(R.color.buttonTopLeft));
                btnTopRight.setBackgroundColor(getResources().getColor(R.color.buttonTopRight));
                btnBottomLeft.setBackgroundColor(getResources().getColor(R.color.buttonBottomLeft));
                btnBottomRight.setBackgroundColor(getResources().getColor(R.color.buttonBottomRight));

                break;
        }
        return super.onOptionsItemSelected(item);

    }


    public void roundWon(){
        userInput.clear();
        i = 0;
        cpuInput.add(random.nextInt(4)+1);
//        int num = Integer.valueOf(roundCounter.getText().toString());
//        num++;
//        roundCounter.setText(Integer.toString(num));
        roundCounter.setText(Integer.toString(cpuInput.size()));

        if(cpuInput.size() > numHighScore){
            numHighScore = cpuInput.size();
            editor.putInt("my_HS", numHighScore);
            editor.apply();
        }

        highScore.setText(Integer.toString(sp.getInt("my_HS", numHighScore-1)));

        Handler handler = new Handler();

        delay();
    }

    public void delay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                @Override
                public void run () {
                    nextRound();
                }
            }
            ,700);
    }


    public void nextRound(){

        List<Animator> list = new ArrayList<Animator>();

        AnimatorSet set1 = new AnimatorSet();

        for(int j = 0; j < cpuInput.size(); j++){

            switch(cpuInput.get(j)){

                case 1:
                    Animator anim1 = ObjectAnimator.ofFloat(btnTopLeft, "alpha", 0f, 1f);
                    anim1.setDuration(300);
                    list.add(j, anim1);
                    break;
                case 2:
                    Animator anim2 = ObjectAnimator.ofFloat(btnTopRight, "alpha", 0f, 1f);
                    anim2.setDuration(300);
                    list.add(j, anim2);
                    break;
                case 3:
                    Animator anim3 = ObjectAnimator.ofFloat(btnBottomLeft, "alpha", 0f, 1f);
                    anim3.setDuration(300);
                    list.add(j, anim3);
                    break;

                case 4:
                    Animator anim4 = ObjectAnimator.ofFloat(btnBottomRight, "alpha", 0f, 1f);
                    anim4.setDuration(300);
                    list.add(j, anim4);
                    break;
                default:
                    break;
            }
        }
        set1.playSequentially(list);
        set1.setInterpolator(new LinearInterpolator());
        set1.start();
    }

    public int idToButtonNum(String aString){
       switch(aString){
           case "topLeft":
               return 1;

           case "topRight":
               return 2;

           case "bottomLeft":
               return 3;

           case "bottomRight":
               return 4;

           default:
               return 99999;
       }
    }
}