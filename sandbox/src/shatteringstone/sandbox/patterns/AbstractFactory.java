package shatteringstone.sandbox.patterns;

public class AbstractFactory {
    public static void main(String[] args) {
        GUIBuilder builder = new GUIBuilder();
        AbstractWidgetFactory widgetFactory = null;
        // check what platform we're on
        if (Platform.currentPlatform() == "MACOSX") {
            widgetFactory = new MacOSXWidgetFactory();
        }
        else {
            widgetFactory = new MsWindowsWidgetFactory();
        }
        builder.buildWindow(widgetFactory);
    }
}

class Platform {
    public static String currentPlatform() {
        return "MACOSX";
    }
}

// Our AbstractProduct
interface WindowInterface {

    public void setTitle(String text);

    public void repaint();
}

// ConcreteProductA1
class MSWindow implements WindowInterface {
    public void setTitle(String text) {
        // MS Windows specific behaviour
    }

    public void repaint() {
        // MS Windows specific behaviour
    }
}

// ConcreteProductA2
class MacOSXWindow implements WindowInterface {
    public void setTitle(String text) {
        // Mac OSX specific behaviour
    }

    public void repaint() {
        // Mac OSX specific behaviour
    }
}

// AbstractFactory
interface AbstractWidgetFactory {
    public WindowInterface createWindow();
}

// ConcreteFactory1
class MsWindowsWidgetFactory implements AbstractWidgetFactory {
    // create an MSWindow
    public WindowInterface createWindow() {
        MSWindow window = new MSWindow();
        return window;
    }
}

// ConcreteFactory2
class MacOSXWidgetFactory implements AbstractWidgetFactory {
    // create a MacOSXWindow
    public WindowInterface createWindow() {
        MacOSXWindow window = new MacOSXWindow();
        return window;
    }
}

// Client
class GUIBuilder {
    public void buildWindow(AbstractWidgetFactory widgetFactory) {
        WindowInterface window = widgetFactory.createWindow();
        window.setTitle("New Window");
    }
}
