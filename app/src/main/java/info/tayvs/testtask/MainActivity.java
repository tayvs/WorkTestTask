package info.tayvs.testtask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    ListView userList;
    EditText maxCacheET;
    Button maxCacheButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize View Components
        userList = (ListView) findViewById(R.id.UserList);
        maxCacheButton = (Button) findViewById(R.id.MaxCacheSizeButton);
        maxCacheET = (EditText) findViewById(R.id.MaxCacheSizeET);

        //Set Button OnClickListener (I know That it obviously but I must study to write comments)
        maxCacheButton.setOnClickListener(this);

        //filling userList (View)
        LazyViewWhichEverythingKnow adapter = new LazyViewWhichEverythingKnow(this);
        userList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Object o = maxCacheET.getText();
        if (o != null && o.toString().length() != 0){
            int mCache = Integer.valueOf(maxCacheET.getText().toString());

            LazyViewWhichEverythingKnow adapter = ((LazyViewWhichEverythingKnow)userList.getAdapter());
            adapter.setMaxCacheSize(mCache);

            Toast.makeText(this, "New max cache count is " + mCache, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You enter invalid value", Toast.LENGTH_SHORT).show();
        }
    }
}
