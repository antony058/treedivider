package ru.antonpriamosudov.treedivider.exception;

public class NotValidTreeSize extends RuntimeException {
    private static final String NOT_VALID_TREE_SIZE = "Недопустимый размер элементов в глубину";

    public NotValidTreeSize(String message) {
        super(message);
    }

    public NotValidTreeSize() {
        super(NOT_VALID_TREE_SIZE);
    }
}
