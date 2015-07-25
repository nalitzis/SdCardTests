package ado.testsdcard;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends Activity {

    private TableLayout mRoot;
    private TextView mIntTxt, mExtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = (TableLayout)findViewById(R.id.table_root);
        mIntTxt = (TextView)findViewById(R.id.textViewInt);
        mExtTxt = (TextView)findViewById(R.id.textViewExt);

        fillTxt();
    }

    private void fillTxt() {
        final TextView extTxt = new TextView(this);
        extTxt.setText("external KK api");
        mRoot.addView(extTxt);

        mIntTxt.setText("" + getFilesDir());
        //not null if you want the path used for media like pics, videos,...
        mExtTxt.setText("" + getExternalFilesDir(null));
        final File[] files = getExternalFilesDirs(null);
        for(int i = 0; i < files.length; i++) {
            final TableRow tr = new TableRow(this);
            final TextView txtKey = new TextView(this);
            txtKey.setText("" + i + ":");
            final TextView txtValue = new TextView(this);
            txtValue.setText(files[i].toString());
            tr.addView(txtKey);
            tr.addView(txtValue);
            mRoot.addView(tr);
        }
        /*final TextView cacheTxt = new TextView(this);
        cacheTxt.setText("cache");
        mRoot.addView(cacheTxt);
        final TableRow tr = new TableRow(this);
        final TextView c2 = new TextView(this);
        c2.setText("Cache   ");
        final TextView c2val = new TextView(this);
        c2val.setText("" + getExternalCacheDir());
        tr.addView(c2);
        tr.addView(c2val);
        mRoot.addView(tr);*/

        addHeader("Obb Honeycomb");
        addRow("obb (api 3.0)   ", getObbDir().toString());
        addHeader("Obb KK");
        File[] obbDirs = getObbDirs();
        for(int i = 0; i < obbDirs.length; i++) {
            addRow(i + ":  ", obbDirs[i].toString());
        }
        addHeader("Environment API");
        addRow("Env   ", Environment.getExternalStorageDirectory().toString());
        addRow("Data   ", Environment.getDataDirectory().toString());
        addRow("Root   ", Environment.getRootDirectory().toString());

        //not null, type required
        //addRow("Ext public", Environment.getExternalStoragePublicDirectory(null).toString());

        addHeader("Characteristics of external");
        addRow("emulated?   ", "" + Environment.isExternalStorageEmulated());
        addRow("removable?   ", "" + Environment.isExternalStorageRemovable());

    }

    private void addHeader(String title) {
        final TextView env = new TextView(this);
        env.setText(title);
        mRoot.addView(env);
    }

    private void addRow(String key, String value) {
        final TableRow tr = new TableRow(this);
        final TextView c2 = new TextView(this);
        c2.setText(key + "   ");
        final TextView c2val = new TextView(this);
        c2val.setText("" + value);
        tr.addView(c2);
        tr.addView(c2val);
        mRoot.addView(tr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
