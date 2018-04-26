package ru.antonpriamosudov.treedivider;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private Node<T> parentNode = null;
    private List<Node<T>> childNodesList = new ArrayList<Node<T>>();
    private T data = null;

    private Integer subTreeSize = 0;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    public Node(Node<T> node) {
        this.data = node.getData();
        this.parentNode = node.getParentNode();
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

    public void addChildNodes(T... nodesData) {
        for (T data: nodesData) {
            addChildNode(new Node<T>(data));
        }
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
}
