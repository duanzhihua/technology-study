package cn.guxiangfly.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author guxiang02
 * @Date 2020/5/21
 **/
public class DrawEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DrawEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "Draw Event Occured";
    }
}
