package com.huawei.deepitm.helper;

public interface HomeObserverable  {
    public void registerObserver(HomeObserver o);

    public void removeObserver(HomeObserver o);

    public void notifyObserver(Integer num);
}
