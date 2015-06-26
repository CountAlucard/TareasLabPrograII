package lab.unitec.android.tarea3;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class fragmento2 extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_fragmento2,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        text= (TextView) getActivity().findViewById(R.id.textView);
    }

    public void changeData(int i){
        Resources res =getResources();
        String[] descripciones = res.getStringArray(R.array.descripcion);
        text.setText(descripciones[i]);
    }

}
