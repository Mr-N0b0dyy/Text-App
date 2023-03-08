/*
    This class makes sure that TextApp created just one time
*/
class Singleton {
    private static final TextApp app = new TextApp();

    // The global access method to get the singleton instance.
    public static TextApp getApp() {
        return app;
    }

}
