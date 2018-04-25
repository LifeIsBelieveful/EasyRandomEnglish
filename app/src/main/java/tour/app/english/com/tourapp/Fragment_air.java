package tour.app.english.com.tourapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_air extends Fragment {

    Button question1;
    LinearLayout layout;
    @Override
    public View onCreateView(
       LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fragment_air, null);

        question1=view.findViewById(R.id.Q1);
        layout=view.findViewById(R.id.layout_fragment_question);

        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent1);
                //버튼에 글자넣기

                ((StartActivity)getActivity()).questionlayout.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
