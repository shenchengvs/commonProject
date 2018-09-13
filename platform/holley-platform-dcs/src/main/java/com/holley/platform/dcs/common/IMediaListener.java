package com.holley.platform.dcs.common;

public interface IMediaListener 
{
    void onReceived(Object sender, ReceiveEventArgs e);    


    void onActive(Object media, boolean active);
}