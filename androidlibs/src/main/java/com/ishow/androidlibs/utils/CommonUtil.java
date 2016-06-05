package com.ishow.androidlibs.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonUtil {
    private static final String TAG = "CommonUtil";

    /**
     * 判断当前网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 判断服务是否运行
     *
     * @param context
     * @param clazz   要判断的服务的class
     * @return
     */
    public static boolean isServiceRunning(Context context,
                                           Class<? extends Service> clazz) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningServiceInfo> services = manager.getRunningServices(100);
        for (int i = 0; i < services.size(); i++) {
            String className = services.get(i).service.getClassName();
            if (className.equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 格式化时间格式,格式化为yyyy-MM-dd  HH:mm:ss
     *
     * @param time 时间 单位：毫秒
     * @return String
     */
    public static String getDateFormat(long time) {
        //		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        //		return sdf.format(new Date(time));
        return getDateFormat(time, "yyyy-MM-dd  HH:mm:ss");
    }

    /**
     * 根据时间格式将时间格式化
     *
     * @param time   需要格式化的时间   单位：毫秒
     * @param format 格式化后的类型
     * @return String
     */
    public static String getDateFormat(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 根据时间字符串将格式化为yyyy-MM-dd
     *
     * @param time 时间字符串，如yyyy-MM-dd  HH:mm:ss
     * @return
     */
    public static String getDateFormat(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(time);
            return sdf.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将指定字符串进行MD5加密
     *
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    /**
     * 根据指定字符串获取其md5加密后再经过base64转换的值
     *
     * @param str 要加密的字符串
     * @return md5加密经进行了base64转换后的值
     */
    public static String getMd5Code(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("md5");
            byte[] afterStr = md5.digest(str.getBytes());
            byte[] encode = Base64.encode(afterStr, Base64.DEFAULT);
            return new String(encode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取某个路径文件的md5值
     *
     * @param path 文件路径
     * @return md5值
     */
    public static String getAppMd5(String path) {
        MessageDigest digest;
        try {

            digest = MessageDigest.getInstance("md5");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            fis.close();
            StringBuffer sb = new StringBuffer();
            byte[] result = digest.digest();
            for (byte b : result) {
                // 获取到低八位
                int number = b & 0xff;
                String hex = Integer.toHexString(number);

                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 获取app版本号名称
     *
     * @return
     */
    public static String getVersionName() {
        Context context = UIUtils.getContext();
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            //			int codeVersion = pi.versionCode;
            return pi.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取app版本号
     *
     * @return
     */
    public static int getVersion() {
        Context context = UIUtils.getContext();
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 根据指定的时间日期字符串与当前时间进行比较大小后返回相差天数
     *
     * @param endTime 时间日期
     * @return 天数
     */
    public static int getDaysByDateTime(String endTime, String format) {

        long intervalMilli = 0;
        //"yyyy-MM-dd hh:mm:ss"
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(endTime);
            Date current = new Date();
            intervalMilli = date.getTime() - current.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }


    /**
     * 检测手机是否连接了wifi
     * @param mContext
     * @return
     */
    public static boolean checkWifiConnection(Context mContext) {
        final ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (info.isAvailable()&&info.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测手机是否连接网络
     * @param context
     * @return
     */
    public static boolean checkNetworkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        final NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isAvailable() || mobile.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInstallAppByPackageName(String packageName){
        PackageManager pm=UIUtils.getContext().getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);
        for (PackageInfo info : infos){
            if(info.packageName.equals(packageName)){
                return true;
            }
        }
        return false;
    }

    public static int getSystemVersion(){
        return android.os.Build.VERSION.SDK_INT;
    }
}