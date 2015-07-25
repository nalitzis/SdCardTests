package ado.testsdcard;

import android.app.Activity;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.util.Formatter;


public class MainActivity extends Activity {

    private TableLayout mRoot;

    private static final float MB = 1024.0f * 1024.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = (TableLayout)findViewById(R.id.table_root);
        fillTxt();
    }



    private void fillTxt() {
        addHeader("android.app.Context API");
        addRow("getFilesDir(): ", getFilesDir().toString());
        addRow("getExternalFilesDir(): ", getExternalFilesDir(null).toString());
        addHeader("[4.4+] android.app.Context.getExternalFilesDirs()");
        final File[] files = getExternalFilesDirs(null);
        for(int i = 0; i < files.length; i++) {
            addRow(i + ":  ", files[i].toString());
        }
        addRow("Context.getObbDir(): ", getObbDir().toString());
        addHeader("[4.4+] Context.getObbDirs()");
        File[] obbDirs = getObbDirs();
        for(int i = 0; i < obbDirs.length; i++) {
            addRow(i + ":  ", obbDirs[i].toString());
        }
        addHeader("\nandroid.os.Environment API");
        addRow("getExternalStorageDirectory(): ", Environment.getExternalStorageDirectory().toString());
        addRow("getExternalStorageState(): ", Environment.getExternalStorageState());
        addRow("getDownloadCacheDirectory(): ", Environment.getDownloadCacheDirectory().toString());
        addRow("getDataDirectory(): ", Environment.getDataDirectory().toString());
        addRow("getRootDirectory(): ", Environment.getRootDirectory().toString());
        addRow("isExternalStorageEmulated(): ", "" + Environment.isExternalStorageEmulated());
        addRow("isExternalStorageRemovable(): ", "" + Environment.isExternalStorageRemovable());
        addHeader("[5.0+] isExternalStorageEmulated(File path) - OBBs");
        for(int i = 0; i < obbDirs.length; i++) {
            addRow(i + ":  ", "" + Environment.isExternalStorageEmulated(obbDirs[i]));
        }
        addHeader("[5.0+] isExternalStorageRemovable(File path) - OBBs");
        for(int i = 0; i < obbDirs.length; i++) {
            addRow(i + ":  ", "" + Environment.isExternalStorageRemovable(obbDirs[i]));
        }
        addHeader("[5.0+] isExternalStorageEmulated(File path) - External paths");
        for(int i = 0; i < files.length; i++) {
            addRow(i + ":  ", "" + Environment.isExternalStorageEmulated(files[i]));
        }
        addHeader("[5.0+] isExternalStorageRemovable(File path) - External paths");
        for(int i = 0; i < files.length; i++) {
            addRow(i + ":  ", "" + Environment.isExternalStorageRemovable(files[i]));
        }

        addHeader("\nandroid.os.StatFs API - available space");
        addRow("[4.3] size of getFilesDir(): ", "" + printSize(getFilesDir()) + " MB");
        addRow("[4.3] size of getExternalFilesDir(null): ", "" + printSize(getExternalFilesDir(null)) + " MB");
        addRow("[4.3] size of Environment.getDataDirectory(): ", "" + printSize(Environment.getDataDirectory()) + " MB");
        addRow("[4.3] size of Environment.getExternalStorageDirectory(): ", "" + printSize(Environment.getExternalStorageDirectory()) + " MB");

    }

    private long getSizeBytes(final File path) {
        final StatFs stat = new StatFs(path.getPath());
        long availableBytes = stat.getAvailableBytes();
        return availableBytes;
    }

    private float toMB(final long bytes) {
        return ((float)bytes)/MB;
    }

    private String toMBString(final long bytes) {
        final float mb = toMB(bytes);
        final Formatter formatter = new Formatter();
        return formatter.format("%.2f", mb).toString();
    }

    private String printSize(final File path) {
        return toMBString(getSizeBytes(path));
    }

    private void addHeader(String title) {
        final TextView env = new TextView(this);
        env.setText(title);
        mRoot.addView(env);
    }

    private void addRow(String key, String value) {
        final TableRow tr = new TableRow(this);
        final TextView c2 = new TextView(this);
        c2.setText(key);
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
