package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.lang.ref.WeakReference;

/**
 * Created by strunov on 9/8/2015.
 */
public class Vector3fProperty extends ObservableValueBase<Vector3f> implements Property<Vector3f> {

    private Vector3f value = Vector3f.ZERO;
    private ObservableValue<? extends Vector3f> observable;
    private InvalidationListener listener = null;

    private boolean valid = true;

    public Vector3fProperty() {
    }

    public Vector3fProperty(Vector3f value) {
        this.value = value;
    }

    private void markInvalid() {
        if (valid) {
            valid = false;
            invalidated();
            fireValueChangedEvent();
        }
    }

    protected void invalidated() {
    }

    @Override
    public void bind(ObservableValue<? extends Vector3f> newObservable) {
        if (!newObservable.equals(observable)) {
            unbind();
            observable = newObservable;
            if (listener == null) {
                listener = new Listener(this);
            }
            observable.addListener(listener);
            markInvalid();
        }
    }

    @Override
    public void unbind() {
        if (observable != null) {
            value = observable.getValue();
            observable.removeListener(listener);
            observable = null;
        }
    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public void bindBidirectional(Property<Vector3f> other) {
        Bindings.bindBidirectional(this, other);
    }

    @Override
    public void unbindBidirectional(Property<Vector3f> other) {
        Bindings.unbindBidirectional(this, other);
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Vector3f getValue() {
        valid = true;
        return observable == null ? value : observable.getValue();
    }

    @Override
    public void setValue(Vector3f value) {
        if (isBound()) {
            throw new java.lang.RuntimeException("A bound value cannot be set.");
        }

        if (this.value == null || !this.value.equals(value)){
            this.value = value;
            markInvalid();
        }
    }


    private static class Listener implements InvalidationListener {

        private final WeakReference<Vector3fProperty> wref;

        public Listener(Vector3fProperty ref) {
            this.wref = new WeakReference<>(ref);
        }

        @Override
        public void invalidated(Observable observable) {
            Vector3fProperty ref = wref.get();
            if (ref == null) {
                observable.removeListener(this);
            } else {
                ref.markInvalid();
            }
        }
    }
}
