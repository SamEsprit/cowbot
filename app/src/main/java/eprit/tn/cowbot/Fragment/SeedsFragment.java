package eprit.tn.cowbot.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import eprit.tn.cowbot.Entity.Plant.Plant;
import eprit.tn.cowbot.Entity.Seeds.SeedsInput;
import eprit.tn.cowbot.Entity.Seeds.SeedsOutput;
import eprit.tn.cowbot.Factory.ServiceFactory;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Service.PlantService;
import eprit.tn.cowbot.Service.SeedService;
import eprit.tn.cowbot.Utils.URLS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class SeedsFragment extends Fragment {


    private Spinner pot1, pot2, pot3, pot4, pot5;
    private CompositeDisposable mCompositeDisposable;
    private SeedService seedService;
    private PlantService plantService;


    private List<Plant> potList1, potList2, potList3, potList4, potList5;
    private ArrayAdapter<Plant> potList1Adapter, potList2Adapter, potList3Adapter, potList4Adapter, potList5Adapter;


    public SeedsFragment() {
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
        return InitilizeView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSeedsPlants();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void InitializeUtils() {
        mCompositeDisposable = new CompositeDisposable();
        seedService = ServiceFactory.createRetrofitService(SeedService.class, URLS.EndPoint);
        plantService = ServiceFactory.createRetrofitService(PlantService.class, URLS.EndPoint);
        potList1 = new ArrayList<>();
        potList2 = new ArrayList<>();
        potList3 = new ArrayList<>();
        potList4 = new ArrayList<>();
        potList5 = new ArrayList<>();


        potList1Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, potList1);
        potList2Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, potList2);
        potList3Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, potList3);
        potList4Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, potList4);
        potList5Adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, potList5);

    }

    public View InitilizeView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeds, container, false);
        pot1 = (Spinner) view.findViewById(R.id.pot1);
        pot2 = (Spinner) view.findViewById(R.id.pot2);
        pot3 = (Spinner) view.findViewById(R.id.pot3);
        pot4 = (Spinner) view.findViewById(R.id.pot4);
        pot5 = (Spinner) view.findViewById(R.id.pot5);
        pot1.setAdapter(potList1Adapter);
        pot2.setAdapter(potList2Adapter);
        pot3.setAdapter(potList3Adapter);
        pot4.setAdapter(potList4Adapter);
        pot5.setAdapter(potList5Adapter);
        return view;

    }


    private void getAllPlants() {
        mCompositeDisposable.add(plantService.getPlants()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePlantResponse, this::handlePlantError));
    }


    private void getSeedsPlants() {
        mCompositeDisposable.add(seedService.getSeeds(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable throwable) {
        Log.d("erreur1", throwable.getMessage());
    }

    private void handleResponse(List<SeedsInput> seedsInput) {

        if (seedsInput.size() == 0) {
            getAllPlants();
        } else {
            getAllPlants();
            for (SeedsInput s : seedsInput) {


                if (s.getSeed().getPosition().equals("A0")) {
                    Plant plant = new Plant();
                    plant.setId(s.getSeed().getPlant_id());
                    plant.setLibelle(s.getLibelle());
                    potList1.add(1, plant);
                    potList1Adapter.notifyDataSetChanged();
                    pot1.setSelection(1);
                } else if (s.getSeed().getPosition().equals("A1")) {
                    Plant plant = new Plant();
                    plant.setId(s.getSeed().getPlant_id());
                    plant.setLibelle(s.getLibelle());
                    potList2.add(1, plant);
                    potList2Adapter.notifyDataSetChanged();
                    pot2.setSelection(1);
                } else if (s.getSeed().getPosition().equals("A2")) {
                    Plant plant = new Plant();
                    plant.setId(s.getSeed().getPlant_id());
                    plant.setLibelle(s.getLibelle());
                    potList3.add(1, plant);
                    potList3Adapter.notifyDataSetChanged();
                    pot3.setSelection(1);
                } else if (s.getSeed().getPosition().equals("A3")) {
                    Plant plant = new Plant();
                    plant.setId(s.getSeed().getPlant_id());
                    plant.setLibelle(s.getLibelle());
                    potList4.add(1, plant);
                    potList4Adapter.notifyDataSetChanged();
                    pot4.setSelection(1);
                } else if (s.getSeed().getPosition().equals("A4")) {
                    Plant plant = new Plant();
                    plant.setId(s.getSeed().getPlant_id());
                    plant.setLibelle(s.getLibelle());
                    potList5.add(1, plant);
                    potList5Adapter.notifyDataSetChanged();
                    pot5.setSelection(1);
                }
            }
        }
    }

    private void handlePlantError(Throwable throwable) {
        Log.d("erreur2", throwable.getMessage());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handlePlantResponse(List<Plant> plants) {


        potList1.addAll(plants);
        potList2.addAll(plants);
        potList3.addAll(plants);
        potList4.addAll(plants);
        potList5.addAll(plants);
        potList1.add(0,new Plant("Choise Seed"));
        potList2.add(0,new Plant("Choise Seed"));
        potList3.add(0,new Plant("Choise Seed"));
        potList4.add(0,new Plant("Choise Seed"));
        potList5.add(0,new Plant("Choise Seed"));
        potList1Adapter.notifyDataSetChanged();
        potList2Adapter.notifyDataSetChanged();
        potList3Adapter.notifyDataSetChanged();
        potList4Adapter.notifyDataSetChanged();
        potList5Adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_seeds, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_seeds_syncro:
                // do s.th.
                Plant plant1 = (Plant) pot1.getSelectedItem();
                Plant plant2 = (Plant) pot2.getSelectedItem();
                Plant plant3 = (Plant) pot3.getSelectedItem();
                Plant plant4 = (Plant) pot4.getSelectedItem();
                Plant plant5 = (Plant) pot5.getSelectedItem();

                if (plant1.getLibelle().equals("Choise seed")) {
                    System.out.println("do nothing1");
                } else {
                    System.out.println(plant1.getId());
                    mCompositeDisposable.add(seedService.SeedControl(new SeedsOutput(10, plant1.getId(), "A0"))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                }
                if (plant2.getLibelle().equals("Choise seed")) {
                    System.out.println("do nothing2");
                } else {
                    mCompositeDisposable.add(seedService.SeedControl(new SeedsOutput(10, plant2.getId(), "A1"))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                }
                if (plant3.getLibelle().equals("Choise seed")) {
                    System.out.println("do nothing3");
                } else {
                    mCompositeDisposable.add(seedService.SeedControl(new SeedsOutput(10, plant3.getId(), "A2"))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                }
                if (plant4.getLibelle().equals("Choise seed")) {
                    System.out.println("do nothing4");
                } else {
                    mCompositeDisposable.add(seedService.SeedControl(new SeedsOutput(10, plant4.getId(), "A3"))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                }
                if (plant5.getLibelle().equals("Choise seed")) {
                    System.out.println("do nothing5");
                } else {
                    mCompositeDisposable.add(seedService.SeedControl(new SeedsOutput(10, plant5.getId(), "A4"))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
