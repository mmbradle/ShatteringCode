package com.shatteringstone.tij;

public class Private {
    private int outerI;
    InnerPrivate innerObj;
    public Private(){
        this.outerI = 0;
        this.innerObj = new InnerPrivate();
        this.innerObj.innerI = 0;
    }
    
    private class InnerPrivate{
        private int innerI;
        public InnerPrivate() {
            outerI = 0;
            innerI = 0;
        }
    }

}
