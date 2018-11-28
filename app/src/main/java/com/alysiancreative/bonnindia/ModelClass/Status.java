package com.alysiancreative.bonnindia.ModelClass;

public class Status {
    private int Done;
    private int InProgress;
    private int New;
    private int Closed;

    public int getDone() {
        return Done;
    }

    public void setDone(int done) {
        Done = done;
    }

    public int getInProgress() {
        return InProgress;
    }

    public void setInProgress(int inProgress) {
        InProgress = inProgress;
    }

    public int getNew() {
        return New;
    }

    public void setNew(int aNew) {
        New = aNew;
    }

    public int getClosed() {
        return Closed;
    }

    public void setClosed(int closed) {
        Closed = closed;
    }
}
