package app.model;

import app.linker.Component;
import app.linker.Composite;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Momento {

    private Queue<Object> momentoList = new ArrayDeque<>();


    public void push(Object state) {
        momentoList.add(state);
    }

    public Object poll() {
        return momentoList.poll();
    }

    public boolean remove(Object state) {
        return momentoList.remove(state);
    }

    public int getSize() {
        return momentoList.size();
    }

    public ArrayList<Object> getListShapes() {
        return new ArrayList<>(momentoList);
    }

    public void changeShape(ArrayList<Component> arrayList) {
        if (!arrayList.isEmpty()) {
            for (Component comp : arrayList) {
                if (momentoList.contains(comp.getDecorate())) {
                    for (Object obj : momentoList) {
                        if (obj instanceof Decorate && obj.equals(comp.getDecorate())) {
                            obj = comp.getDecorate();
                        }
                    }
                }
            }
        }
    }

}