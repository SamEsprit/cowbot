package eprit.tn.cowbot.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import eprit.tn.cowbot.R;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.reactivex.disposables.CompositeDisposable;

public class CamFragment extends Fragment {

    private VideoView vidView;
    private ImageButton up, down, left, right, zoommoins, zoomplus;
    private SegmentedGroup speed;
    private Spinner AxeX,AxeY;
    private CompositeDisposable mCompositeDisposable;
    private ArrayAdapter<CharSequence> AxeXAdapter, AxeYAdapter;



    public CamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeUtils();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return InitializeView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewCreated();
    }

    public View InitializeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        vidView = (VideoView) view.findViewById(R.id.Streaming);
        up = (ImageButton) view.findViewById(R.id.up);
        down = (ImageButton) view.findViewById(R.id.down);
        right = (ImageButton) view.findViewById(R.id.right);
        left = (ImageButton) view.findViewById(R.id.left);
        zoommoins = (ImageButton) view.findViewById(R.id.zoommoins);
        zoomplus = (ImageButton) view.findViewById(R.id.zoomplus);
        speed = (SegmentedGroup) view.findViewById(R.id.speed);

        AxeX =(Spinner) view.findViewById(R.id.X);
        AxeY =(Spinner) view.findViewById(R.id.Y);

        Glide.with(getActivity()).load(R.drawable.up).fitCenter().into(up);
        Glide.with(getActivity()).load(R.drawable.down).fitCenter().into(down);
        Glide.with(getActivity()).load(R.drawable.left).fitCenter().into(left);
        Glide.with(getActivity()).load(R.drawable.right).fitCenter().into(right);
        Glide.with(getActivity()).load(R.drawable.moins).fitCenter().into(zoommoins);
        Glide.with(getActivity()).load(R.drawable.plus).fitCenter().into(zoomplus);

        return view;
    }

    public void ViewCreated() {

        speed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.lowSpeed:
                        Toast.makeText(getActivity(),"lowSpeed",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.NormalSpeed:
                        Toast.makeText(getActivity(),"NormalSpeed",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.higthSpeed:
                        Toast.makeText(getActivity(),"higthSpeed",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


    public void InitializeUtils(){
        AxeXAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.AxeXValue,android.R.layout.simple_spinner_item);
        AxeYAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.AxeYValue,android.R.layout.simple_spinner_item);

    }


}
