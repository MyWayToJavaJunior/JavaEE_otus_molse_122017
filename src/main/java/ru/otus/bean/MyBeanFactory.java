package ru.otus.bean;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Enumeration;
import java.util.Hashtable;

public class MyBeanFactory implements ObjectFactory{
    @Override
    public Object getObjectInstance(Object obj, Name name2, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        MyBean bean = new MyBean();

        // Customize the bean properties from our attributes
        Reference ref = (Reference) obj;
        Enumeration addrs = ref.getAll();
        while (addrs.hasMoreElements()) {
            RefAddr addr = (RefAddr) addrs.nextElement();
            String name = addr.getType();
            String value = (String) addr.getContent();
            if (name.equals("foo")) {
                bean.setFoo(value);
            } else if (name.equals("bar")) {
                try {
                    bean.setBar(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    throw new NamingException("Invalid 'bar' value " + value);
                }
            }
        }

        // Return the customized instance
        return (bean);
    }
}
