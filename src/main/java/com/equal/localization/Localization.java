package com.equal.localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {

    private static Locale currentLocale;
    private static ResourceBundle messages;

    public final static String CLICK_BUTTON = "button_click";
    public final static String BUTTON_SUBMIT = "button_submit";
    public final static String NO_BUTTON = "no_button";
    public final static String SELECT_RANDOM = "select_random";
    public final static String SELECT_VALUE = "select_value";
    public final static String SELECT_DATA_WRONG = "select_data_wrong";
    public final static String NO_SELECT = "no_select";
    public final static String SELECT_GET_TEXT = "select_get_text";
    public final static String TEXT_CONTAINER_GET = "text_container_get";
    public final static String NO_TEXT_CONTAINER = "no_text_container";
    public final static String INPUT_SET_VALUE = "input_set_value";
    public final static String NO_INPUT = "no_input";
    public final static String CLICK_JQUERY = "button_click_jquery";
    public final static String EXECUTE_SCRIPT = "execute_scipt";
    public final static String NO_ELEMENT = "no_element";

    private static void init() {
        currentLocale = new Locale("en");
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public static String getMessage(String msg) {
        if (messages == null) {
            init();
        }

        return messages.getString(msg);
    }

    public static String getMessage(String msg, String... includes) {
        if (messages == null) {
            init();
        }

        return MessageFormat.format(messages.getString(msg), includes);
    }
}
