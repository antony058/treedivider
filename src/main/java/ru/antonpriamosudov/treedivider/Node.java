package ru.antonpriamosudov.treedivider;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private Node<T> parentNode = null;
    private List<Node<T>> childNodesList = new ArrayList<Node<T>>();
    private int weight;
    private T data;

    private Integer subTreeSize = 0;

    public Node() {

    }

    public Node(int weight, T data) {
        this.weight = weight;
        this.data = data;
    }

    public Node(Node<T> node) {
        this.weight = node.getWeight();
        this.data = node.getData();
        this.parentNode = node.getParentNode();
    }

    public void copySubTree(Node<T> node) {
        this.weight = node.getWeight();
        this.data = node.getData();
        this.childNodesList = node.getChildNodesList();
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    public List<Node<T>> getChildNodesList() {
        return childNodesList;
    }

    public void setChildNodesList(List<Node<T>> childNodesList) {
        this.childNodesList = childNodesList;
    }

    public void addChildNode(Node<T> childNode) {
        childNodesList.add(childNode);
        childNode.setParentNode(this);
    }

    public void removeChildNode(Node<T> childNode) {
        childNodesList.remove(childNode);
        childNode.setParentNode(null);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getChildNodesAmount() {
        return childNodesList.size();
    }

    public Integer getSubTreeSize() {
        return subTreeSize;
    }

    public void setSubTreeSize(Integer subTreeSize) {
        this.subTreeSize = subTreeSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = result * prime + weight;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        int value = ((Node<Object>) obj).getWeight();

        if (weight != value)
            return false;

        return true;
    }
}
