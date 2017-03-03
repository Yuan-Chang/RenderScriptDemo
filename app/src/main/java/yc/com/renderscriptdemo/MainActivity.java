package yc.com.renderscriptdemo;

import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////////////
        //1. add render script => myscripts.rs
        //2. example code bellow
        //////////////////////////////////////////////

        RenderScript mRS = RenderScript.create(this);
        Allocation DataOut;
        ScriptC_myscript mScript;

        //create array of input
        int RNGE = 10;
        ScriptField_MyDataIn mIn = new ScriptField_MyDataIn(mRS, RNGE);

        for (int i = 0; i < RNGE; ++i) {
            mIn.set_a(i, i, false);
            mIn.set_b(i, i*2, false);
        }
        //add data in
        mIn.copyAll();

        //allocate 10 output array
        DataOut = Allocation.createSized(mRS, Element.I32(mRS), RNGE);

        mScript = new ScriptC_myscript(mRS);
        //run script
        mScript.forEach_root(mIn.getAllocation(), DataOut);

        int[] Out = new int[RNGE];
        DataOut.copyTo(Out);

        for (int i = 0; i < RNGE; ++i) {
            Log.i("MyParallel", "(a,b,sum)=("+mIn.get_a(i)+"+"+mIn.get_b(i)+"="+Out[i]+")");
        }
    }
}
