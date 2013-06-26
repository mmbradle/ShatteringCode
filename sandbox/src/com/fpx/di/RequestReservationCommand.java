package com.fpx.di;

public class RequestReservationCommand implements IMessage{

    public static class Quickening implements IQuickening {
        public IMessage quicken() {
            return new RequestReservationCommand();
        }
    }
}
