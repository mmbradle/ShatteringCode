package com.fpx.di;

public class ReservationAcceptedEvent implements IMessage{

    public static class Quickening implements IQuickening {
        public IMessage quicken() {
            return new ReservationAcceptedEvent();
        }
    }

}
