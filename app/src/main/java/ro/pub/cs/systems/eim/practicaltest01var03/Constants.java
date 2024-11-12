package ro.pub.cs.systems.eim.practicaltest01var03;

public interface Constants {
    public final static String INPUT1 = "input1";
    public final static String INPUT2 = "input2";

    public final static String OUTPUT = "output";

    public final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01var03.sum",
            "ro.pub.cs.systems.eim.practicaltest01var03.diff"
    };

    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";
}
