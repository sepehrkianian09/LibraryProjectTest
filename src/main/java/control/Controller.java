package control;

public abstract class Controller {
    private static boolean hasLoggedIn = false;
    private static String loginUserName;
    private static String loginType;

    public static String getLoginType() {
        return loginType;
    }

    public static void setLoginType(String loginType) {
        Controller.loginType = loginType;
    }

    public static String getLoginUserName() {
        return loginUserName;
    }

    public static void setLoginUserName(String userName) {
        Controller.loginUserName = userName;
    }

    public static boolean getLoginStatus(){
        return Controller.hasLoggedIn;
    }

    public static void setLoginStatus(boolean newLoginStatus){
        Controller.hasLoggedIn = newLoginStatus;
    }


}
