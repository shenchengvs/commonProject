package com.holley.platform.dcs.common;

/** 
 * Available Device state changes.
*/
public enum MediaState
{
    /** 
     Device is closed.<br/>            
    */
    CLOSED,
    /** 
     Device is open.<br/>            
    */
    OPEN,
    /** 
     Device is opening.<br/>
    */
    OPENING,
    /** 
     Device is closing.<br/>            
    */
    CLOSING,
    /** 
     Device type has changed.<br/>            
    */
    CHANGED;
}