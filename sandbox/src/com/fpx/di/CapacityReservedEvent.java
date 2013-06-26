package com.fpx.di;

public class CapacityReservedEvent implements IMessage {

    public static class Quickening implements IQuickening {
        public IMessage quicken() {
            return new CapacityReservedEvent();
        }
    }
}
