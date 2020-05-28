package org.med.salemx.commons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayInputStream;

public class SaleMXCommons {

    public static final String URL_SERVER = "http://192.168.0.13:8084/Sale_MX/";

    public static Drawable fromBase64(Context context,
                                      String str64) throws Exception
    {
        if(str64 == null || str64.isEmpty())
            return null;

        byte[] bytes = Base64.decode(str64, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Drawable img = Drawable.createFromResourceStream(context.getResources(),
                null,bais,null,null);
        bais.close();
        return img;
    }
}
