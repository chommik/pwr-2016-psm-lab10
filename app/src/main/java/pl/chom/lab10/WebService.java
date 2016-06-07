package pl.chom.lab10;

import java.util.HashMap;
import java.util.Map;

public class WebService {
    public boolean setItemValue(int itemId, String content) {
        return false;
    }

    public String getItem(int itemId) {



    }

    public Map<Integer, String> getItems() {
        HashMap<Integer, String> ret = new HashMap<>();

        ret.put(1, "asdaasdas");
        ret.put(2, "asdasdasdasdasdasdasdas");

        return ret;
    }
}
