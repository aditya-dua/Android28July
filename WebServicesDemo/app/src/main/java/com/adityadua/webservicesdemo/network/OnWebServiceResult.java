package com.adityadua.webservicesdemo.network;

import com.adityadua.webservicesdemo.CommonUtilities;

/**
 * Created by AdityaDua on 18/09/17.
 */

public interface OnWebServiceResult {

    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
