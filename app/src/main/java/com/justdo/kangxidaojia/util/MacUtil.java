package com.justdo.kangxidaojia.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MacUtil {
    /*public static byte[] getMacAddress() {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        while (interfaces.hasMoreElements()) {
            final NetworkInterface ni = interfaces.nextElement();
            try {
                if (ni.isLoopback() || ni.isPointToPoint() || ni.isVirtual())
                    continue;
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] macAddress = null;
            try {
                macAddress = ni.getHardwareAddress();
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (macAddress != null && macAddress.length > 0)
                return macAddress;
        }
        return nul;
    }*/
    public static String getMacAddress(Context context){
 /*获取mac地址有一点需要注意的就是android 6.0版本后，以下注释方法不再适用，不管任何手机都会返回"02:00:00:00:00:00"这个默认的mac地址，这是googel官方为了加强权限管理而禁用了getSYstemService(Context.WIFI_SERVICE)方法来获得mac地址。*/
        String macAddress= "";
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        macAddress = wifiInfo.getMacAddress();
        return macAddress;

/*        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;*/
    }
}
