package service;

public class NegocioException extends RuntimeException{

    private static final long serialVertionUID = 1L;

    public NegocioException(String msg) {
        super(msg);
    }
}
