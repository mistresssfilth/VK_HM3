package exception;

public class NoEmptyPlaceException extends Exception{
    public NoEmptyPlaceException(String message) {
        System.out.println(message);
    }
}
