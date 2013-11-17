package com.tiarebalbi.events.process;

/**
 *  Interface with default method for call the event
 *
 * @author Tiarê Balbi Bonamini
 * @since 
 *
 * @param <T> Type of classs to inject the {@link Event}
 */
public interface Event<T> {
    
    void fire(T event);

}
