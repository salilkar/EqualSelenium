package com.equal.pages.base;

import com.equal.common.Logger;
import com.equal.pages.base.PageObject;


public class PageFactory {
    /*
     * Function for getting new pages
     */
    public static PageObject init(Class<? extends PageObject> a) {

        try {
            return a.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Logger.logError("Page is not initialized");
        return null;


    }

}
