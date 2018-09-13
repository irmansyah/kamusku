package com.irmansyah.kamusku.data;

import com.irmansyah.kamusku.data.local.db.KamusEngIndHelper;
import com.irmansyah.kamusku.data.local.db.KamusIndEngHelper;
import com.irmansyah.kamusku.data.local.prefs.PreferencesHelper;

/**
 * Created by irmansyah on 06/03/18.
 */

public interface DataManager extends PreferencesHelper, KamusEngIndHelper, KamusIndEngHelper {
}
