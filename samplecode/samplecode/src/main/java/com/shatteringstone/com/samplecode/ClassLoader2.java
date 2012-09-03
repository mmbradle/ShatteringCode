package com.shatteringstone.com.samplecode;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

//
public class ClassLoader2 {
    public void doIt() {
        try {
            File file = new File("dataset CVS.zip");
            
            URL[] url;
                url = new URL[] {
                    file.toURI().toURL()
                };
            
            URLClassLoader child = new URLClassLoader (url, this.getClass().getClassLoader());
            Class classToLoad = Class.forName ("com.fpx.cvscaremark.CVSFunctions", true, child);
            Method method = classToLoad.getDeclaredMethod ("Min");
            Object instance = classToLoad.newInstance ();
            //Object result = method.invoke (instance);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("here");
        ClassLoader2 loader = new ClassLoader2();
        loader.doIt();
        System.out.println("there");
    }
}
