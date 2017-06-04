package eprit.tn.cowbot.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.List;

import eprit.tn.cowbot.Entity.Controlle;
import eprit.tn.cowbot.Entity.GoTo;
import eprit.tn.cowbot.Entity.Status;
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
    private Boolean active = false;
    private String speedValue="1";
    private Switch statusSwitch;
    private Button Goto;
    public CamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeUtils();
        setHasOptionsMenu(true);
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
        Goto=(Button)view.findViewById(R.id.Goto);
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
        Uri vidUri = Uri.parse("http://192.168.1.8:9090/stream/video.mjpeg");
        vidView.setVideoURI(vidUri);
        vidView.start();
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!active) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "down",speedValue))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;

                        case MotionEvent.ACTION_UP:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "downoff"))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;


                    }
                } else
                    Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!active) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "up",speedValue))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;

                        case MotionEvent.ACTION_UP:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "upoff"))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;
                    }
                } else
                    Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!active) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "left",speedValue))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;

                        case MotionEvent.ACTION_UP:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "leftoff"))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;


                    }
                } else
                    Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!active) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "right",speedValue))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;

                        case MotionEvent.ACTION_UP:

                            mCompositeDisposable.add(controlleService.controlle(new Controlle(idUser, "rightoff"))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe());
                            return true;


                    }
                } else
                    Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        speed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (!active) {
                    switch (checkedId) {
                        case R.id.lowSpeed:
                            speedValue="0.5";
                            break;
                        case R.id.NormalSpeed:
                            speedValue="1";
                            break;
                        case R.id.higthSpeed:
                            speedValue="2";
                            break;
                    }
                } else
                    Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();
            }
        });
        Goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String X=AxeX.getSelectedItem().toString();
                String Y=AxeY.getSelectedItem().toString();
                Log.d("X",X);
                Log.d("Y",Y);
                mCompositeDisposable.add(controlleService.GoTo(new GoTo(idUser,X,Y,speedValue))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe());
            }
        });
    }

    public void InitializeUtils() {
        AxeXAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.AxeXValue, android.R.layout.simple_spinner_item);
        AxeYAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.AxeYValue, android.R.layout.simple_spinner_item);
        mCompositeDisposable = new CompositeDisposable();
        controlleService = ServiceFactory.createRetrofitService(ControlleService.class, URLS.EndPoint);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        idUser = sharedPreferences.getInt("id", 0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.controlle, menu);
        final MenuItem toggleservice = menu.findItem(R.id.status);

         statusSwitch = (Switch) MenuItemCompat.getActionView(toggleservice);
        mCompositeDisposable.add(controlleService.GetStatusControlle(idUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleStatusResponse, this::handleStatusError));
        statusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    active = false;
                    mCompositeDisposable.add(controlleService.StatusControlle(idUser, "2")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                } else {
                    active = true;
                    mCompositeDisposable.add(controlleService.StatusControlle(idUser, "3")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribe());
                }
            }
        });
    }

    private void handleStatusResponse(List<Status> status) {

        if (status.get(0).getState().equals("0")) {
            mCompositeDisposable.add(controlleService.StatusControlle(idUser, "2")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe());
            statusSwitch.setChecked(true);
            active = false;
        }
        else{
            active = true;
            Toast.makeText(getActivity(), "Your cowbot is planting", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleStatusError(Throwable throwable) {
        Log.d("throwble", throwable.getLocalizedMessage());
    }




}
