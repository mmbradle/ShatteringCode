package shatteringstone.sandbox;

import java.util.ServiceLoader;

public class ServiceProviderExample {
    public static void main(String[] ignored) {
        HelloProvider provider = HelloProvider.getDefault();
        System.out.println(provider.getMessage());
    }
}

abstract class HelloProvider {

    public static HelloProvider getDefault() {
        ServiceLoader<HelloProvider> ldr = ServiceLoader.load(HelloProvider.class);
        for (HelloProvider provider : ldr) {
            // We are only expecting one
            return provider;
        }
        throw new Error("No HelloProvider registered");
    }

    public abstract String getMessage();
}

class HelloImpl extends HelloProvider {
    @Override
    public String getMessage() {
        return "Hello World";
    }
}