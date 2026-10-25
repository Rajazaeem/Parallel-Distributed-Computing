public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("Main thread: "
                + Thread.currentThread().getName());

        TelemetryThread telemetry = new TelemetryThread();
        telemetry.setName("Telemetry-Worker");

        Thread logWorker =
                new Thread(new LogProcessorTask(), "Log-Worker");

        telemetry.start();
        logWorker.start();

        System.out.println("Both worker threads have started.");
    }
}
