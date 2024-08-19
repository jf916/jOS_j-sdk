package jOS.Core.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import jOS.Core.jLIBCoreApp;

public class LabelUtils {

    public static String getActivityLabel(Context context) {
        PackageManager pm = context.getPackageManager();

        Intent intent = ((jLIBCoreApp)context.getApplicationContext()).getCurrentActivity().getIntent();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        if (resolveInfo != null) {
            resolveInfo.loadLabel(pm);
            return resolveInfo.loadLabel(pm).toString();
        } else {
            return "";
        }
    }

    public static String getExternalActivityLabel(String title, Context context, ComponentName componentName) {
        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent();
        intent.setComponent(componentName);
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        if (resolveInfo != null) {
            resolveInfo.loadLabel(pm);
            return resolveInfo.loadLabel(pm).toString();
        } else {
            return getExternalAppLabel(pm, componentName, context, title);
        }
    }

    public static String getExternalAppLabel(PackageManager pm, ComponentName componentName, Context context, String title) {
        try {
            return pm.getApplicationLabel(pm.getApplicationInfo(componentName.getPackageName(), 0)).toString();
        } catch (Exception e) {
            ErrorUtils.handle(e, context);
            return title;
        }
    }

    public static String getAppLabel(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}
