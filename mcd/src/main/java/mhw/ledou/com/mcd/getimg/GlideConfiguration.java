package mhw.ledou.com.mcd.getimg;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

public class GlideConfiguration implements GlideModule {
     
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            // Apply options to the builder here.
            builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        }

    @Override
    public void registerComponents(Context context, Registry registry) {

    }
}