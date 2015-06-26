package lab.unitec.android.tarea3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class Fragmento1 extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    Communicador comunicador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_fragmento1,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comunicador = (Communicador) getActivity();
        list= (ListView)getActivity().findViewById(R.id.listView);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.prestamos,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        comunicador.responder(position);
    }
}
