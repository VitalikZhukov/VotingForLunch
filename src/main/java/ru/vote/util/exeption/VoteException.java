package ru.vote.util.exeption;

public class VoteException extends RuntimeException {
    public VoteException() {
        super("You can vote once a day");
    }
}
