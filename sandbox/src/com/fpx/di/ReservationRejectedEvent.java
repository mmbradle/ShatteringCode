package com.fpx.di;

public class ReservationRejectedEvent implements IMessage {

    public static class Quickening implements IQuickening {
        public IMessage quicken() {
            return new ReservationRejectedEvent();
        }
    }

}
