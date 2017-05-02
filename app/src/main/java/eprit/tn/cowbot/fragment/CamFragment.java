package eprit.tn.cowbot.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import eprit.tn.cowbot.R;

public class CamFragment extends Fragment implements View.OnClickListener {

    VideoView vidView;
    ImageButton up, down, left, right, zoommoins, zoomplus;

    public CamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    private void playStream(String src) {
        Uri UriSrc = Uri.parse(src);
        if (UriSrc == null) {
            Toast.makeText(getActivity(),
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        } else {
            vidView.setVideoURI(UriSrc);
            vidView.start();

            Toast.makeText(getActivity(),
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }
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
        return view;
    }

    public void ViewCreated() {
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        zoommoins.setOnClickListener(this);
        zoomplus.setOnClickListener(this);


        Picasso.with(getActivity()).load(R.drawable.up).fit().into(up);
        Picasso.with(getActivity()).load(R.drawable.down).fit().into(down);
        Picasso.with(getActivity()).load(R.drawable.left).fit().into(left);
        Picasso.with(getActivity()).load(R.drawable.right).fit().into(right);
        Picasso.with(getActivity()).load(R.drawable.moins).fit().into(zoommoins);
        Picasso.with(getActivity()).load(R.drawable.plus).fit().into(zoomplus);
        playStream("http://41.228.241.72:9090/stream/video.mjpeg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up:
                Toast.makeText(getContext(), "machine en avence", Toast.LENGTH_SHORT).show();
                break;
            case R.id.down:
                Toast.makeText(getContext(), "machine en arriére", Toast.LENGTH_SHORT).show();
                break;
            case R.id.left:
                Toast.makeText(getContext(), "machine to left", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right:
                Toast.makeText(getContext(), "machine to right", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zoomplus:
                Toast.makeText(getContext(), "machine camera zoom +", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zoommoins:
                Toast.makeText(getContext(), "machine camera zoom -", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
