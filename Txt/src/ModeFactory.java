/*
    This class creating the modes of the app which can be dark or light
 */

class ModeFactory {
    public void createMode(String mode) {
        if ("Dark-Mode".equalsIgnoreCase(mode)) {
            new DarkMode();
        } else if ("Light-Mode".equalsIgnoreCase(mode))        // if mode parameter equals to light mode
        {
            new LightMode();
        }

    }
}
