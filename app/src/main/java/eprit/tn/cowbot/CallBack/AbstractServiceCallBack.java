package eprit.tn.cowbot.CallBack;

import java.util.List;

/**
 * Created by Sami on 29/03/2017.
 */

public abstract class AbstractServiceCallBack<T> implements ServiceCallBack {
    @Override
    public void onSuccess(List t) {

    }

    @Override
    public void onSuccess(List t, String s) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void noData() {

    }
}
