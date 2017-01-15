package service;

public class NegocioException extends Exception{

    private static final long serialVertionUID = 1L;

    public NegocioException(String msg) {
        super(msg);
    }
}
