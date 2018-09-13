package com.holley.platform.dcs;

import com.holley.platform.dcs.channel.DCSChannel;

public interface IDeviceService {

    public String getDeviceCode();

    public int onReceive(byte[] msg);

    public void notifyLogin(byte[] data);

    public void linkChannel(DCSChannel channel);

    public Object getDevice(int subCommAddr);

    public Object getDeviceByID(int devID);

    public boolean saveData(Object rec);

    public boolean sendBizCmd(Object data);
}
