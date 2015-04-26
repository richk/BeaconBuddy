package com.codepath.beacon.events;

public class EventName {

    // Home Page
    public static final String HOME_PAGE_RENDER = "home_page.render";
    public static final String HOME_PAGE_SIGNUP_CLICKED = "home_page.click.signup";
    public static final String HOME_PAGE_LOGIN_CLICKED = "home_page.click.login";

    // Signup
    public static final String SIGNUP_PAGE_RENDER = "signup_page.render";
    public static final String SIGNUP_PAGE_SIGNUP_CLICKED = "signup_page.click.signup";

    // Login
    public static final String LOGIN_PAGE_RENDER = "login_page.render";
    public static final String LOGIN_PAGE_LOGIN_CLICKED = "login_page.click.login";

    // Recipe list
    public static final String RECIPE_PAGE_RENDER = "recipe_page.render";
    public static  final String RECIPE_PAGE_NEW_RECIPE_CLICK = "recipe_page.click.new_recipe";
    public static final String RECIPE_PAGE_REFRESH_CLICK = "recipe_page.click.refresh";

    // New Recipe
    public static final class NewRecipe {
        public static final String RENDER = "new_recipe_page.render";
        public static final String CLICK_SELECT_BEACON =
                "new_recipe_page.click.select_beacon";
        public static final String CLICK_SELECT_ACTION =
                "new_recipe_page.click.select_action";
        public static final String CLICK_DONE =
                "new_recipe_page.click.done";
        public static final String CLICK_DELETE =
                "new_recipe_page.click.delete";
    }


    // Beacons List
    public final static class BeaconList {
        public static final String RENDER = "beacons_list.render";
        public static final String CLICK_BEACON =
                "beacons_list.new_beacons.click.beacon";
        public static final String PAGE_TYPE_PROPERTY = "page_type";

        public static final class PageType {
            public static final String NEW_BEACONS = "new_beacons";
            public static final String MY_BEACONS = "my_beacons";
        }

        public static final String CLICK_REFRESH = "beacons_list.click.refresh";
    }

    // Recipe Action Page
    public static final class RecipeAction {
        public static final String RENDER = "recipe_action_page.render";

        public static final String SELECT_TRIGGER =
                "recipe_action_page.select.trigger";
        public static final String TRIGGER_TYPE_PROPERTY = "trigger_type";

        public static class TriggerType {
            public static final String TRIGGER_APPROACHING = "trigger_approaching";
            public static final String TRIGGER_LEAVING = "trigger_leaving";
        }

        public static final String SELECT_ACTION =
                "recipe_action_page.select.action";
        public static final String ACTION_TYPE_PROPERTY = "action_type";

        public static final class ActionType {
            public static final String ACTION_NOTIFICATION = "action_notification";
            public static final String ACTION_SMS = "action_sms";
            public static final String ACTION_SILENT_MODE = "action_silent_mode";
            public static final String ACTION_PHILIPS_HUE = "action_philips_hue";
            public static final String ACTION_LAUNCH_APP = "action_launch_app";
        }

        public static final String CLICK_SAVE = "recipe_action_page.click.save";
    }
}
