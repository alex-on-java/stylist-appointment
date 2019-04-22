package ru.buyanov.stylist.exception;

public class NoStylistsException extends RuntimeException {
    public NoStylistsException() {
        super("Currently we have no registered stylists. Please come back later");
    }
}
