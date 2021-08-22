package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    private TaskAdapter adapter;
    private List<Task> taskList;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        TextView user= findViewById(R.id.showuser);
        user.setText(preferences.getString("username","please add user name from setting page"));
        // to show user teamName
        TextView team = findViewById(R.id.showteam);
        team.setText(( preferences.getString("teamName","please add team name from setting page")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // amplify

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("plugin", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("plugin", "Could not initialize Amplify", e);
        }

        // create teams and save it at dynamoDB ---- generate one time

//        Team team = Team.builder().name("TeamA").build();
//        Team team = Team.builder().name("TeamB").build();
//        Team team = Team.builder().name("TeamC").build();
//
//        Amplify.API.mutate(ModelMutation.create(team),
//        success -> Log.i("teamSave", "Saved Team to api : " + success.getData()),
//        error -> Log.e("teamSave", "Could not save Team to API/dynamodb", error));



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        ImageView profile = (ImageView) findViewById(R.id.profile);
        Button setting = (Button) findViewById(R.id.setting);
//        Button detail = (Button) findViewById(R.id.detail);



        profile.setBackgroundResource(R.drawable.profile);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this,AddTask.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTasks.class);
                startActivity(intent);

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });

//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,TaskDetail.class);
//                startActivity(intent);
//
//            }
//        });

//        RecyclerView recyclerView = findViewById(R.id.list);
//        taskList= new ArrayList<>();
//        taskList.add( new Task("work","meet with the team","new"));
//        taskList.add( new Task("house","remove garbage","assigned"));
//        taskList.add( new Task("work","create weekly plan ","in progress"));
//        taskList.add( new Task("house","wash desh","complete"));
//
//        adapter= new TaskAdapter(taskList);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
//                getApplicationContext()
//
//        );
//                this,
//                LinearLayoutManager.VERTICAL,
//                false
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

//    public void toAddTask( View view){
//        Intent intent = new Intent (this,AddTask.class);
//        startActivity(intent);
//
//    }
//     public void toAllTasks( View view){
//            Intent intent = new Intent (this,AllTasks.class);
//            startActivity(intent);
//
//        }


}