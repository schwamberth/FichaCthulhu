package br.com.fichacthulhu;

public interface OnDeleteListener<T> {
    void handleDelete(T t);
}
