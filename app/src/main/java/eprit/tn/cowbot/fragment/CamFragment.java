package eprit.tn.cowbot.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import eprit.tn.cowbot.Entity.Controlle;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.ControlleService;
import eprit.tn.cowbot.Utils.URLS;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CamFragment extends Fragment {

    private VideoView vidView;
    private ImageButton up, down, left, right, zoommoins, zoomplus;
    private SegmentedGroup speed;
    private Spinner AxeX, AxeY;
    private CompositeDisposable mCompositeDisposable;
    private ControlleService controlleService;
    private ArrayAdapter<CharSequence> AxeXAdapter, AxeYAdapter;
    private Integer idUser;
    private SharedPreferences sharedPreferences;

    public CamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeUtils();
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.menu_etat);
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

        AxeX = (Spinner) view.findViewById(R.id.X);
        AxeY = (Spinner) view.findViewById(R.id.Y);

        Glide.with(getActivity()).load(R.drawable.up).fitCenter().into(up);
        Glide.with(getActivity()).load(R.drawable.down).fitCenter().into(down);
        Glide.with(getActivity()).load(R.drawable.left).fitCenter().into(left);
        Glide.with(getActivity()).load(R.drawable.right).fitCenter().into(right);
        Glide.with(getActivity()).load(R.drawable.moins).fitCenter().into(zoommoins);
        Glide.with(getActivity()).load(R.drawable.plus).fitCenter().into(zoomplus);

        return view;
    }

    public void ViewCreated() {

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "down"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;

                    case MotionEvent.ACTION_UP:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "downoff"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;


                }
                return false;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "up"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;

                    case MotionEvent.ACTION_UP:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "upoff"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;


                }
                return false;
            }
        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "left"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;

                    case MotionEvent.ACTION_UP:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "leftoff"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;


                }
                return false;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "right"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;

                    case MotionEvent.ACTION_UP:

                        mCompositeDisposable.add(controlleService.controlle(new Controlle(10, "rightoff"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                        return true;


                }
                return false;
            }
        });
        speed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.lowSpeed:
                        Toast.makeText(getActivity(), "lowSpeed", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.NormalSpeed:
                        Toast.makeText(getActivity(), "NormalSpeed", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.higthSpeed:
                        Toast.makeText(getActivity(), "higthSpeed", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


    public void InitializeUtils() {
        AxeXAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.AxeXValue, android.R.layout.simple_spinner_item);
        AxeYAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.AxeYValue, android.R.layout.simple_spinner_item);
        mCompositeDisposable = new CompositeDisposable();
        controlleService = ServiceFactory.createRetrofitService(ControlleService.class, URLS.EndPoint);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        idUser =sharedPreferences.getInt("id", 0);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.controlle, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.status:


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
