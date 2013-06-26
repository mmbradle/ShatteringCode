package com.fpx.di;

public class SoldOutEvent implements IMessage{
    public class Quickening implements IQuickening {
        public IMessage quicken() {
            return new SoldOutEvent();
        }
    }
}
