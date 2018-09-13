/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.irmansyah.kamusku.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;
import com.irmansyah.kamusku.ui.translete.EngIndAdapter;
import com.irmansyah.kamusku.ui.translete.IndEngAdapter;

import java.util.ArrayList;

/**
 * Created by amitshekhar on 11/07/17.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("progress")
    public static void setProgressBar(ProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @BindingAdapter({"engIndAdapter"})
    public static void addEngIndAdapter(RecyclerView recyclerView,
                                             ArrayList<EnglishIndonesia> models) {
        EngIndAdapter adapter = (EngIndAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(models);
        }
    }

    @BindingAdapter({"indEngAdapter"})
    public static void addIndEngAdapter(RecyclerView recyclerView,
                                        ArrayList<IndonesiaEnglish> models) {
        IndEngAdapter adapter = (IndEngAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(models);
        }
    }
}
