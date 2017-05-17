package eprit.tn.cowbot.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Entity.Plant.PlantToPlantOutput;
import eprit.tn.cowbot.Entity.PosLib;
import eprit.tn.cowbot.Entity.Seeds.SeedsInput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantService;
import eprit.tn.cowbot.Service.SeedService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class PlantMapFragment extends Fragment {
    private LinearLayout p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18;
    private LinearLayout exist_plant;
    private CompositeDisposable mCompositeDisposable;
    private SeedService seedService;
    private PlantService plantService;
    private List<SeedsInput> plantToPlant = new ArrayList<>();
    private List<PosLib> posLib = new ArrayList<>();

    private Integer idUser;
    private SharedPreferences sharedPreferences;

    public PlantMapFragment() {
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
        getSeedsPlants();
        getplantedSeed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public View InitializeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_map, container, false);
        exist_plant = (LinearLayout) view.findViewById(R.id.exist_plant);
        p1 = (LinearLayout) view.findViewById(R.id.p1);
        p2 = (LinearLayout) view.findViewById(R.id.p2);
        p3 = (LinearLayout) view.findViewById(R.id.p3);
        p4 = (LinearLayout) view.findViewById(R.id.p4);
        p5 = (LinearLayout) view.findViewById(R.id.p5);
        p6 = (LinearLayout) view.findViewById(R.id.p6);
        p7 = (LinearLayout) view.findViewById(R.id.p7);
        p8 = (LinearLayout) view.findViewById(R.id.p8);
        p9 = (LinearLayout) view.findViewById(R.id.p9);
        p10 = (LinearLayout) view.findViewById(R.id.p10);
        p11 = (LinearLayout) view.findViewById(R.id.p11);
        p12 = (LinearLayout) view.findViewById(R.id.p12);
        p13 = (LinearLayout) view.findViewById(R.id.p13);
        p14 = (LinearLayout) view.findViewById(R.id.p14);
        p15 = (LinearLayout) view.findViewById(R.id.p15);
        p16 = (LinearLayout) view.findViewById(R.id.p16);
        p17 = (LinearLayout) view.findViewById(R.id.p17);
        p18 = (LinearLayout) view.findViewById(R.id.p18);

        p1.setOnDragListener(myOnDragListener);
        p2.setOnDragListener(myOnDragListener);
        p3.setOnDragListener(myOnDragListener);
        p4.setOnDragListener(myOnDragListener);
        p5.setOnDragListener(myOnDragListener);
        p6.setOnDragListener(myOnDragListener);
        p7.setOnDragListener(myOnDragListener);
        p8.setOnDragListener(myOnDragListener);
        p9.setOnDragListener(myOnDragListener);
        p10.setOnDragListener(myOnDragListener);
        p11.setOnDragListener(myOnDragListener);
        p12.setOnDragListener(myOnDragListener);
        p13.setOnDragListener(myOnDragListener);
        p14.setOnDragListener(myOnDragListener);
        p15.setOnDragListener(myOnDragListener);
        p16.setOnDragListener(myOnDragListener);
        p17.setOnDragListener(myOnDragListener);
        p18.setOnDragListener(myOnDragListener);

        return view;


    }

    private void InitializeUtils() {
        mCompositeDisposable = new CompositeDisposable();
        seedService = ServiceFactory.createRetrofitService(SeedService.class, URLS.EndPoint);
        plantService = ServiceFactory.createRetrofitService(PlantService.class, URLS.EndPoint);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        idUser = sharedPreferences.getInt("id", 0);
    }

    private void getSeedsPlants() {
        mCompositeDisposable.add(seedService.getSeeds(idUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable throwable) {
        Log.d("erreur1", throwable.getMessage());
    }

    private void handleResponse(List<SeedsInput> seedsInput) {
        plantToPlant.clear();
        plantToPlant.addAll(seedsInput);
        exist_plant.removeAllViews();
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (SeedsInput s : seedsInput) {
            View v = li.inflate(R.layout.item_plant, null);
            if (isTablet(getActivity()) == true) {
                v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            }
            TextView libelle = (TextView) v.findViewById(R.id.libelle);
            ImageView PlantImg = (ImageView) v.findViewById(R.id.PlantImg);
            libelle.setText(s.getLibelle());
            Glide.with(getActivity()).load(URLS.Image + s.getImage()).into(PlantImg);
            exist_plant.addView(v);
            v.setOnLongClickListener(myOnLongClickListener);
            v.setOnDragListener(myOnDragListener);
        }
    }

    private void getplantedSeed() {
        mCompositeDisposable.add(plantService.getPlanteds(idUser).repeat(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePlantedResponse, this::handlePlantedError));
    }

    private void handlePlantedResponse(List<SeedsInput> plants) {

        clearMapView();
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (SeedsInput s : plants) {
            View v = li.inflate(R.layout.item_plant, null);

            TextView libelle = (TextView) v.findViewById(R.id.libelle);
            ImageView PlantImg = (ImageView) v.findViewById(R.id.PlantImg);
            libelle.setText(s.getLibelle());
            Glide.with(getActivity()).load(URLS.Image + s.getImage()).into(PlantImg);
            switch (s.getSeed().getX()) {
                case "1":
                    switch (s.getSeed().getY()) {
                        case "1":
                            p1.addView(v);
                            break;
                        case "2":
                            p4.addView(v);
                            break;
                        case "3":
                            p7.addView(v);
                            break;
                        case "4":
                            p10.addView(v);
                            break;
                        case "5":
                            p13.addView(v);
                            break;
                        case "6":
                            p16.addView(v);
                            break;
                        default:
                            break;
                    }
                    break;
                case "2":
                    switch (s.getSeed().getY()) {
                        case "1":
                            p2.addView(v);
                            break;
                        case "2":
                            p5.addView(v);
                            break;
                        case "3":
                            p8.addView(v);
                            break;
                        case "4":
                            p11.addView(v);
                            break;
                        case "5":
                            p14.addView(v);
                            break;
                        case "6":
                            p17.addView(v);
                            break;
                        default:
                            break;
                    }
                    break;
                case "3":
                    switch (s.getSeed().getY()) {
                        case "1":
                            p3.addView(v);
                            break;
                        case "2":
                            p6.addView(v);
                            break;
                        case "3":
                            p9.addView(v);
                            break;
                        case "4":
                            p12.addView(v);
                            break;
                        case "5":
                            p15.addView(v);
                            break;
                        case "6":
                            p18.addView(v);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void handlePlantedError(Throwable throwable) {
        Log.d("erreur3", throwable.getMessage());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_syncro:
                // do s.th.
                new MaterialDialog.Builder(getActivity()).contentGravity(GravityEnum.CENTER)
                        .content("Would you like to synchronize your plants")
                        .title("Cowbot").titleGravity(GravityEnum.CENTER)
                        .positiveText("synchronize")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                synchronize();
                                getplantedSeed();
                            }
                        })
                        .show().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        getplantedSeed();
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    View.OnLongClickListener myOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }

    };

    View.OnDragListener myOnDragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            String position = PositionS(v);
            if (PositionB(v) == true)
                return false;
            else {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if (((LinearLayout) v).getChildCount() == 0)
                            v.setBackgroundResource(R.drawable.plant_place_drag_enter);

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        v.setBackgroundResource(R.drawable.plant_place);
                        break;
                    case DragEvent.ACTION_DROP:
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);

                        if (isTablet(getActivity()) == true) {
                            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                        }
                        TextView libelle = (TextView) view.findViewById(R.id.libelle);
                        PosLib poslib = new PosLib(position, libelle.getText().toString());

                        LinearLayout newParent = (LinearLayout) v;
                        if (newParent.getChildCount() == 1)
                            Toast.makeText(getActivity(), "Impossible to Drag the seeds in this place", Toast.LENGTH_SHORT).show();
                        else {
                            newParent.addView(view);
                            posLib.add(poslib);
                        }
                        view.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {

                                new MaterialDialog.Builder(getActivity()).
                                        onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                newParent.removeView(view);
                                                posLib.remove(poslib);
                                            }
                                        })
                                        .title("CowBot")
                                        .titleGravity(GravityEnum.CENTER)
                                        .content("Would you like to delete the plant recently added")
                                        .contentGravity(GravityEnum.CENTER)
                                        .positiveText("Delete")
                                        .show();
                                return true;
                            }
                        });
                        getSeedsPlants();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundResource(R.drawable.plant_place);

                    default:
                        break;
                }
                return true;
            }
        }

    };

    public String PositionS(View v) {
        String position = "";
        if (v == p1)
            position = "p1";
        if (v == p2)
            position = "p2";
        if (v == p3)
            position = "p3";
        if (v == p4)
            position = "p4";
        if (v == p5)
            position = "p5";
        if (v == p6)
            position = "p6";
        if (v == p7)
            position = "p7";
        if (v == p8)
            position = "p8";
        if (v == p9)
            position = "p9";
        if (v == p10)
            position = "p10";
        if (v == p11)
            position = "p11";
        if (v == p12)
            position = "p12";
        if (v == p13)
            position = "p13";
        if (v == p14)
            position = "p14";
        if (v == p15)
            position = "p15";
        if (v == p16)
            position = "p16";
        if (v == p17)
            position = "p17";
        if (v == p18)
            position = "p18";

        return position;
    }

    public Boolean PositionB(View v) {
        Boolean b = true;
        if (v == p1)
            b = false;
        if (v == p2)
            b = false;
        if (v == p3)
            b = false;
        if (v == p4)
            b = false;
        if (v == p5)
            b = false;
        if (v == p6)
            b = false;
        if (v == p7)
            b = false;
        if (v == p8)
            b = false;
        if (v == p9)
            b = false;
        if (v == p10)
            b = false;
        if (v == p11)
            b = false;
        if (v == p12)
            b = false;
        if (v == p13)
            b = false;
        if (v == p14)
            b = false;
        if (v == p15)
            b = false;
        if (v == p16)
            b = false;
        if (v == p17)
            b = false;
        if (v == p18)
            b = false;

        return b;
    }

    public void synchronize() {
        List<PlantToPlantOutput> plantToPlantOutputs = new ArrayList<>();
        for (int i = 0; i < posLib.size(); i++)
            for (int j = 0; j < plantToPlant.size(); j++) {
                if (posLib.get(i).getLibelle().equals(plantToPlant.get(j).getLibelle())) {
                    PlantToPlantOutput plantToPlantOutput = new PlantToPlantOutput();
                    plantToPlantOutput.setUser_id(idUser);
                    plantToPlantOutput.setPlant_id(plantToPlant.get(j).getSeed().getPlant_id());
                    switch (posLib.get(i).getPos()) {
                        case "p1":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("1");
                            break;
                        case "p2":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("1");
                            break;
                        case "p3":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("1");
                            break;
                        case "p4":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("2");
                            break;
                        case "p5":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("2");
                            break;
                        case "p6":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("2");
                            break;
                        case "p7":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("3");
                            break;
                        case "p8":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("3");
                            break;
                        case "p9":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("3");
                            break;
                        case "p10":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("4");
                            break;
                        case "p11":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("4");
                            break;
                        case "p12":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("4");
                            break;
                        case "p13":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("5");
                            break;
                        case "p14":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("5");
                            break;
                        case "p15":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("5");
                            break;
                        case "p16":
                            plantToPlantOutput.setX("1");
                            plantToPlantOutput.setY("6");
                            break;
                        case "p17":
                            plantToPlantOutput.setX("2");
                            plantToPlantOutput.setY("6");
                            break;
                        case "p18":
                            plantToPlantOutput.setX("3");
                            plantToPlantOutput.setY("6");
                            break;
                    }

                    plantToPlantOutputs.add(plantToPlantOutput);
                }
            }
        for (PlantToPlantOutput ptpo : plantToPlantOutputs) {
            mCompositeDisposable.add(plantService.addPlant(ptpo).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe());

        }

        posLib.clear();
    }

    public void clearMapView() {
        p1.removeAllViews();
        p2.removeAllViews();
        p3.removeAllViews();
        p4.removeAllViews();
        p5.removeAllViews();
        p6.removeAllViews();
        p7.removeAllViews();
        p8.removeAllViews();
        p9.removeAllViews();
        p10.removeAllViews();
        p11.removeAllViews();
        p12.removeAllViews();
        p13.removeAllViews();
        p14.removeAllViews();
        p15.removeAllViews();
        p16.removeAllViews();
        p17.removeAllViews();
        p18.removeAllViews();

    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }






}
