package eprit.tn.cowbot.CallBack;

import java.util.List;

/**
 * Created by Sami on 02/02/2017.
 */

public interface ServiceCallBack<T>  {
     void onSuccess(List<T> plants);
     void onSuccess(List<T> plants,String s);
     void onFail();
     void noData();
}
