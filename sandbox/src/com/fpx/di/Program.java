package com.fpx.di;

public class Program
{
    public static void main(String... args)
    {
        DirectoryInfo queueDirectory = 
            new DirectoryInfo("..\\..\\..\\BookingWebUI\\Queue").CreateIfAbsent();
        DirectoryInfo singleSourceOfTruthDirectory = 
            new DirectoryInfo("..\\..\\..\\BookingWebUI\\SSoT").CreateIfAbsent();
        DirectoryInfo viewStoreDirectory = 
            new DirectoryInfo("..\\..\\..\\BookingWebUI\\ViewStore").CreateIfAbsent();

        String extension = "txt";

        FileDateStore fileDateStore = new FileDateStore(
            singleSourceOfTruthDirectory,
            extension);

        IQuickening[] quickenings = new IQuickening[]
        {
            new RequestReservationCommand.Quickening(),
            new ReservationAcceptedEvent.Quickening(),
            new ReservationRejectedEvent.Quickening(),
            new CapacityReservedEvent.Quickening(),
        };

        CompositeDisposable disposable = new CompositeDisposable();
        Subject messageDispatcher = new Subject();
        disposable.Add(
            messageDispatcher.Subscribe(
                new Dispatcher<IMessage>(
                    new CapacityGate(
                        new JsonCapacityRepository(
                            fileDateStore,
                            fileDateStore,
                            quickenings),
                        new JsonChannel<IMessage>(
                            new FileQueueWriter<IMessage>(
                                queueDirectory,
                                extension)),
                        new JsonChannel<IMessage>(
                            new FileQueueWriter<IMessage>(
                                queueDirectory,
                                extension)),
                        new JsonChannel<IMessage>(
                            new FileQueueWriter<IMessage>(
                                queueDirectory,
                                extension))))));
        disposable.Add(
            messageDispatcher.Subscribe(
                new Dispatcher<IMessage>(
                    new MonthViewUpdater(
                        new FileMonthViewStore(
                            viewStoreDirectory,
                            extension)))));

        QueueConsumer q = new QueueConsumer(
            new FileQueue(
                queueDirectory,
                extension),
            new JsonStreamObserver(
                quickenings,
                messageDispatcher));
    }
}